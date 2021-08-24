package classes;

import java.util.ArrayList;
import java.util.List;

public class LCA {
	List<List<Integer>> ug;
	int[][] par;
	int[] dist;
	int n;
	int k;
	int root;
	public LCA(int n){
		this.n = n;
		ug = new ArrayList<>();
		for(int i = 0; i < n; i++) ug.add(new ArrayList<>());
	}

	public void addEdge(int a, int b){
		ug.get(a).add(b);
	}

	public void build(int root){
		this.root = root;
		dist = new int[n];
		k = 1;
		while((1<<k) < n) k++;
		par = new int[k][n];
		for(int i = 0; i < k; i++) for(int j = 0; j < n; j++)	par[i][j] = -1;
		dfs(root,-1,0);
		for(int i = 0; i < k-1; i++){
			for(int j = 0; j < n; j++){
				if(par[i][j] < 0) par[i+1][j] = -1;
				else par[i+1][j] = par[i][par[i][j]];
			}
		}
	}

	private void dfs(int v, int pv, int d){
		par[0][v] = pv;
		dist[v] = d;
		for(int nv : ug.get(v)) if(nv != pv) dfs(nv, v, d+1);
	}

	public int getLCA(int a, int b){
		if(dist[a] < dist[b])	{
			int t = a; a = b; b = t;
		}
		int dif = dist[a] - dist[b];
		for(int i = 0; i < k; i++) if((dif>>i & 1) == 1) a = par[i][a];
		if(a == b) return a;
		for(int i = k-1; i >= 0; i--){
			if(par[i][a] != par[i][b]){
				a = par[i][a];
				b = par[i][b];
			}
		}
		return par[0][a];
	}

	public int dist(int a, int b){
		return dist[a] + dist[b] - 2*dist[getLCA(a,b)];
	}
}
