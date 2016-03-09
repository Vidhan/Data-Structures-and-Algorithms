Searching Algorithms

| Algorithm     | Worst-case time   | Best-case  time  | Requires sorted array?|
| ------------- |:-------------:| :-----------:|:--------------:|
| Linear Search | θ(n)          | θ(1)         | no             |
| Binary Search | θ(lgn)        | θ(1)         | yes            |


Sorting Algorithms

| Algorithm     | Worst-case time   | Best-case time   | Average-case time|Worst-case swaps| In-Place?|
| ------------- |:-------------:| :-----------:|:--------------:|:--------------:|:--------------:|
| Selection sort | θ(n^2)          | θ(n^2)         | θ(n^2)|θ(n)|yes           |
| Insertion sort | θ(n^2)        | θ(n)         | θ(n^2)|θ(n^2)|yes            |
|Merge sort | θ(nlgn)          | θ(nlgn)         | θ(nlgn)|θ(nlgn)|no             |
| Quicksort | θ(n^2)        | θ(nlgn)         | θ(nlgn)|θ(n^2)|yes            |

Randomised QuickSort is essentially the champ for large datasets. Can we beat it? 
Note: Insertion sort works well when no element has to move very far in the array. Once the subproblem sizes in the recursive quicksort gets down to some size k, no element has to move more than k-1 positions. Instead of continuing to call randomised quicksort once the subproblem sizes become sufficiently small, if we run the insertion sort over the subarray, we can sort even faster than randomized quicksort.
