import java.util.Arrays;

public class Collatz {

	public static void main(String[] args) {
		longestChain(1000000L);
	}
	
	static void longestChain(long val){
		long maximumValue = val;
		int counts[]= new int[(int)maximumValue];
		Arrays.fill(counts, 0);
		int answerNumber = 1;
		int answerLength = 1;
		for (int i=1; i<=maximumValue; i++){
			if (i==1){
				counts[i-1]=1;
			} else{
				long nextNumberInSequence = i;
				int count = 0;
				while (nextNumberInSequence!=1){
					count++;
					nextNumberInSequence = nextNumberInSequence(nextNumberInSequence);	
					if (nextNumberInSequence>maximumValue) continue;
					if (counts[(int)nextNumberInSequence-1] !=0){
						break;
					}
				}
				counts[i-1]=count + counts[(int)nextNumberInSequence-1];
			}
			if (counts[i-1]>answerLength){
				answerLength=counts[i-1];
				answerNumber=i;
			}
		}
		System.out.println("" + answerNumber +" has the largest chain with a length of "+ answerLength);
	}
	
	static long nextNumberInSequence(long n){
		if (n%2==0){
			return n/2;
		} else return 3*n+1;
	}

}
