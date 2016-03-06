import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

class VertexWeighted{
	
	int vertexNumber;
	LinkedList<WeightedEdge> adjacencyList;
	
	VertexWeighted(int number){
		adjacencyList = new LinkedList<WeightedEdge>();
		vertexNumber = number;
	}
	
	void addAdjacentVertices(WeightedEdge[] edges){
		for (WeightedEdge e : edges){
			adjacencyList.add(e);
		}
	}
	
}
class WeightedEdge{
	VertexWeighted endVertex;
	int weight;
	
	WeightedEdge(VertexWeighted endVertex, int weight){
		this.endVertex = endVertex;
		this.weight = weight;
	}
}

class VertexWithDistance implements Comparator<VertexWithDistance>
{
    VertexWeighted vertex;
    int distance;
 
    VertexWithDistance(){
    }
 
    VertexWithDistance(VertexWeighted vertex, int distance)
    {
        this.vertex = vertex;
        this.distance = distance;
    }
    @Override
	public int compare(VertexWithDistance vertex1, VertexWithDistance vertex2){
        if (vertex1.distance < vertex2.distance)
            return -1;
        if (vertex1.distance > vertex2.distance)
            return 1;
        return 0;
    }
    @Override
    public boolean equals(Object o){
      if(o instanceof VertexWithDistance){
        VertexWithDistance c = (VertexWithDistance)o;
        return vertex == c.vertex && distance == c.distance;
      }
      return false;
    }
}

class Tester{
	public static void main(String...  args){
	//	PriorityQueue<VertexWithDistance> = new PriorityQueue<VertexWithDistance>();
		
	}
}
public class WeightedGraph {
	ArrayList<VertexWeighted> vertexSet;
	
	WeightedGraph(VertexWeighted[] vertexSet){
		this.vertexSet=new ArrayList<VertexWeighted>();
		for (VertexWeighted v : vertexSet){
			this.vertexSet.add(v);
		}
	}
	
	Object[] bellmanFord(VertexWeighted source){
		int shortestPathLength[] = new int[vertexSet.size()];
		VertexWeighted predecessor[] = new VertexWeighted[vertexSet.size()];
		
		//set shortestPathLength to MAX_VALUE for all vertices except source
		//set shortestPathLength of source to 0
		//set predecessor of all Vertices to null
		for (int i=0; i<shortestPathLength.length; i++){
			if (i==source.vertexNumber){
				shortestPathLength[i]=0;
			} else{
				shortestPathLength[i]=Integer.MAX_VALUE;
			}
			predecessor[i]=null;
		}
		
		//call relax on each edge. Do this |V|-1 times
		for (int i = 1;i<=vertexSet.size()-1;i++){
			for (VertexWeighted u:vertexSet){
				Iterator<WeightedEdge> weightedEdges = u.adjacencyList.listIterator();
				while(weightedEdges.hasNext()){
					WeightedEdge e = weightedEdges.next();
					relax(u,e, shortestPathLength, predecessor);
				}
			}
		}
		return new Object[]{shortestPathLength, predecessor};
	}
	
	Object[] dijkstra(VertexWeighted source){
		int shortestPathLength[] = new int[vertexSet.size()];
		VertexWeighted predecessor[] = new VertexWeighted[vertexSet.size()];
		PriorityQueue<VertexWithDistance> queue = new PriorityQueue<VertexWithDistance>(new VertexWithDistance());
		
		
		//set shortestPathLength to MAX_VALUE for all vertices except source
		//set shortestPathLength of source to 0
		//set predecessor of all Vertices to null
		for (int i=0; i<shortestPathLength.length; i++){
			if (i==source.vertexNumber){
				shortestPathLength[i]=0;
			} else{
				shortestPathLength[i]=Integer.MAX_VALUE;
			}
			predecessor[i]=null;
		}
		
		//Insert all vertices into the priority Queue. Note: Priority is decided based on shortestPathLength value of vertex
		for (VertexWeighted u:vertexSet){
			queue.add(new VertexWithDistance(u, shortestPathLength[u.vertexNumber]));
			
		}
		
		while(!queue.isEmpty()){
			//Remove the vertex with the smallest distance
			VertexWeighted u = queue.remove().vertex;
			Iterator<WeightedEdge> weightedEdges = u.adjacencyList.listIterator();
			
			//Relax all its adjacent edges
			while(weightedEdges.hasNext()){
				WeightedEdge e = weightedEdges.next();
				int shortestDistanceBeforeRelax = shortestPathLength[e.endVertex.vertexNumber];
				relax(u,e,shortestPathLength, predecessor);
				
				//if call to relax updated the shortestDistance for any adjacent vertex, then
				//decrease the key-value in priority queue as well
				if (shortestPathLength[e.endVertex.vertexNumber] != shortestDistanceBeforeRelax){
					queue.remove(new VertexWithDistance(e.endVertex,shortestDistanceBeforeRelax ) );
					queue.add(new VertexWithDistance(e.endVertex,shortestPathLength[e.endVertex.vertexNumber] ) );
				}
			}
		}
		return new Object[]{shortestPathLength, predecessor};
	}
	
	private void relax(VertexWeighted u, WeightedEdge e, int[] shortest, VertexWeighted[] predecessor){
		int startVertex = u.vertexNumber;
		int endVertex = e.endVertex.vertexNumber;
		int weight = e.weight;
		
		if (shortest[startVertex]+weight<shortest[endVertex]){
			shortest[endVertex]=shortest[startVertex]+weight;
			predecessor[endVertex]=u;
		}
			
	}
	
	public static void main(String args[]){
		VertexWeighted zeroth = new VertexWeighted(0);
		VertexWeighted first = new VertexWeighted(1);
		VertexWeighted second = new VertexWeighted(2);
		VertexWeighted third = new VertexWeighted(3);
		VertexWeighted fourth = new VertexWeighted(4);
		VertexWeighted fifth = new VertexWeighted(5);
		
		zeroth.addAdjacentVertices(new WeightedEdge[]{new WeightedEdge(first, 3), new WeightedEdge(second, 4)});
		first.addAdjacentVertices(new WeightedEdge[]{new WeightedEdge(second, 6), new WeightedEdge(third, 2), new WeightedEdge(fourth, 7)});
		second.addAdjacentVertices(new WeightedEdge[]{new WeightedEdge(fourth, 5)});
		third.addAdjacentVertices(new WeightedEdge[]{new WeightedEdge(fourth, 1), new WeightedEdge(fifth, 8)});
		fourth.addAdjacentVertices(new WeightedEdge[]{new WeightedEdge(fifth, 4)});
		fifth.addAdjacentVertices(new WeightedEdge[]{});
		
		
		WeightedGraph graph = new WeightedGraph(new VertexWeighted[]{zeroth, first, second, third, fourth,fifth});
		
		long timeBeforeBellman = System.nanoTime();
		System.out.println("Bellman-Ford");
		Object[] answer = graph.bellmanFord(zeroth);
		int[] shortest = (int[])answer[0];
		VertexWeighted[] predecessor = (VertexWeighted[])answer[1];
		
		for (int i:shortest){
			System.out.print(i + " ");
		}
		System.out.println();
		for (VertexWeighted v: predecessor){
			if (v==null){
				System.out.print("NULL"+ " ");
			} else{
				System.out.print(v.vertexNumber + " ");
			}
		}
		
		long timeAfterBellman = System.nanoTime();
		long durationBellman = (timeAfterBellman - timeBeforeBellman);
		System.out.println();
		System.out.println("Time for bellman: "+durationBellman+"ns");
		System.out.println();
		System.out.println();
		
		long timeBeforeDijkstra = System.nanoTime();
		System.out.println("Dijkstra");
		Object[] answer2 = graph.dijkstra(zeroth);
		int[] shortest2 = (int[])answer2[0];
		VertexWeighted[] predecessor2 = (VertexWeighted[])answer2[1];
		
		for (int i:shortest2){
			System.out.print(i + " ");
		}
		System.out.println();
		for (VertexWeighted v: predecessor2){
			if (v==null){
				System.out.print("NULL"+ " ");
			} else{
				System.out.print(v.vertexNumber + " ");
			}
		}
		long timeAfterDijkstra = System.nanoTime();
		long durationDijkstra = (timeAfterDijkstra - timeBeforeDijkstra);
		System.out.println();
		System.out.println("Time for dijkstra: "+durationDijkstra+"ns");
	}
}
	


