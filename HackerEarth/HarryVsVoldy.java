import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class HarryVsVoldy {
    public static void main(String args[] ) throws Exception {
       

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
        	int size = Integer.parseInt(br.readLine());
        	StringTokenizer str = new StringTokenizer(br.readLine());
        	long input[] = new long[size+1];
        	long dp[] = new long[size+1];
        	int j= 0;
        	input[0]=0;
        	dp[0]=0;
        	
        	while (str.hasMoreTokens()){
        		input[++j]=Long.parseLong(str.nextToken());
        	}
        	dp[1]=Math.max(0,input[1]);
        	for (int k = 2; k<dp.length;k++){
        		dp[k] = Math.max(input[k]+dp[k-2],dp[k-1]);
        	}
        	
        	if (dp[size] == 0){
        		System.out.println("DANGER");
        	} else{
        		System.out.println(dp[size]);
        	}
        	
        	
        }
        

        
    }
}

