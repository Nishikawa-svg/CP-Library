package classes;

public class LazySegmentTree {
	public long[] dat, lazy;
	public int n;
	public long ex, em;
	public LazySegmentTree(int size, long ex, long em){
		this.ex = ex;
		this.em = em;
		n = 1;
		while(size > n) n *= 2;
		dat = new long[2*n-1];
		lazy = new long[2*n-1];
		for(int i = 0; i < 2*n-1; i++){
			dat[i] = ex;
			lazy[i] = em;
		}
	}
	public LazySegmentTree(long[] arr, long ex, long em){
		this.ex = ex;
		this.em = em;
		int size = arr.length;
		n = 1;
		while(size > n) n *= 2;
		dat = new long[2*n-1];
		lazy = new long[2*n-1];
		for(int i = 0; i < size; i++) dat[i+n-1] = arr[i];
		for(int i = size; i < n; i++) dat[i+n-1] = ex;
		for(int i = n-2; i >= 0; i--) dat[i] = fx(dat[i*2+1], dat[i*2+2]);
		for(int i = 0; i < 2*n-1; i++) lazy[i] = em;
	}
	private long fx(long x, long y){
		return Math.min(x, y);
	}
	private long fm(long x, long y){
		return y;
	}
	private long fa(long x, long y){
		return y;
	}
	private long fp(long x, long len){
		return x;
	}
	private void eval(int k, int l, int r){
		if(lazy[k] == em) return;
		if(k < n-1){
			lazy[k*2+1] = fm(lazy[k*2+1], lazy[k]);
			lazy[k*2+2] = fm(lazy[k*2+2], lazy[k]);
		}
		dat[k] = fa(dat[k], fp(lazy[k],r-l));
		lazy[k] = em;
	}
	public void evalAll(){
		_evalAll(0,0,n);
	}
	private void _evalAll(int k, int l, int r){
		eval(k,l,r);
		if(k < n-1){
			_evalAll(k*2+1, l, (l+r)/2);
			_evalAll(k*2+2, (l+r)/2, r);
		}
	}
	public void update(int a, int b, long x){
		_update(a,b,x,0,0,n);
	}
	private void _update(int a, int b, long x, int k, int l, int r){
		eval(k,l,r);
		if(a <= l && r <= b){
			lazy[k] = fm(lazy[k], x);
			eval(k,l,r);
		} else if(a < r && l < b){
			_update(a, b, x, k*2+1, l, (l+r)/2);
			_update(a, b, x, k*2+2, (l+r)/2, r);
			dat[k] = fx(dat[k*2+1], dat[k*2+2]);
		}
	}
	public long query(int a, int b){
		return _query(a, b, 0, 0, n);
	}
	private long _query(int a, int b, int k, int l, int r){
		eval(k,l,r);
		if(r <= a || b <= l) return ex;
		else if(a <= l && r <= b) return dat[k];
		else {
			long vl = _query(a, b, k*2+1, l, (l+r)/2);
			long vr = _query(a, b, k*2+2, (l+r)/2, r);
			return fx(vl, vr);
		}
	}
	public long get(int i){
		return dat[n-1+i];
	}
}
