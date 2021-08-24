package classes;

import java.util.List;
import java.util.ArrayList;

public class WeightedRerooting {
	class Edge {
		int to;
		long cost;
		Edge(int to, long cost){
			this.to = to;
			this.cost = cost;
		}
	}
	List<List<Edge>> wg;
	int n;
	long[][] dp;
	long[] ans;
	long identity;
	public WeightedRerooting(int n, int identity){
		this.n = n;
		this.identity = identity;
		wg = new ArrayList<>();
		for(int i = 0; i < n; i++) wg.add(new ArrayList<>());
	}

	public void addEdge(int from, int to, long cost){
		wg.get(from).add(new Edge(to, cost));
	}

	public long[] build(){
		dp = new long[n][];
		dfs1(0,-1);
		ans = new long[n];
		dfs2(0,-1,0);
		return ans;
	}

	private long dfs1(int v, int pv){
		int deg = wg.get(v).size();
		dp[v] = new long[deg];
		long cum = 0;
		for(int i = 0; i < deg; i++){
			Edge e = wg.get(v).get(i);
			if(e.to == pv) continue;
			dp[v][i] = dfs1(e.to,v);
			cum = merge(cum, dp[v][i] + e.cost);
		}
		return cum;
	}

	private long merge(long a, long b){
		return Math.max(a, b);
	}

	private void dfs2(int v, int pv, long dp_pre){
		int deg = wg.get(v).size();
		for(int i = 0; i < deg; i++){
			if(wg.get(v).get(i).to == pv){
				dp[v][i] = dp_pre;
			}
		}
		long[] dpl = new long[deg+1];
		long[] dpr = new long[deg+1];
		for(int i = 0; i < deg+1; i++) dpl[i] = dpr[i] = 0;
		for(int i = 0; i < deg; i++){
		 	dpl[i+1] = merge(dpl[i], dp[v][i] + wg.get(v).get(i).cost);
		}
		for(int i = deg-1; i >= 0; i--){
		 	dpr[i] = merge(dpr[i+1], dp[v][i] + wg.get(v).get(i).cost);
		}
		ans[v] = dpl[deg];

		for(int i = 0; i < deg; i++){
			Edge e = wg.get(v).get(i);
			if(e.to == pv) continue;
			dfs2(e.to, v, merge(dpl[i], dpr[i+1]));
		}
	}

	public long treeDiameter(){
		long ret = 0;
		for(int i = 0; i < n; i++) ret = Math.max(ret, ans[i]);
		return ret;
	}
}
