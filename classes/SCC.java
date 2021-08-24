package classes;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class SCC {
	int n;
	List<List<Integer>> g, rg, dag;
	boolean[] used;
	int[] postOrder;
	int[] comp;
	int idx;
	int groups;

	class Edge {
		int from, to;
		Edge(int from, int to){
			this.from = from;
			this.to = to;	
		}
		@Override
		public boolean equals(Object o){
			if(this == o) return true;
			if(!(o instanceof Edge)) return false;
			Edge e = (Edge)o;
			if(this.from == e.from && this.to == e.to) return true;
			else return false;
		}
		@Override
		public int hashCode(){
			int hash = 7;
			int prime = 31;
			hash = hash*prime + this.from;
			hash = hash*prime + this.to;
			return hash;
		}
	}

	public SCC(int n){
		this.n = n;
		g = new ArrayList<>();
		for(int i = 0; i < n; i++) g.add(new ArrayList<>());
		rg = new ArrayList<>();
		for(int i = 0; i < n; i++) rg.add(new ArrayList<>());
	}

	public void addEdge(int a, int b){
		g.get(a).add(b);
		rg.get(b).add(a);
	}

	/**
	 * @return A graph that contracts each strongly connected component.
	 * 				 (Multiple edges are removed)
	 */
	public List<List<Integer>> build(){
		used = new boolean[n];
		postOrder = new int[n];
		idx = 0;
		for(int i = 0; i < n; i++) if(!used[i]) dfs(i);
		used = new boolean[n];
		comp = new int[n];
		int k = 0;
		for(int i = n-1; i >= 0; i--){
			int v = postOrder[i];
			if(!used[v]){
				rdfs(v,k);
				k++;
			}
		}
		groups = k;
		dag = new ArrayList<>();
		Set<Edge> set = new HashSet<>();
		for(int i = 0; i < groups; i++) dag.add(new ArrayList<>());
		for(int i = 0; i < n; i++){
			for(int j : g.get(i)){
				int v = comp[i], nv = comp[j];
				Edge e = new Edge(v, nv);
				if(v == nv || set.contains(e)) continue; 
				set.add(e);
				dag.get(v).add(nv);
			}
		}
		return dag;
	}

	private void dfs(int v){
		used[v] = true;
		for(int nv : g.get(v)){
			if(!used[nv]) dfs(nv);
		}
		postOrder[idx++] = v;
	}

	private void rdfs(int v, int k){
		used[v] = true;
		comp[v] = k;
		for(int nv : rg.get(v)) if(!used[nv]) rdfs(nv,k);
	}

	public boolean same(int a, int b){
		return comp[a] == comp[b];
	}

	/**
	 * @return HashMap of (groupId, number of elements)
	 */
	public Map<Integer,Integer> groups(){
		Map<Integer,Integer> groupComp = new HashMap<>();
		for(int i = 0; i < n; i++){
			int g = comp[i];
			if(groupComp.containsKey(g)) groupComp.put(g,groupComp.get(g)+1);
			else groupComp.put(g,1);
		}
		return groupComp;
	}

	/**
	 * @return Number of vertex pairs that can go back and forth
	 * 				 between each other in both directions.
	 */
	public long reachablePairs(){
		long[] cnt = new long[n];
		for(int i = 0; i < n; i++){
			cnt[comp[i]]++;
		}
		long ret = 0;
		for(int i = 0; i < n; i++){
			ret += cnt[i]*(cnt[i]-1) / 2;
		}
		return ret;
	}
}