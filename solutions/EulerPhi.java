package solutions;

/**
 * @verified https://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=NTL_1_D&lang=jp
 */

public class EulerPhi {
	/**
	 * phi_0 = n
	 * phi_1 = phi_0 - phi_0/p1 = phi_0(1-1/p1) = n(1-1/p1)
	 * phi_2 = phi_1 - phi_1/p2 = phi_1(1-1/p2) = n(1-1/p1)(1-1/p2)
	 * ...
	 * phi = n\prod_{i=1}^{e} (1-1/phi_i)
	 */
	public static int phi(int n){
		int ret = n;
		for(int i = 2; i*i <= n; i++){
			if(n % i == 0){
				ret -= ret / i;
				while(n % i == 0) n /= i;
			}
		}
		if(n > 1) ret -= ret / n;
		return ret;
	}

	public static int[] table(int n){
		int[] euler = new int[n+1];	
		for(int i = 0; i <= n; i++) euler[i] = i;

		for(int i = 2; i <= n; i++){
			if(euler[i] == i){
				for(int j = i; j <= n; j += i){
					euler[j] = euler[j] / i * (i-1);
				}
			}
		}
		return euler;
	}
}

