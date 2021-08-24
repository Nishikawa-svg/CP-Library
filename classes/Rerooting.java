package classes;

import java.util.List;
import java.util.ArrayList;

public class Rerooting {
	List<List<Integer>> ug;	
	int n;
	int[][] dp;
	int[] ans;
	int identity;
	public Rerooting(int n, int identity){
		this.n = n;
		this.identity = identity;
		ug = new ArrayList<>();
		for(int i = 0; i < n; i++) ug.add(new ArrayList<>());
	}

	public void addEdge(int from, int to){
		ug.get(from).add(to);
	}

	public int[] build(){
		dp = new int[n][];
		dfs1(0,-1);
		ans = new int[n];
		dfs2(0,-1,0);
		return ans;
	}

	private int dfs1(int v, int pv){
		int deg = ug.get(v).size();
		dp[v] = new int[deg];
		int cum = identity;
		for(int i = 0; i < deg; i++){
			int nv = ug.get(v).get(i);
			if(nv == pv) continue;
			dp[v][i] = dfs1(nv, v);
			cum = merge(cum, dp[v][i]);
		}
		return addRoot(cum);
	}

	private int merge(int a, int b){
		return Math.max(a, b);
	}

	private int addRoot(int x){
		return x + 1;
	}

	private void dfs2(int v, int pv, int dp_pre){
		int deg = ug.get(v).size();
		for(int i = 0; i < deg; i++){
			if(ug.get(v).get(i) == pv) dp[v][i] = dp_pre;
		}

		int[] dpl = new int[deg+1];
		int[] dpr = new int[deg+1];
		for(int i = 0; i < deg+1; i++) dpl[i] = dpr[i] = identity;
		for(int i = 0; i < deg; i++) dpl[i+1] = merge(dpl[i], dp[v][i]);
		for(int i = deg-1; i >= 0; i--) dpr[i] = merge(dpr[i+1], dp[v][i]);

		ans[v] = addRoot(dpl[deg]);

		for(int i = 0; i < deg; i++){
			int nv = ug.get(v).get(i);
			if(nv == pv) continue;
			dfs2(nv, v, addRoot(merge(dpl[i], dpr[i+1])));
		}
	}
}
