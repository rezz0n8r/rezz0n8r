/*
 * @author: bsisk
 */
package examples.rezz0n8r.octtree;

import com.bmri.ui.core.geom.EuclidianVolume;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import org.apache.commons.geometry.euclidean.threed.Vector3D;



/**
 *
 * @author graphics
 */
public class OctNode<K,V> {
    
    private K key;
    private V value;
    private EuclidianVolume region;
    private OctNode<K,V> parent;
    private LinkedHashMap<K,OctNode<K,V>> childNodes;
    
    public OctNode(K nodeKey, V nodeValue, EuclidianVolume volume){
       this.key = nodeKey;
       this.value = nodeValue;
       this.region = volume;
       this.parent = null;
       this.childNodes = new LinkedHashMap<>();
    }
    
    public OctNode(K nodeKey, V nodeValue, EuclidianVolume vol, OctNode parentNode){
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
    
    public EuclidianVolume getRegion(){
        return this.region;
    }
    
    public void setRegion(EuclidianVolume vol){
        this.region = vol;
    }
    
    public boolean isIntersectedBy(EuclidianVolume ev){
       if(ev == null || this.region == null){
           return false;
       }
       return ev.isIntersectingOther(this.region);
    }
    
    public Set<OctNode<K,V>> getIntersectedSubOctants(EuclidianVolume testVolume, OctNode<K,V> searchNode){
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

    boolean octantContainsVolume(EuclidianVolume searchBox) {
        return this.getRegion().entirelyContainsOther(searchBox);
    }
    
}
