package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;

public class Graph {
	class Edge {
		int from, to;
		long w;
		Edge(int from, int to, long w){
			this.from = from;
			this.to = to;
			this.w = w;
		}
	}
	class Node {
		int v;
		long cost;
		Node(int v, long cost){
			this.v = v;
			this.cost = cost;
		}
	}
	class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2){
			if(n1.cost > n2.cost) return 1;
			else if(n1.cost < n2.cost) return -1;
			else return 0;
		}
	}
	List<List<Edge>> wg;
	int n;
	List<Edge> edges;
	boolean negative;
	static long INFL = 1L<<60;
	public Graph(int n){
		this.n = n;
		wg = new ArrayList<>();
		for(int i = 0; i < n; i++) wg.add(new ArrayList<>());
		edges = new ArrayList<>();
	}
	public void addEdge(int from, int to, long cost){
		wg.get(from).add(new Edge(from, to, cost));
		edges.add(new Edge(from, to, cost));
	}
	public long[] Dijkstra(int from){
		long[] dist = new long[n];
		for(int i = 0; i < n; i++) dist[i] = INFL;
		Queue<Node> pq = new PriorityQueue<>(new NodeComparator());
		pq.add(new Node(from, 0));
		dist[from] = 0;
		while(!pq.isEmpty()){
			Node node = pq.poll();
			int v = node.v;
			if(dist[v] < node.cost) continue;
			for(Edge e : wg.get(v)){
				int nv = e.to;
				if(dist[nv] > dist[v] + e.w){
					dist[nv] = dist[v] + e.w;
					pq.add(new Node(nv, dist[v] + e.w));
				}
			}
		}
		return dist;
	}
	public long[] BellmanFord(int from){
		long[] dist = new long[n];
		Arrays.fill(dist, INFL);
		dist[from] = 0;
		negative = false;
		for(int i = 0; i < n; i++){
			for(Edge e : edges){
				if(dist[e.from] != INFL && dist[e.to] > dist[e.from] + e.w){
					dist[e.to] = dist[e.from] + e.w;
					if(i == n-1) negative = true;
				}
			}
		}
		return dist;
	}
	public boolean existNegativeCycle(){
		return this.negative;
	}
	public long[][] WarshallFloyd(){
		long[][] dist = new long[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i == j) dist[i][j] = 0;
				else dist[i][j] = INFL;
			}
		}
		for(Edge e : edges){
			dist[e.from][e.to] = e.w;
		}
		for(int k = 0; k < n; k++){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(dist[i][k] != INFL && dist[k][j] != INFL){
						dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
					}
				}
			}
		}
		return dist;
	}
}
