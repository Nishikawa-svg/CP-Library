package functions;

import java.util.Arrays;

public class LIS {
	static final long INFL = 1L<<60;
	public static int solve(long[] arr, boolean narrow){
		int n = arr.length;
		Long[] dp = new Long[n];	
		Arrays.fill(dp,INFL);
		for(int i = 0; i < n; i++){
			int pos;
			if(narrow) pos = ~Arrays.binarySearch(dp, arr[i], (x,y) -> x >= y ? 1 : -1);
			else pos = ~Arrays.binarySearch(dp, arr[i], (x,y) -> x > y ? 1 : -1);
			dp[pos] = arr[i];
		}
		return  ~Arrays.binarySearch(dp, INFL, (x,y) -> x >= y ? 1 : -1);
	}

	public static int[] solveAll(long[] arr, boolean narrow){
		int n = arr.length;
		Long[] dp = new Long[n];	
		int[] ret = new int[n];
		Arrays.fill(dp,INFL);
		for(int i = 0; i < n; i++){
			int pos;
			if(narrow) pos = ~Arrays.binarySearch(dp, arr[i], (x,y) -> x >= y ? 1 : -1);
			else pos = ~Arrays.binarySearch(dp, arr[i], (x,y) -> x > y ? 1 : -1);
			dp[pos] = arr[i];
			ret[i] = ~Arrays.binarySearch(dp, INFL, (x,y) -> x >= y ? 1 : -1);
		}
		return ret;
	}
}
