package classes;

import java.util.List;
import java.util.ArrayList;

public class EulerTour {
	int n;
	List<List<Integer>> g;	
	int[] ord;
	int[] pre;
	int[] in, out;
	int idx;
	SegmentTree seg;
	public EulerTour(int n){
		this.n = n;
		g = new ArrayList<>();
		for(int i = 0; i < n; i++) g.add(new ArrayList<>());
	}
	public void addEdge(int a, int b){
		g.get(a).add(b);
	}
	public void build(){
		ord = new int[2*n-1];
		pre = new int[n];
		idx = 0;	
		dfs(0,-1);
		seg = new SegmentTree(ord,1<<30);
	}
	private void dfs(int v, int pv){
		pre[v] = idx;
		ord[idx++] = v;
		for(int nv : g.get(v)) if(nv != pv){
			dfs(nv,v);
			ord[idx++] = v;
		}
	}
	public int getLCA(int a, int b){
		if(pre[a] > pre[b]) return (int)seg.query(pre[a],pre[b]+1);
		else return (int)seg.query(pre[b],pre[a]+1);
	}
}

