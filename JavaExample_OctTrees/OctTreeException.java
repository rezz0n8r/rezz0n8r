/*
 * @author: bsisk
 */
package examples.rezz0n8r.octtree;

public class OctTreeException extends Exception{
    public OctTreeException(){
        super();
    }
    
    public OctTreeException(String msg){
      super(msg);
    }
    
    public OctTreeException(String msg, Throwable cause){
      super(msg,cause);
    }
}
