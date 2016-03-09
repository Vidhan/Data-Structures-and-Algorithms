import java.util.HashMap;

public class SumOf2ElementsIsK {
	
	public static void main(String args[]){
		int[] array={1,4,2,6,4,8,3,5,4,0};
		int k = 8;
		sumOf2ElementsIsK(array, k);
	}
	
	static void sumOf2ElementsIsK(int[] array, int k){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int element:array){
			if (map.containsKey(element)){
				System.out.println(element +", "+map.get(element));
			} else{
				map.put(k-element,element);
			}
		}
		
	}

}
