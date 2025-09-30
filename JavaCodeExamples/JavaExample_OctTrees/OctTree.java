package examples.rezz0n8r.octtree;

import com.bmri.ui.core.geom.EuclidianVolume;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.geometry.euclidean.threed.Vector3D;

/**
 * @author bsisk
 */
public class OctTree<K, V> {

    protected OctNode<K, V> root;
    protected int size;
    protected Map<K, OctNode<K, V>> indexMap;

    public OctTree() {
        this.indexMap = new HashMap<>();
        root = null;
        size = 0;
    }
    
    public void setRootNode(OctNode<K,V> firstNode){
      this.root = firstNode;
      this.recordAddedNode(firstNode);
    }

    public int size() {
        return this.size();
    }
    
     public void recordAddedNode(OctNode<K,V> node){
      if(node == null){
        return;
      }
      
      this.size+=1;
      indexMap.put(node.getKey(),node);
    }

    public boolean isEmpty() {
       return (this.root == null);
    }

    public Map<K, OctNode<K, V>> getIndexMap() {
        return this.indexMap;
    }

    public OctNode<K, V> getNodeDirectlyByKey(K key) {
        return this.indexMap.get(key);
    }

    public void addOctantTo(OctNode<K, V> octant, OctNode<K, V> parentNode) throws OctTreeException {
        if (parentNode.isFull()) {
            throw new OctTreeException("Attempt to add a 9th node to parentNode: " + parentNode.getKey());
        } else {
            parentNode.addChild(octant);
        }
    }

    public Set<OctNode<K, V>> getChildrenOf(OctNode<K, V> target) throws OctTreeException {
        if (target == null) {
            throw new OctTreeException("getChildrenOf() was called on a null OctNode");
        }
        Map<K, OctNode<K, V>> childrenMap = target.getChildren();
        Set<OctNode<K, V>> nodesSet = new HashSet<>();
        nodesSet.addAll(childrenMap.values());
        return nodesSet;
    }

    public OctNode<K, V> getChildOfNodeByKey(OctNode<K, V> target, K nodeKey) {
        if (target == null) {
            return null;
        }
        Map<K, OctNode<K, V>> childrenMap = target.getChildren();
        return childrenMap.get(nodeKey);
    }

    public OctNode<K, V> getSmallestOctantContainingPoint3D(Vector3D point3d) {
        if (this.root == null) {
            return null;
        }
        EuclidianVolume wholeSpace= root.getRegion();
        OctNode<K, V> smallestOctantContaining = findSmallestOctantContainingPointR(root, point3d, wholeSpace);
        return smallestOctantContaining;
    }

    /**
     * 
     * @param focusNode
     * @param point3d
     * @param smallestNodeYet
     * @return the smallest Found OctNode containing the point
     * 
     */
    private OctNode<K, V> findSmallestOctantContainingPointR(OctNode<K, V> focusNode, Vector3D point3d, EuclidianVolume smallestOctantYet) {
        if ( focusNode == null || (!focusNode.containsPoint(point3d))) {
            return null;
        }
        if(focusNode.getRegion().compareSizeTo(smallestOctantYet) == 1){
           ///focus nodes region is Not smaller than the smallest Octant, So exit this path:
           return null;
        }
       smallestOctantYet = focusNode.getRegion();
       LinkedHashMap<K, OctNode<K,V>> childNodes = focusNode.getChildren();
       if(childNodes.isEmpty()){
         return focusNode; //focusNode is a Leaf. And, it contains the Point. So, return it.
       }
       OctNode<K,V> smallestFound = null; 
       //Now, iterate the child nodes of focusNode...
       for (Entry<K, OctNode<K, V>> childEntry : childNodes.entrySet()) {
            OctNode<K, V> childNode = childEntry.getValue();
            OctNode<K,V> candidateSmallerSubOctant = findSmallestOctantContainingPointR(childNode, point3d, smallestOctantYet);
            if (candidateSmallerSubOctant == null) {
                continue; //nothing smaller found, skip to the next child in this loop...
            }
            else {
                    if(smallestFound == null){
                      smallestFound = candidateSmallerSubOctant;
                    }
                    else if(candidateSmallerSubOctant.getRegion().compareSizeTo(smallestFound.getRegion()) == -1){
                      //candidate IS smaller than prev val of "smallestFound" (node):
                       smallestFound = candidateSmallerSubOctant;
                    }
            }
        }
        //all branches have been walked, no smallestFound found, so return null;
        return smallestFound;
    }
    
    public OctNode findSmallestOctantEntirelyContainingSearchVolume(EuclidianVolume search) throws OctTreeException{
      if(search.isEmpty()){
        return null;
      }
      if(this.root == null){
        throw new OctTreeException("Cannot search an empty OctTree");
      }
      return findSmallestOctantContainingVolumeR(root, search);
    }

    private OctNode<K, V> findSmallestOctantContainingVolumeR(OctNode<K, V> focusNode, EuclidianVolume searchBox) {
        if ( focusNode == null || (!focusNode.octantContainsVolume(searchBox))) {
            return null;
        }
       //we know this Node contains the searchBox, so...
       LinkedHashMap<K, OctNode<K,V>> childNodes = focusNode.getChildren();
       if(childNodes.isEmpty()){
         return focusNode; //focusNode is a Leaf. And, it contains the searchBox. So, return it.
       }
       //Now, iterate the child nodes of focusNode...
       OctNode<K,V> smallestFind=null;
       for (Entry<K, OctNode<K, V>> childEntry : childNodes.entrySet()) {
            OctNode<K, V> childNode = childEntry.getValue();
            OctNode<K,V> recursiveFind = findSmallestOctantContainingVolumeR(childNode, searchBox);
            if (recursiveFind == null) {
                continue; //THIS child octant doesnt contain the box, skip to the next child in this loop...
            }
            else {
                   if(smallestFind == null || recursiveFind.getRegion().compareSizeTo(smallestFind.getRegion()) < 0){
                       //we found a smaller one, so mark the new winner:
                       smallestFind = recursiveFind;
                   }
            }
        }
        //all branches have been walked, no smallestFound found, so return null;
        return smallestFind;
    }
    
    
    

}
