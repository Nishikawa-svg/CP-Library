package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;

public class TopologicalSort {
	int n;
	List<List<Integer>> g;
	int[] deg;
	int[] order;
	public TopologicalSort(int n){
		this.n = n;
		g = new ArrayList<>();
		for(int i = 0; i < n; i++) g.add(new ArrayList<>());
		deg = new int[n];
	}
	public void addEdge(int a, int b){
		g.get(a).add(b);
		deg[b]++;
	}
	public int[] build(){
		order = new int[n];
		int pos = 0;
		Queue<Integer> que = new ArrayDeque<>();
		for(int i = 0; i < n; i++) if(deg[i] == 0) que.add(i);
		while(!que.isEmpty()){
			int v = que.poll();
			order[pos++] = v;
			for(int nv : g.get(v)){
				deg[nv]--;
				if(deg[nv] == 0) que.add(nv);
			}
		}
		return order;
	}
}