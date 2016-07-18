import java.util.Comparator;

public class GraphNode{
	private int data;
	private Edge[] paths;

	public GraphNode(){
		data = 0;
		paths = null;
	}
	public GraphNode(int data, Edge[] paths){
		this.data = data;
		this.paths = paths;
	}

	public int getData(){
		return data;
	}
	public void setData(int data){
		this.data = data;
	}
	public Edge[] getPaths(){
		return paths;
	}
	public void setPaths(Edge[] paths){
		this.paths = paths;
	}
}

class DGraphNode extends GraphNode implements Comparable<DGraphNode>{
	// public class LenComp {
		
	// }
	public double distance;
	public DGraphNode parent;

	public DGraphNode(int data, Edge[] paths){
		super(data, paths);
		distance = Double.MAX_VALUE;
		parent = null;
	}
	@Override
	public int compareTo(DGraphNode b) {
        if(this.distance < b.distance){
        	return -1;
        }else if(this.distance == b.distance){
        	return 0;
        }else{
        	return 1;
        }
    }
}

class Edge{
	private GraphNode node;
	private double len;

	public Edge(){
		node = null;
		len = 0;
	}
	public Edge(GraphNode node, double len){
		this.node = node;
		this.len = len;
	}

	public GraphNode getNode(){
		return node;
	}
	public void setNode(GraphNode node){
		this.node = node;
	}
	public double getLen(){
		return len;
	}
	public void setLen(){
		this.len = len;
	}
}