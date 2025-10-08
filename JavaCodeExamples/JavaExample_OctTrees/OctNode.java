/*
 * @author: bsisk
 */
package codeexamples.algs.octtree;


import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.commons.geometry.euclidean.threed.Bounds3D;
import org.apache.commons.geometry.euclidean.threed.ConvexVolume;
import org.apache.commons.geometry.euclidean.threed.RegionBSPTree3D;
import org.apache.commons.geometry.euclidean.threed.Vector3D;


/**
 *
 * @author Brad Sisk
 * 
 * This Generic class represents a Node in an OctTree, which divides 3D space. Each Node has a Key, a generic Value
 * and a Region. The Region being a Convex 3D Volume (uses Apache Commons Math, Geometry, EuclidianGeometry).
 */
public class OctNode<K,V> {
    
    private K key;
    private V value;
    private ConvexVolume region;
    private OctNode<K,V> parent;
    private LinkedHashMap<K,OctNode<K,V>> childNodes;
    
    public OctNode(K nodeKey, V nodeValue, ConvexVolume volume){
       this.key = nodeKey;
       this.value = nodeValue;
       this.region = volume;
       this.parent = null;
       this.childNodes = new LinkedHashMap<>();
    }
    
    public OctNode(K nodeKey, V nodeValue, ConvexVolume vol, OctNode parentNode){
        this(nodeKey,nodeValue,vol);
        this.parent = parentNode;
    }
    
    public OctNode<K,V> addToParentNode(OctNode<K,V> parentNode){
      this.parent = parentNode;
      return this;
    }
    
    public boolean isFull(){
      return (this.childNodes.size() == 8);
    }
    
    public OctNode<K,V> addChild(OctNode<K,V> child) throws OctTreeException{
        if(this.isFull()){
            throw new OctTreeException("Attempt to add a 9th child Node to OctNode: "+this.key);
        }
        this.childNodes.put(child.getKey(), child);
        child.addToParentNode(this);
        return this;
    }
    
    public LinkedHashMap<K,OctNode<K,V>> getChildren(){
     return this.childNodes;
    }
    
    public K getKey(){
        return this.key;
    }
    
    public V getData(){
        return this.value;
    }
    
    public OctNode<K,V> getParentNode(){
        return this.parent;
    }
    
    public boolean hasParent(){
        return (this.parent != null);
    }
    
    public ConvexVolume getRegion(){
        return this.region;
    }
    
    public void setRegion(ConvexVolume vol){
        this.region = vol;
    }
    
    public boolean isIntersectedBy(ConvexVolume other){
    	if (other == null || other.isEmpty()) {
            return false;
        }
        final RegionBSPTree3D thisBsp = this.getRegion().toTree();
        final RegionBSPTree3D otherBsp = other.toTree();
        RegionBSPTree3D commonIntersection = new RegionBSPTree3D();
        commonIntersection.intersection(thisBsp, otherBsp);
        return ((!(commonIntersection.isEmpty())) && (commonIntersection.getSize() > 0.00001d)); //return True: if the intersection volume is Not Empty

    }
    
    public Set<OctNode<K,V>> getIntersectedSubOctants(ConvexVolume testVolume, OctNode<K,V> searchNode){
       Set<OctNode<K,V>> intersectedSubOctants = new HashSet<>();
        for(K nodeKey: this.childNodes.keySet()){
            OctNode<K,V> childOctant= this.childNodes.get(nodeKey);
            if(childOctant.isIntersectedBy(testVolume)){
              intersectedSubOctants.add(childOctant);
            }
       }
     return intersectedSubOctants;
    }
    
    public boolean containsPoint(Vector3D point3d){
      if(point3d == null){
        return false;
      }
      return this.region.contains(point3d);
    
    }

    boolean octantContainsVolume(ConvexVolume searchBox) {
    	Bounds3D searchBoundsBox = searchBox.getBounds();
    	Bounds3D thisBoundsBox = this.getRegion().getBounds();
        Bounds3D intersection3D =  thisBoundsBox.intersection(searchBoundsBox);
        return intersection3D.equals(searchBoundsBox); //only true if this region entirely Contains searchBox
    }
    
}
