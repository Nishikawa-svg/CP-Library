package solutions;

import java.util.Arrays;

public class TSP {
	private static final long INFL = 1L<<59;
	private static final int INF = 1<<29;

	public static long[][] solve(int n, long[][] dist, int start){
		long[][] dp = new long[1<<n][n];
		for(int i = 0; i < 1<<n; i++) Arrays.fill(dp[i], INFL);
		dp[0][start] = 0;
		for(int s = 0; s < 1<<n; s++){
			for(int i = 0; i < n; i++){
				if(s != 0 && (s & 1<<i) == 0) continue;
				for(int j = 0; j < n; j++){
					if((s & 1<<j) != 0) continue;
					dp[s|1<<j][j] = Math.min(dp[s|1<<j][j], dp[s][i] + dist[i][j]);
				}
			}
		}
		return dp;
	}

	public static int[][] solve(int n, int[][] dist, int start){
		int[][] dp = new int[1<<n][n];
		for(int i = 0; i < 1<<n; i++) Arrays.fill(dp[i], INF);
		dp[0][start] = 0;
		for(int s = 0; s < 1<<n; s++){
			for(int i = 0; i < n; i++){
				if(s != 0 && (s & 1<<i) == 0) continue;
				for(int j = 0; j < n; j++){
					if((s & 1<<j) != 0) continue;
					dp[s|1<<j][j] = Math.min(dp[s|1<<j][j], dp[s][i] + dist[i][j]);
				}
			}
		}
		return dp;
	}
}
