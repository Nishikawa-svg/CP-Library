package classes;

import java.util.ArrayList;
import java.util.List;

public class UnWeightedGraph {
	List<List<Integer>> ug;
	int n;
	int[] dist;
	public UnWeightedGraph(int n){
		this.n = n;
		ug = new ArrayList<>();
		for(int i = 0; i < n; i++) ug.add(new ArrayList<>());
	}

	public void addEdge(int from, int to){
		ug.get(from).add(to);
	}

	private void dfs_getDist(int v, int pv){
		for(int nv : ug.get(v)) if(nv != pv){
			dist[nv] = dist[v] + 1;
			dfs_getDist(nv, v);
		}
	}
	public int[] getDist(int from){
		dist = new int[n];
		for(int i = 0; i < n; i++) dist[i] = -1;
		dist[from] = 0;
		dfs_getDist(from, -1);
		return dist;
	} 

	class Path {
		int from, to, dist;
		public Path(int from , int to, int dist){
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}

	public Path treeDiameter(){
		int[] dist1 = getDist(0);
		int from = 0;
		int maxDist = 0;
		for(int i = 0; i < n; i++){
			if(dist1[i] > maxDist){
				from = i; 
				maxDist = dist1[i];
			}
		}
		int[] dist2 = getDist(from);
		int to = 0;
		int diameter = 0;
		for(int i = 0; i < n; i++){
			if(dist2[i] > diameter){
				to = i;
				diameter = dist2[i];
			}
		}
		return new Path(from, to, diameter);
	}

}
