# Data-Structures-and-Algorithms
## Graphs.java
* Dags have no cycles. They are great for modelling dependencies where one task must occur before another. When it is required to determine a single order in which the tasks needs to be done, we perform a 'topological sort'. The linear order hence achieved may not be unique.
* **topologicalSortforDAGsWithoutDFS()** and **topologicalSortforDAGsUsingDFS()** are two ways of performing a topological sort. Although it is tempting to assume DFS alone can produce a topo sort, this assumption is mistaken. We need a Stack as well when using DFS.
* Topological sort takes **Θ(|V|+|E|)** time.
* Depth/Breadth First Traversal (or Search) for a graph is similar to Depth/Breadth First Traversal of a tree. The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again. To avoid processing a node more than once, we use a boolean visited array. 
* Note that the DFS/BFS traverses only the vertices reachable from a given source vertex. All the vertices may not be reachable from a given vertex (example Disconnected graph). To do complete DFS/BFS traversal of such graphs, we must call DFSUtil() {and similar logic for BFS} for every vertex. Also, before calling DFSUtil() {or similar BFS logic}, we should check if it is already visited. 
* Time Complexity of DFS/BFS: **O(|V|+|E|)** where V is number of vertices in the graph and E is number of edges in the graph.
