import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph{
	ArrayList<GraphNode> nodes;
	//Path [][] adjMatrix;

	public Graph(){
		nodes = test();
		//adjMatrix = null;
	}
	// private void fillAdjMatrix(Path path){
	// 	PathNode tempI = path.getHead();
	// 	while(tempI != null){
	// 		PathNode tempJ = tempI.next;

	// 	}

	// }
	public Path shortest(int a, int b){
		boolean [] visited = new boolean [nodes.size()];
		DGraphNode[] dNode = new DGraphNode[nodes.size()];
		Path path = null;
		PriorityQueue<DGraphNode> queue = new PriorityQueue<DGraphNode>();
		DGraphNode temp = null;
		for(int i = 0; i < nodes.size(); i++){
			temp = new DGraphNode(nodes.get(i).getData(), nodes.get(i).getPaths());
			if(i == a){
				temp.distance = 0;
				queue.add(temp);
			}
			dNode[temp.getData()] = temp;
			//queue.add(temp);
		}
		temp = null;
		while(queue.size() != 0){
			temp = queue.poll();
			if(!visited[temp.getData()]){
				Edge[] pathways = temp.getPaths();
				for(int i = 0; i < pathways.length; i++){
					double distA = temp.distance + pathways[i].getLen();
					DGraphNode curr = dNode[pathways[i].getNode().getData()];
					if(distA < curr.distance){
						curr.distance = distA;
						curr.parent = temp;
					}
					queue.add(curr);
				}
				visited[temp.getData()] = true;
			}
		}
		temp = dNode[b];
		path = new Path();
		path.setDistance(temp.distance);
		while(temp != null){
			path.addHead(temp.getData());
			temp = temp.parent;
		}
		return path;
	}

	private ArrayList<GraphNode> test(){
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