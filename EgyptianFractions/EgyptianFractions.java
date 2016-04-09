import java.util.Scanner;


public class EgyptianFractions {

	static int max = 0;
	public static void main (String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.print("Numerator: ");
		int numerator = scan.nextInt();
		System.out.print("Denominator: ");
		int denominator = scan.nextInt();
		scan.close();
		getEgyptianFractions(numerator,denominator);

		
	}
	
	private static void getEgyptianFractions(int numerator, int denominator){
		if (numerator*denominator < -1){
			System.out.println("Cannot proceed for a negative fraction");
			return;
		} 
		if (numerator == 0 || denominator ==0){
			System.out.println("Cannot proceed if either numerator or denominator is 0");
			return;
		}
		
		if (numerator <0 && denominator <0){
			numerator = numerator * -1;
			denominator = denominator * -1;
		}  
		
		if (numerator%denominator==0){
			System.out.println(numerator/denominator);
			return;
		} 
		if (denominator%numerator==0){
			
			System.out.println("1/"+ denominator/numerator);
			return;
		}
		
		if (numerator>denominator){
			System.out.println(numerator/denominator);
			getEgyptianFractions(numerator%denominator, denominator);
			return;
		}
		
		
		int nextDenominator = denominator/numerator + 1;
		nextDenominator = checkForBetterDenominator(numerator, denominator, nextDenominator);
		while(nextDenominator <= max){
			nextDenominator = checkForBetterDenominator(numerator, denominator, nextDenominator+1);
		}
	
		max=nextDenominator;
		System.out.println("1/"+nextDenominator);
		int remainingNumerator = numerator*nextDenominator-denominator;
		int remainingDenominator = denominator*nextDenominator;
		int gcd  = gcd(remainingNumerator, remainingDenominator);
		remainingNumerator = remainingNumerator/gcd;
		remainingDenominator = remainingDenominator/gcd;
		getEgyptianFractions(remainingNumerator, remainingDenominator);
	}
	
	static int checkForBetterDenominator(int numerator, int denominator, int nextDenominator){
		int remainingNumerator = numerator*nextDenominator-denominator;
		int remainingDenominator = denominator*nextDenominator;
		while (gcd(remainingNumerator, remainingDenominator) == 1){
			if (remainingNumerator == 1){
				break;
			} else{
				nextDenominator++;
				remainingNumerator = numerator*nextDenominator-denominator;
				remainingDenominator = denominator*nextDenominator;
			}
		}
		
		return nextDenominator;
	}
	static int gcd (int a, int b){
		if (b == 0){
			return a;
		} else return gcd(b, a%b);
	}
}
