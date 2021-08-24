package classes;

public class WUFT {
	int[] par;
	int[] rank;
	long[] weight;
	int n;	
	long e;
	public WUFT(int n, long e){
		this.n = n;
		this.e = e;
		par = new int[n];
		rank = new int[n];
		weight = new long[n];
		for(int i = 0; i < n; i++) {
			par[i] = i;
			weight[i] = e;
		}
	}

	public int root(int x){
		if(par[x] == x) return x;
		else {
			int r = root(par[x]);
			weight[x] += weight[par[x]];
			return par[x] = r;
		}
	}

	public long getWeight(int x){
		root(x);
		return weight[x];
	}

	public boolean isSame(int x, int y){
		return root(x) == root(y);
	}

	public boolean merge(int x, int y, long w){
		w += getWeight(x);
		w -= getWeight(y);
		x = root(x);
		y = root(y);
		if(x == y) return false;
		if(rank[x] < rank[y]){
			par[x] = y;
			weight[x] = -w;
		} else {
			par[y] = x;
			weight[y] = w;
			if(rank[x] == rank[y]) rank[x]++;
		}
		return true;
	}

	public long diff(int x, int y){
		return getWeight(y) - getWeight(x);
	}
}
