package alda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MyGraph<T extends Comparable<? super T>> implements MiniGraph<T> {
	
	//The graph adjacency list
	private HashMap<T, List<Edge<T>>> nodeMap = new HashMap<T, List<Edge<T>>>();
	private int NUMBER_OF_EDGES = 0;
	private int TOTAL_EDGE_WEIGHT = 0;
	
	//The edge class that ceeps track on the oregin of the edge and its destination
	private class Edge<T> implements Comparable<Edge<T>>{
		public int weight;
		public T destination;
		public T source;
		
		public Edge(T dest, T source, int w){
			this.destination = dest;
			this.source = source;
			this.weight = w;
		}
		//Makes it possible to sort our edges in the Prims-algorithm
		@Override
		public int compareTo(Edge<T> o) {
			if(o.weight < this.weight){
				return 1;
			}
			else if(o.weight > this.weight){
				return -1;
			}
			else{
				return 0;
			}
			
		}
	}
		
	@Override
	public void addNode(T n) {
		//Adds the node if it dosent allready exist
		if(!contains(n)){
			nodeMap.put(n, new ArrayList<Edge<T>>());
		}
	}

	@Override
	public void removeNode(T n){  
		// Removes all the edges that are pointing at the 
		// node we require to remove
		List<Edge<T>> list = nodeMap.get(n);
		for(Edge<T> e : list){
			List<Edge<T>> x = nodeMap.get(e.destination);
			Edge<T>  remove = null;
			for(Edge<T> i : x){
				if(i.destination.equals(n)){
					remove = i;
					break;
				}
			}
			x.remove(remove);
		}
		//Removes the requierd node.
		nodeMap.remove(n);
	}

	public void connectNodes(T n1, T n2, int weight) {
		List<Edge<T>> nodeA = nodeMap.get(n1);
		List<Edge<T>> nodeB = nodeMap.get(n2);
		//Check if the nodes exists and if no preexisting edge is defined.
		if(nodeB!=null && nodeA != null && checkEdges(nodeA, n1, nodeB, n2) && weight >= 0){
			Edge<T> edgeA = new Edge<T>(n1, n2, weight);
			Edge<T> edgeB = new Edge<T>(n2, n1, weight);
			//Adding the edge to the list of edges to requested node.
			nodeA.add(edgeB);
			nodeB.add(edgeA);
			NUMBER_OF_EDGES++;
			TOTAL_EDGE_WEIGHT += weight;
		}
	}

	private boolean checkEdges(List<Edge<T>> edgesA, T vertexA, List<Edge<T>> edgesB, T vertexB){
		//If there should be a match there is allready a defined edge
		for(Edge<T> edge : edgesA){
			if(edge.destination == vertexB){
				return false;
			}
		}
		for(Edge<T> edge : edgesB){
			if(edge.destination == vertexB){
				return false;
			}
		}	
		return true;
	}
	
	@Override
	public void disconnectNodes(T n1, T n2) {
		List<Edge<T>> edgesA = nodeMap.get(n1);
		List<Edge<T>> edgesB = nodeMap.get(n2);
		int weight = 0;
		//Locates and removes the reqierd edge on both nodes.
		if(edgesA!=null && edgesB != null ){
			for(int x= 0; x < edgesA.size(); x++){
				if(edgesA.get(x).destination.equals(n2)){
					edgesA.remove(x);
					weight = edgesA.get(x).weight;
					break;
				}
			}
			for(int x= 0; x < edgesB.size(); x++){
				if(edgesB.get(x).destination.equals(n2)){
					edgesB.remove(x);
					break;
				}
			}
			// Removing edge weight from total weight
			TOTAL_EDGE_WEIGHT -= weight;
		}
	}

	@Override
	public boolean contains(T n) {
		return nodeMap.get(n) != null;
	}

	@Override
	public int getNumberOfNodes() {
		return nodeMap.size();
	}

	@Override
	public boolean edgeExistsBetween(T n1, T n2) {
		List<Edge<T>> edgesA = nodeMap.get(n1);
		List<Edge<T>> edgesB = nodeMap.get(n2);
		// if there should be a match the edge exists 
		if(edgesA!=null && edgesB != null ){
			for(int x= 0; x < edgesA.size(); x++){
				if(edgesA.get(x).destination.equals(n2)){
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public int getNumberOfEdges(){
		return NUMBER_OF_EDGES;
	}

	@Override
	public int getTotalEdgeWeight(){
		return TOTAL_EDGE_WEIGHT;
	}

	@Override
	public MiniGraph<T> generateMinimumSpanningTree(){
		MiniGraph<T> graph = new MyGraph<T>();		
		if(nodeMap.size() > 0){
			Object[] valuesList;
			valuesList = nodeMap.keySet().toArray();
			//Obtaing the first key in the HashMap to use as root
			T root = (T)valuesList[0];
			
			graph.addNode(root);
			List<Edge<T>> edges = nodeMap.get(root);
			Collections.sort(edges);
			//Prims
			while(!edges.isEmpty()){
				Edge<T> temp = edges.get(0);
				edges.remove(0);
				//If node is not in the new MinniSpanningTree add it
				if(!graph.contains(temp.destination)){
					graph.addNode(temp.destination);
					graph.connectNodes(temp.destination, temp.source, temp.weight);
					edges.addAll(nodeMap.get(temp.destination));
					//Adds to the collection of nodes with their
					//edges and sorts them by the smalles
					Collections.sort(edges);
				}
			}
		}
		return graph;
	}

}
