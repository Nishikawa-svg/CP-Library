package classes;

public class SegmentTree {
	public int n;
	public long[] dat;
	public long e;
	public SegmentTree(int size, long e){
		this.e = e;
		n = 1;
		while(size > n)	n *= 2;
		dat = new long[2*n-1];
		for(int i = 0; i < 2*n-1; i++) dat[i] = e;
	}
	public SegmentTree(long[] arr, long e){
		this.e = e;
		int size = arr.length;
		n = 1;
		while(size > n) n *= 2;
		dat = new long[2*n-1];
		for(int i = 0; i < size; i++) dat[n-1+i] = arr[i];
		for(int i = size; i < n; i++) dat[n-1+i] = e;
		for(int i = n-2; i >= 0; i--) dat[i] = fx(dat[i*2+1], dat[i*2+2]);
	}
	public SegmentTree(int[] arr, int e){
		this.e = e;
		int size = arr.length;
		n = 1;
		while(size > n) n *= 2;
		dat = new long[2*n-1];
		for(int i = 0; i < size; i++) dat[n-1+i] = arr[i];
		for(int i = size; i < n; i++) dat[n-1+i] = e;
		for(int i = n-2; i >= 0; i--) dat[i] = fx(dat[i*2+1],dat[i*2+2]);
	}
	private long fx(long x, long y){
		return Math.min(x, y);
	}
	private long fa(long x, long y){
		return y;
	}
	public void update(int i, long x){
		i += n-1;
		dat[i] = x;
		dat[i] = fa(dat[i], x);
		while(i > 0){
			i = (i-1) / 2;
			dat[i] = fx(dat[i*2+1], dat[i*2+2]);
		}
	}
	public long query(int a, int b){
		return _query(a, b, 0, 0, n);
	}
	private long _query(int a, int b, int k, int l, int r){
		if(r <= a || b <= l) return e;
		else if(a <= l && r <= b) return dat[k];
		else {
			long vl = _query(a, b, k*2+1, l, (l+r)/2);
			long vr = _query(a, b, k*2+2, (l+r)/2, r);
			return fx(vl, vr);
		 }
	}
	public int findRightest(int a, int b, int x){
		return _findRightest(a, b, x, 0, 0, n);
	}
	public int findLeftest(int a, int b, int x){
		return _findLeftest(a, b, x, 0, 0, n);
	}
	private int _findRightest(int a, int b, int x, int k, int l, int r){
		if(dat[k] > x || r <= a || b <= l){
			return a-1;
		} else if(k >= n-1){
			return k-n+1;
		} else {
			int vr = _findRightest(a, b, x, 2*k+2, (l+r)/2, r);
			if(vr != a-1) return vr;
			else return _findRightest(a, b, x, 2*k+1, l, (l+r)/2);
		}
	}
	private int _findLeftest(int a, int b, int x, int k, int l, int r){
		if(dat[k] > x || r <= a || b <= l){
			return b;
		} else if(k > n-1){
			return k-n+1;
		} else {
			int vl = _findLeftest(a, b, x, 2*k+1, l, (l+r)/2);
			if(vl != b) return vl;
			else return _findLeftest(a, b, x, 2*k+2, (l+r)/2, r);
		}
	}
	public long get(int i){
		return dat[n-1+i];
	}
}
