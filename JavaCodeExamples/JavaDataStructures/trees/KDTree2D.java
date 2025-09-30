package codeexamples.algs.trees;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class KDTree2D {
	
	public static void main(String[] args) {
		Rectangle2D.Float wholeArea = new Rectangle2D.Float(0f,0f,1,1);
		KDTree2D tree = new KDTree2D(wholeArea);
		tree.insert(new Point2D.Float(0.5f,0.5f));
		tree.insert(new Point2D.Float(0.20f, 0.8f));
		tree.insert(new Point2D.Float(0.6f,0.75f));
		tree.insert(new Point2D.Float(0.1f,0.9f));
		tree.insert(new Point2D.Float(0.9f,0.95f));
		System.out.println("Inserted 5 nodes in the tree: ");
		System.out.println();
		System.out.println(tree.toString());
		System.out.println("The nearest neighbor to point (0.189f,1f) is.....");
		Point2D.Float searchPt= new Point2D.Float(0.189f, 1f);
		
	}
	
	public static class Node{
		Point2D.Float data;
		Node left;
		Node right;
		Rectangle2D.Float rect;
		
		public Node(Point2D.Float value) {
			this.data = value;
		}
		
		public Node(Point2D.Float value, Rectangle2D.Float region) {
			this.data = value;
			this.rect = region;
		}
		
		public boolean isPointGeometricallyFoundInside(Point checkPt) {
		 if(checkPt == null || this.rect == null) {
			 return false;
		 }	
		 return this.rect.contains(checkPt); 
		}
		
		public boolean isNodeOverlapping(Rectangle2D.Float searchRect) {
		  if(searchRect == null || this.rect == null) {
			  return false;
		  }	
		  return this.rect.intersects(searchRect);
		}
		
		public String toString() {
		  return "[ p: "+this.data+", rect:["+this.rect+"]  ]";
			
		}
	}
	
	private int k = 2; ///the number of dimensions in ea point
	private Node root;
	private Rectangle2D.Float wholeSpace;
	
	public KDTree2D(Rectangle2D.Float fullSpace) {
		this.root = null;
		this.wholeSpace = fullSpace;
	}
	
	public boolean isEmpty() {
		return (this.root == null);
	}
	
	public void insert(Point2D.Float p) {
		if(p == null) {
			return;
		}
		if(this.isEmpty()) {
			this.root = new Node(p, this.wholeSpace);
		}
		else {
			this.root = insertRecursive(this.root, p,this.root.rect ,0);
		}
	}
	
	private Node insertRecursive(Node cursorNode, Point2D.Float point,Rectangle2D.Float area ,int depth) {
		if(cursorNode == null) {
			return new Node(point,area);
		}
		int dimPointer = (depth & k);
		Node nextBranch;
		if(dimPointer == 0) {
		   if(point.x == cursorNode.data.x) {
			   return cursorNode; //dont make any changes, exit out
		   }
		   int branchDirection = (point.x < cursorNode.data.x)? 0:1;
		   Rectangle2D.Float subRect = getSubrectangle(area, cursorNode.data.x, dimPointer, branchDirection);
		   if(branchDirection < 1) {
		      cursorNode.left = insertRecursive(cursorNode.left, point, subRect, depth+1);
		   }
		   else {
			   cursorNode.right = insertRecursive(cursorNode.right, point, subRect, depth+1);
		   } 
		}
		else {
			if(point.y == cursorNode.data.y) {
				   return cursorNode; //dont make any changes, exit out
			   }
			   int branchDirection = (point.y < cursorNode.data.y)? 0:1;
			   Rectangle2D.Float subRect = getSubrectangle(area, cursorNode.data.y, dimPointer, branchDirection);
			   if(branchDirection < 1) {
			      cursorNode.left = insertRecursive(cursorNode.left, point, subRect, depth+1);
			   }
			   else {
				   cursorNode.right = insertRecursive(cursorNode.right, point, subRect, depth+1);
			   } 
		}
	 return cursorNode;	
	}
	
	private Rectangle2D.Float getSubrectangle(final Rectangle2D startingRect, float dividerVal, int dimIndex, int whichHalf) {
	    if(dimIndex == 0) {
	    	if(whichHalf == 0) {
	    		float newWidth = dividerVal - (float)startingRect.getMinX();
	    		return new Rectangle2D.Float((float)startingRect.getMinX(), (float)startingRect.getMaxY(), newWidth, (float)startingRect.getHeight());
	    	}
	    	else {
	    		 float newWidth = (float)startingRect.getMaxX() - dividerVal;
	    		 return new Rectangle2D.Float(dividerVal, (float)startingRect.getMaxY(), newWidth, (float)startingRect.getHeight());
	    	}
	    }
	    else {
	    	  if(whichHalf == 0) {
	    		  //bottom half:
	    		  float newHeight = dividerVal - (float)startingRect.getMinY();
	    		  return new Rectangle2D.Float((float)startingRect.getMinX(), (float)startingRect.getMinY(), (float)startingRect.getWidth(), newHeight);
	    	  }
	    	  else {
	    		    float newHeight = (float)startingRect.getMaxY() - dividerVal;
	    		    return new Rectangle2D.Float((float)startingRect.getMinX(),(float) startingRect.getMaxY(), (float)startingRect.getWidth(), newHeight);
	    	  }
	    }
		
	}
	
	private Node nearestNeighbor(Node root, Point2D.Float target, int depth){
		  if(root == null){
		    ///empty tree, return null:
		    return null;
		  }
          int dim = depth%this.k;
          Node nextBranch=null;
          Node otherBranch = null;
          boolean isLower = (dim < 1)? (target.x < root.data.x): (target.y < root.data.y);
		  if(isLower){
		   nextBranch = root.left;
		   otherBranch = root.right;
		  }
		 else {
		        nextBranch = root.right;
		        otherBranch = root.left;
		  }
		 Node temp = nearestNeighbor(nextBranch, target, depth+1);
		 Node best = returnCloserNode(target, temp, root);
		 double distanceSqrd = best.data.distanceSq(target);
		 double altDist = (dim < 1)? Math.abs(target.x - root.data.x):Math.abs(target.y - root.data.y);
		 if(distanceSqrd >= (altDist * altDist)){
		   temp = nearestNeighbor(otherBranch, target, depth+1);
		   best = returnCloserNode(target, temp, best);
		 }
		 return best;
  }
	
	private Node returnCloserNode(Point2D.Float targetPoint, Node a, Node b){
	    double distanceFromA = a.data.distanceSq(targetPoint);
	    double distanceFromB = b.data.distanceSq(targetPoint);
	    return (distanceFromA <= distanceFromB)? a:b;
	 }
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("root: "+this.root);
		if(!(this.isEmpty())) {
			dumpRecursive(this.root,buff);
		}
		return buff.toString();
	}
	
	private void dumpRecursive(Node node,StringBuffer buffer) {
		if(node == null) {
			return;
		}
		buffer.append("Node: "+node);
		buffer.append("\\n");
		if(node.left != null) {
			buffer.append("/\\n");
			dumpRecursive(node.left,buffer);
		}
		if(node.right != null) {
			buffer.append("\\\n");
			buffer.append("  ");
			dumpRecursive(node.right, buffer);
		}
	}
}
