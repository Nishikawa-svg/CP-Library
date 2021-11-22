package classes;

public class Mod {
	private static int MOD = 1000000007;
	private static long[] fac, finv, inv;
	private static int combSize;

	static void setMod(int mod){
		MOD = mod;
	}
	static int mod(){
		return MOD;
	}
	static long pow(long p, long n){
		p %= MOD;
		long ret = 1;
		while(n > 0){
			if((n & 1) == 1) ret = ret * p % MOD;
			p = p * p % MOD;
			n >>= 1;
		}
		return ret;
	}
	static long factorial(int n){
		long ret = 1;
		for(int i = 2; i <= n; i++) ret = ret * i % MOD;
		return ret;
	}
	static long inverse(long a){
		long b = MOD, u = 1, v = 0;
		while(b > 0){
			long t = a / b;
			a -= t * b;
			long tmp1 = a; a = b; b = tmp1;
			u -= t * v;
			long tmp2 = u; u = v; v = tmp2;
		}
		u %= MOD;
		if(u < 0) u += MOD;
		return u;
	}
	static void initTable(int n){
		combSize = n+1;
		fac = new long[combSize];
		finv = new long[combSize];
		inv = new long[combSize];
		fac[0] = fac[1] = 1;
		finv[0] = finv[1] = 1;
		inv[1] = 1;
		for(int i = 2; i < combSize; i++){
			fac[i] = fac[i-1] * i % MOD;
			inv[i] = MOD - inv[MOD%i] * (MOD / i) % MOD;
			finv[i] = finv[i-1] * inv[i] % MOD;
		}
	}
	static long comb(int n, int k){
		if(n < k) return 0;
		if(n < 0 || k < 0) return 0;
		return fac[n] * (finv[k] * finv[n-k] % MOD) % MOD;
	}
	static long perm(int n, int k){
		if(n < k) return 0;
		if(n < 0 || k < 0) return 0;
		return fac[n] * finv[n-k] % MOD;
	}
	static long homo(int n, int r){
		if(n < 0 || r < 0) return 0;
		return comb(n+r-1, r);
	}
}