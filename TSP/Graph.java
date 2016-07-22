import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph{
	ArrayList<GraphNode> nodes;
	DGraphNode[] dNodes;
	Path [][] adjMatrix;

	public Graph(){
		nodes = null;
		adjMatrix = null;
		dNodes = null;
	}
	// public Graph(ArrayList<GraphNode> nodes){
	// 	this.nodes = nodes;
	// 	adjMatrix = new Path[this.nodes.size()][this.nodes.size()];
	// }
	// private void fillAdjMatrix(DGraphNode[] dNodes){

	// }
	private DGraphNode[] resetDGraphArr(){
		if(nodes != null){
			dNodes = new DGraphNode[nodes.size()];
			for(int i = 0; i < dNodes.length; i++){
				DGraphNode temp = new DGraphNode(nodes.get(i).getData(), nodes.get(i).getPaths());
				dNodes[temp.getData()] = temp;
			}
			return dNodes;
		}
		return null;
	}
	// private Path createPath(int a, int b){

	// }
	public Path shortest(int a, int b){
		boolean [] visited = new boolean [nodes.size()];
		Path path = null;
		PriorityQueue<DGraphNode> queue = new PriorityQueue<DGraphNode>();
		dNodes = resetDGraphArr();
		dNodes[a].distance = 0;
		queue.add(dNodes[a]);
		DGraphNode temp = null;
		while(queue.size() != 0){
			temp = queue.poll();
			if(!visited[temp.getData()]){
				Edge[] pathways = temp.getPaths();
				for(int i = 0; i < pathways.length; i++){
					double distA = temp.distance + pathways[i].getLen();
					DGraphNode curr = dNodes[pathways[i].getNode().getData()];
					if(distA < curr.distance){
						curr.distance = distA;
						curr.parent = temp;
					}
					queue.add(curr);
				}
				visited[temp.getData()] = true;
			}
		}
		temp = dNodes[b];
		path = new Path();
		path.setDistance(temp.distance);
		while(temp != null){
			path.addHead(temp.getData());
			temp = temp.parent;
		}
		return path;
	}

	private static ArrayList<GraphNode> test(){
		ArrayList<GraphNode> nod = new ArrayList<GraphNode>();
		GraphNode start = new GraphNode(0, null);
		GraphNode b = new GraphNode(1, null);
		Edge[] temp = new Edge[1];
		temp[0] = new Edge(b, 1);
		start.setPaths(temp);
		nod.add(start.getData(), start);
		temp = new Edge[2];
		start = new GraphNode(2, null);
		GraphNode d = new GraphNode(3, null);
		temp[0] = new Edge(start, 6);
		temp[1] = new Edge(d, 3);
		b.setPaths(temp);
		nod.add(b.getData(),b);
		temp = new Edge[2];
		temp[0] = new Edge(b, 6);
		temp[1] = new Edge(d, 1);
		start.setPaths(temp);
		nod.add(start.getData(), start);
		temp = new Edge[2];
		temp[0] = new Edge(b, 3);
		temp[1] = new Edge(start, 1);
		d.setPaths(temp);
		nod.add(d.getData(), d);
		return nod;
	}
}