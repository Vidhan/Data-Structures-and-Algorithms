# Data-Structures-and-Algorithms
## Graphs.java
* Dags have no cycles. They are great for modelling dependencies where one task must occur before another. When it is required to determine a single order in which the tasks needs to be done, we perform a 'topological sort'. The linear order hence achieved may not be unique.
* **topologicalSortforDAGsWithoutDFS()** and **topologicalSortforDAGsUsingDFS()** are two ways of performing a topological sort. Although it is tempting to assume DFS alone can produce a topo sort, this assumption is mistaken. We need a Stack as well when using DFS.
* Topological sort takes big-Θ(|V|+|E|) time.
