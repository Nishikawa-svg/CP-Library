package classes;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	class Edge {
		int to;
		long cost;
	 	Edge(int to, long cost){
			this.to = to;
			this.cost = cost;
		}
	}
	List<List<Edge>> g;
	int size;
	long[] dist;
	public Tree(int n){
		this.size = n;
		g = new ArrayList<>();
		for(int i = 0; i < n; i++) g.add(new ArrayList<>());
	}

	public void addEdge(int a, int b, long c){
		g.get(a).add(new Edge(b, c));
	}
	
	private void dfs_getDist(int v, int pv){
		for(Edge e : g.get(v)) if(e.to != pv){
			dist[e.to] = dist[v] + e.cost;
			dfs_getDist(e.to, v);
		}
	}

	public long[] getDist(int from){
		dist = new long[size];
		for(int i = 0; i < size; i++) dist[i] = -1;
		dist[from] = 0;
		dfs_getDist(from, -1);
		return dist;
	}

	class Path {
		int from, to;
		long dist;
		Path(int from, int to, long dist){
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}

	public Path getDiameter(){
		long[] dist1 = getDist(0);
		int from = 0;
		long maxDist = 0;
		for(int i = 0; i < size; i++){
			if(dist1[i] > maxDist){
				from = i;
				maxDist = dist1[i];
			}
		}
		long[] dist2 = getDist(from);
		int to = from;
		long diameter = 0;
		for(int i = 0; i < size; i++){
			if(dist2[i] > diameter){
				to = i;
				diameter = dist2[i];
			}
		}
		return new Path(from, to, diameter);
	}

}
