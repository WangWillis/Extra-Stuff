public class Path{
	PathNode head, tail;
	int size;
	double distance;
	//static methods
	//does not create new nodes just sets headpointer and new size
	// public static Path createPath(PathNode a, PathNode b){
	// 	Path path = new Path();
	// 	path.setHead(a);
	// 	int sizeTemp = 0;
	// 	while(a != null && a != b){
	// 		sizeTemp++;
	// 		a = a.next;
	// 	}
	// 	path.setSize(sizeTemp);
	// 	return path;
	// }

	//constructors
	public Path(){
		head = null;
		size = 0;
		distance = 0;
	}

	//getters
	public double getDistance(){
		return distance;
	}
	public PathNode getHead(){
		return head;
	}

	//setters
	// public void setHead(PathNode temp){
	// 	if(head == null)
	// 		head = temp;
	// }
	public void setSize(int size){
		this.size = size;
	}

	//adders
	public void addDistance(double dist){
		distance += dist;
	}
	public void setDistance(double dist){
		distance = dist;
	}
	//adder for lists
	public void addNode(int data){
		if(head == null){
			head = tail = new PathNode(data);
		}else{
			tail.next = new PathNode(data);
			tail = tail.next;
		}
		size++;
	}
	public void addHead(int data){
		PathNode temp = new PathNode(data);
		temp.next = head;
		head = temp;
		size++;
	}
	//used to append a path onto another
	//does not modify the passed in path
	public Path append(Path temp){
		distance += temp.getDistance();
		PathNode node = temp.getHead();
		for(int i = 0; i < size && node != null; i++){
			this.addNode(node.data);//inc size in method so no need to inc here
			node = node.next;
		}
		return this;
	}

	//overloaded methods
	public String toString(){
		String path = "";
		PathNode temp = head;
		for(int i = 0; i < size && temp != null; i++){
			path += Integer.toString(temp.data) + " ";
			temp = temp.next;
		}
		return path;
	}
}

class PathNode{
	public int data;
	public PathNode next;
	public PathNode(int data){
		this.data = data;
		//len = 0;
		next = null;
	}
}

