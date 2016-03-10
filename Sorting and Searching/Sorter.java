
public class Sorter {
	
	public static void main (String args[]){
		int input1[] = {10,2,9,5,8,0,-4,100,-5};
		int input2[] = {10,2,9,5,8,0,-4,100,-5};
		int input3[] = {10,2,9,5,8,0,-4,100,-5};
		int input4[] = {10,2,9,5,8,0,-4,100,-5};
		int input5[] = {10,2,9,5,8,0,-4,100,-5};
		
		long timeBeforeSelection = System.nanoTime();
		int answer1[]=selectionSort(input1);
		long timeAfterSelection = System.nanoTime();
		
		long timeBeforeInsertion = System.nanoTime();
		int answer2[]=insertionSort(input2);
		long timeAfterInsertion = System.nanoTime();
		
		long timeBeforeMergeSort = System.nanoTime();
		mergeSort(input3,0,input3.length-1);
		long timeAfterMergeSort = System.nanoTime();
		
		long timeBeforeQuickSort = System.nanoTime();
		quickSort(input4,0,input4.length-1);
		long timeAfterQuickSort = System.nanoTime();

		for (int i : answer1){
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : answer2){
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : input3){
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : input4){
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("Time for Selection: "+(timeAfterSelection-timeBeforeSelection)+"ns");
		System.out.println("Time for Insertion: "+(timeAfterInsertion-timeBeforeInsertion)+"ns");
		System.out.println("Time for MergeSort: "+(timeAfterMergeSort-timeBeforeMergeSort)+"ns");
		System.out.println("Time for QuickSort: "+(timeAfterQuickSort-timeBeforeQuickSort)+"ns");
		
		System.out.println(recursiveBinarySearch(input4,0,input4.length-1, -5)+" ");
		System.out.println(sentinelLinearSearch(input5, 100));
		System.out.println(recursiveLinearSearch(input5, 0,100));

	}
	
	//Selection Sort
	//Time complexity - 0(n^2) in all cases
	//#. of comparisons = Summ (n-i) over i -->as i goes from 0 to n-2 i.e. 0(n^2)
	//#. of swaps - 0(n) {for all cases}
	//Running time unaffected if the input is already sorted
	//If swapping is memory intensive or over a slow disk, this algo can be chosen
	static int[] selectionSort (int[] input){
		if (input.length == 0 || input.length == 1){
			return input;
		}
		for (int i =0; i<input.length-1; i++){
			int minIndex=i;
			for (int j=i+1; j<input.length; j++){
				if (input[j]<input[minIndex]){
					minIndex = j;
				}
			}
			if (minIndex != i){
				int temp = input[i];
				input[i]=input[minIndex];
				input[minIndex]=temp;
			}
		}
		return input;
	}
	
	//Insertion Sort
	//Running time affected if input is sorted
	//Time complexity - 0(n^2) {Worst case} //input sorted in reverse order - inner loop iterates summ(i-1) times over i (as i goes from 1 to n-1)
	//Time complexity - 0(n) {Best case} //input already sorted - inner loop makes only 1 comparison per 1 run of outer loop.
	//Insertion sort is excellent choice when the array is almost sorted.
	//# of comparisons = 0(n) in best-case, 0(n^2) in average case, 0(n^2) in worst case
	//# of swaps = 0(0) in best-case, 0(n^2) in worst case 
	static int[] insertionSort (int[] input){
		if (input.length==0 || input.length ==1){
			return input;
		}
		
		for(int i=1; i<input.length; i++){
			for (int j=i-1; j>-1 && (input[j]>input[j+1]);j--){
					int temp=input[j+1];
					input[j+1]=input[j];
					input[j]=temp;
			}
		}
		
		return input;
		
	}
	
	//MergeSort
	//Running Time - 0(nlgn) in all cases - T(n) = 2T(n/2)+0(n)
	//Constant factor that we hide in asymptotic notation is higher{insignificant if n is large}
	//Merge Sort doesnt work in place. So if space is a premium, you may want to avoid using this
	//Employs divide amd conquer 
	static void mergeSort(int[] array, int startIndex, int endIndex){
		if (endIndex>startIndex){
			
			int midIndex = (startIndex+endIndex)/2;
			mergeSort(array, startIndex, midIndex);
			mergeSort(array, midIndex+1, endIndex);
			Merge(array, startIndex, midIndex, endIndex);
		}
		
	}
	
	//0(n). if you try to merge in place, you may use 0(n^2) time. but a linear time in-place merge procedure exists(complicated)
	static void Merge(int[] array, int startIndex, int midIndex, int endIndex){
		int[] array1 = new int[midIndex-startIndex+2];
		int[] array2 = new int[endIndex-midIndex+1];
		int firstArrIndex = 0;
		int secondArrIndex = 0;
		for (int i=startIndex; i<=midIndex; i++){
			array1[firstArrIndex++]=array[i];
		}
		
		for (int i=midIndex+1; i<=endIndex; i++){
			array2[secondArrIndex++]=array[i];
		}
		
		array1[array1.length-1]=Integer.MAX_VALUE;
		array2[array2.length-1]=Integer.MAX_VALUE;
		
		firstArrIndex = 0;
		secondArrIndex = 0;
		
		for (int i = startIndex; i<=endIndex; i++){
			if (array1[firstArrIndex] <= array2[secondArrIndex]){
				array[i]=array1[firstArrIndex++];
			} else{
				array[i]=array2[secondArrIndex++];
			}
		}
		
	}
	
	
	//QuickSort
	//Uses divide and conquer
	//Works in place
	//Worst-case running time is 0(n^2) - unbalanced partitions i.e. sorted/reverse sorted array: T(n) = T(n-1)+0(n)
	//Average-case running time is 0(nlgn) - balanced partitions: T(n) = 2T(n/2)+0(n)
	//It has better constant factors than mergeSort
	//to make this random quickSort, select any element from array before calling partition and exchange it with the last element
	//Even better - select 3 random elements and exchange their median with the last element
	//The best case for minimum swaps is also one of the worst case for running time - when the array is already sorted. No swaps occur
	//The most swap occurs when n is even and input array is : n, n-2, n-4, ...4,2,1,3,5,...n-3,n-1. Number of swaps then is (n^2)/4 and
	//asymptotic running time is still the worst case. 0(n^2) 
	static void quickSort(int[] array, int startIndex, int endIndex){
		if (endIndex>startIndex){
			int pivotIndex = partition(array, startIndex, endIndex);
			quickSort(array,startIndex, pivotIndex-1);
			quickSort(array, pivotIndex+1, endIndex);
		}
	}
	
	//0(n)
	static int partition(int[] array, int startIndex, int endIndex){
		//assuming element at endIndex to be the pivot
		int lastLesserIndex = startIndex-1;
		for (int i = startIndex; i<endIndex; i++){
			if (array[i]<=array[endIndex]){
				lastLesserIndex++;
				//swap element at lastLesserIndex and element at i
				if ( array[lastLesserIndex]!=array[i]){
					int temp = array[lastLesserIndex];
					array[lastLesserIndex] = array[i];
					array[i]=temp; 
				}
			}
		}
		lastLesserIndex++;
		int temp=array[lastLesserIndex];
		array[lastLesserIndex]=array[endIndex];
		array[endIndex]= temp;
		return lastLesserIndex;
		
		
	}
	
	//binarySearch - array needs to be sorted
	//Worst-case: 0(lgn), Best-case: 0(1)
	//All cases- O(lgn)
	static int binarySearch(int[] array, int x){
		int start = 0;
		int end = array.length-1;
		

		while (start<=end){
			int mid = start + (end-start)/2; //to avoid overflow
			if (array[mid] == x){
				return mid;
			} else if (x<array[mid]){
				end =mid-1;
			} else{
				start = mid+1;
			}
			
		}
		return -1;
	
	}
	
	//recursive Binary Search
	static int recursiveBinarySearch(int[] array, int low, int high, int x){
		int  mid= low + (high-low)/2;
		if (low > high){
			return -1;
		}
		if (array[mid]==x){
			return mid;
		} else if (x<array[mid]){
			return recursiveBinarySearch(array,low,mid-1,x);
		} else{
			return recursiveBinarySearch(array,mid+1,high,x);
		}
	}
	
	//O(n), cant say 0(n) because in best case it takes 0(1) time
	static int linearSearch(int[] array, int x){
		int i = 0;
		int answer = -1;
		while(i <array.length){ //we can avoid this calculation which is happening multiple times(sentinelLinearSearch)
			if (array[i]==x){
				answer = i;
				break;
			}
		}
		return answer;
	}
	
	//better than linearSearch in terms of constants
	static int sentinelLinearSearch(int[] array, int x){
		int i=0;
		int last=array[array.length-1];
		array[array.length-1]=x;
		while(x!=array[i]){
			i++;
		}
		array[array.length-1]=last;
		if (i < array.length - 1 || last==x){
			return i;
		} else{
			return -1;
		}
		
	}
	
	//recursive linear search
	static int recursiveLinearSearch(int[] array, int indexToCheck, int x){
		if (indexToCheck>array.length-1){
			return -1;
		} 
		if (array[indexToCheck]==x){
			return indexToCheck;
		} else{
			return recursiveLinearSearch(array, indexToCheck+1, x);
		}
		
	}
	
	//Counting sort 
	//assuming range of values is from 0 to m-1
	//if values were real numbers with fractional parts, we couldn't have used this sort
	//Running time - 0(n) 
	//It is a stable sort
	static int[] countingSort(int input[], int m){
		int answer[] = new int[input.length];
		int freq[] = new int[m];
		int next[] = new int[m];
		Arrays.fill(freq, 0);
		
		//count freq
		for (int value : input){
			freq[value]++;
		}
		
		//number of elements lesser than the value
		for (int i=0; i<m;i++){
			if (i==0){
				next[i]=0;
			} else{
				next[i] = next[i-1]+freq[i-1];
			}
			
		}
		
		//filling up answer array
		for (int i=0; i<input.length; i++){
			answer[next[input[i]]]=input[i];
			next[input[i]]++;
		}
		
		return answer;
		
	}

}
