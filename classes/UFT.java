package classes;

public class UFT {
	int[] par;
	int[] rank;
	int[] size;
	int n;
	int groups;
	public UFT(int n){
		this.n = n;
		groups = n;
		par = new int[n];
		rank = new int[n];
		size = new int[n];
		for(int i = 0; i < n; i++){
			par[i] = i;
			rank[i] = 0;
			size[i] = 1;
		}
	}
	public int root(int x){
		if(par[x] == x) return x;
		else return par[x] = root(par[x]);
	}
	public boolean merge(int x, int y){
		x = root(x);
		y = root(y);
		if(x == y) return false;
		if(rank[x] < rank[y]){
			par[x] = y;
			size[y] += size[x];
			size[x] = 0;
		} else {
			par[y] = x;
			size[x] += size[y];
			size[y] = 0;
			if(rank[x] == rank[y]) rank[x]++;
		}
		groups--;
		return true;
	}
	public boolean isSame(int x, int y){
		return root(x) == root(y);
	}
	public boolean isConnected(){
		int s = root(0);
		if(s == n) return true;
		else return false;
	}
	public int connectedComponents(){
		return groups;
	}
	public int groupSize(int i){
		return size[root(i)];
	}
}
