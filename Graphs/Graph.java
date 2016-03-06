import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Vertex{
	
	int vertexNumber;
	LinkedList<Vertex> adjacencyList;
	
	Vertex(int number){
		adjacencyList = new LinkedList<Vertex>();
		vertexNumber = number;
	}
	
	void addAdjacentVertices(Vertex[] vertices){
		for (Vertex v : vertices){
			adjacencyList.add(v);
		}
	}
	
}

public class Graph {
	
	ArrayList<Vertex> vertexSet;
	
	Graph(Vertex[] vertexSet){
		this.vertexSet=new ArrayList<Vertex>();
		for (Vertex v : vertexSet){
			this.vertexSet.add(v);
		}
	}
	
	void BFS(Vertex source){
		Boolean visited[]= new Boolean[vertexSet.size()];
		Queue<Vertex> queue = new LinkedList<Vertex>();
		
		for (Vertex i : vertexSet ){
			visited[i.vertexNumber]=false;
		}
		
		queue.add(source);
		visited[source.vertexNumber]=true;
		
		while (!queue.isEmpty()){
			Vertex current = queue.poll();
			System.out.print(current.vertexNumber + " ");
			Iterator<Vertex> adjacentVertices = current.adjacencyList.listIterator();
			while (adjacentVertices.hasNext()){
				Vertex adjacentVertex = adjacentVertices.next();
				if (!visited[adjacentVertex.vertexNumber]){
					queue.add(adjacentVertex);
					visited[adjacentVertex.vertexNumber]=true;
				}
			}
		}		
	}
	
	void DFS(Vertex source){
		boolean visited[]= new boolean[vertexSet.size()];
		for (Vertex i : vertexSet ){
			visited[i.vertexNumber]=false;
		}
		
		DFSUtil(source, visited);
	}
	
	void topoUtil(Vertex v, boolean[] visited, Stack<Vertex> stack){
		visited[v.vertexNumber]=true;
		Iterator<Vertex> adjacentVertices = v.adjacencyList.listIterator();
		while(adjacentVertices.hasNext()){
			Vertex adjacentVertex = adjacentVertices.next();
			if (!visited[adjacentVertex.vertexNumber]){
				topoUtil(adjacentVertex, visited, stack);
			}
		}
		stack.push(v);
	}
	
	void topologicalSortforDAGsUsingDFS(){
		Stack<Vertex> stack = new Stack<Vertex>();
		boolean visited[] = new boolean[vertexSet.size()];
		
		for(Vertex v: vertexSet){
			visited[v.vertexNumber]=false;
		}
		
		for (Vertex v: vertexSet){
			if (!visited[v.vertexNumber]){
				topoUtil(v, visited, stack);
			}
		}
		
		while(!stack.isEmpty()){
			System.out.print(stack.pop().vertexNumber+" ");
		}
	}
	
	void topologicalSortforDAGsWithoutDFS(){
		int inDegree[] = new int[vertexSet.size()];
		for (Vertex v:vertexSet){
			inDegree[v.vertexNumber]=0;
		}
		for (Vertex v:vertexSet){
			Iterator<Vertex> adjacentVertices = v.adjacencyList.listIterator();
			while(adjacentVertices.hasNext()){
				inDegree[adjacentVertices.next().vertexNumber]++;
			}
		}
		Queue<Vertex> queue = new LinkedList<Vertex>();
		for (Vertex v:vertexSet){
			if (inDegree[v.vertexNumber]==0){
				queue.add(v);
			}
		}
		
		while(!queue.isEmpty()){
			Vertex v = queue.poll();
			System.out.print(v.vertexNumber+ " ");
			Iterator<Vertex> adjacentVertices = v.adjacencyList.listIterator();
			while(adjacentVertices.hasNext()){
				Vertex adjacentVertex = adjacentVertices.next();
				if (--inDegree[adjacentVertex.vertexNumber]==0){
					queue.add(adjacentVertex);
				}
			}
		}
	}
	
	private void DFSUtil(Vertex source, boolean[] visited){

		System.out.print(source.vertexNumber + " ");
		visited[source.vertexNumber]=true;
		Iterator<Vertex> adjacentVertices = source.adjacencyList.listIterator();
	    while (adjacentVertices.hasNext()){
	    	Vertex adjacentVertex = adjacentVertices.next();
	    	if (!visited[adjacentVertex.vertexNumber])
	                DFSUtil(adjacentVertex, visited);
	        }
	}
	
	public static void main(String args[]){
		
		Vertex zeroth = new Vertex(0);
		Vertex first = new Vertex(1);
		Vertex second = new Vertex(2);
		Vertex third = new Vertex(3);
		Vertex fourth = new Vertex(4);
		Vertex fifth = new Vertex(5);
		
		zeroth.addAdjacentVertices(new Vertex[]{});
		first.addAdjacentVertices(new Vertex[]{});
		second.addAdjacentVertices(new Vertex[]{third});
		third.addAdjacentVertices(new Vertex[]{first});
		fourth.addAdjacentVertices(new Vertex[]{zeroth,first});
		fifth.addAdjacentVertices(new Vertex[]{zeroth,second});
		
		Graph graph = new Graph(new Vertex[]{zeroth, first, second, third, fourth, fifth});
		
		System.out.print("DFS: ");
		graph.DFS(second);
		System.out.println();
		System.out.print("BFS: ");
		graph.BFS(second);
		System.out.println();
		System.out.print("Topological: ");
		graph.topologicalSortforDAGsUsingDFS();
		System.out.println();
		System.out.print("Topological without DFS: ");
		graph.topologicalSortforDAGsWithoutDFS();	
	}
}


