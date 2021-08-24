package functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Arrays;

public class NumberTheory {

	/**
	 * @return Nth power of P
	 * O(log(N))
	 */
	public static long pow(long p, long n){
		long ret = 1;
		while(n > 0){
			if((n & 1) == 1) ret *= p;
			p *= p;
			n >>= 1;
		}
		return ret;
	}

	/**
	 * @return Factorial value of N!
	 * O(N)
	 */
	public static long factorial(int n){
		long ret = 1;
		for(int i = 2; i <= n; i++) ret *= i;
		return ret;
	}

	/**
	 * @return Binominal coefficient
	 * O(k)
	 */
	public static long binomial(int n, int k){
		if(k < 0 || n < k) return 0;
		long ret = 1;
		for(long i = 1; i <= k; i++){
			ret *= n--;
			ret /= i;
		}
		return ret;
	}

	/**
	 * @return Matrix of binomial coefficient
	 * O(N^2)
	 * Binominal coefficient table generated from Pascal's triangle
	 */
	public static long[][] binomialTable(int n){
		long[][] mat = new long[n+1][n+1];
		for(int i = 0; i <= n; i++){
			for(int j = 0; j <= i; j++){
				if(j == 0 || j == i) mat[i][j] = 1;
				else mat[i][j] = mat[i-1][j-1] + mat[i-1][j];
			}
		}
		return mat;
	}


	/**
	 * @return Gratest common divisor of two values
	 * O(log(min{a,b}))
	 */
	public static int gcd(int a, int b){
		if(b == 0) return a;
		else return gcd(b, a % b);
	}

	public static long gcd(long a, long b){
		if(b == 0) return a;
		else return gcd(b, a % b);
	}

	/**
	 * @return Greatest common divisor of numbers in an array
	 * O(N*log(a))
	 */
	public static int gcd(int[] arr){
		int n = arr.length;
		int ret = 0;
		for(int i = 0; i < n; i++) {
			ret = gcd(ret, arr[i]);
		}
		return ret;
	}

	public static long gcd(long[] arr){
		int n = arr.length;
		long ret = 0;
		for(int i = 0; i < n; i++){
			ret = gcd(ret, arr[i]);
		}
		return ret;
	}

	/**
	 * @return Least Common Multiple of two values
	 * O(log(min{a,b}))
	 */
	public static long lcm(int a, int b){
		return (long)a * b / gcd(a,b);
	}

	public static long lcm(long a, long b){
		return a * b / gcd(a,b);
	}

	/**
	 * @return Wheater the number is a prime number (true of false)
	 * O(sqrt(N))
	 */
	public static boolean isPrime(int n){
		for(int i = 2; i*i <= n; i++){
			if(n % i == 0) return false;
		}
		return true;
	}

	public static boolean isPrime(long n){
		for(long i = 2; i*i <= n; i++){
			if(n % i == 0) return false;
		}
		return true;
	}

	/**
	 * @return Ascending list of divisors
	 * O(sqrt(N))
	 */
	public static List<Integer> divisor(int n){
		List<Integer> ret = new ArrayList<>();
		for(int i = 1; i*i <= n; i++){
			if(n % i == 0){
				ret.add(i);
				if(i*i != n) ret.add(n/i);
			}
		}
		Collections.sort(ret);
		return ret;
	}

	public static List<Long> divisor(long n){
		List<Long> ret = new ArrayList<>();
		for(long i = 1; i*i <= n; i++){
			if(n % i == 0){
				ret.add(i);
				if(i*i != n) ret.add(n/i);
			}
		}
		Collections.sort(ret);
		return ret;
	}

	/**
	 * @return Map of prime factors: Map<prime number, count>
	 * O(sqrt(N))
	 */
	public static Map<Integer, Integer> primeFactor(int n){
		Map<Integer, Integer> ret = new HashMap<>();
		for(int i = 2; i*i <= n; i++){
			while(n % i == 0){
				int val = 1;
				if(ret.containsKey(i)) val = ret.get(i) + 1;
				ret.put(i, val);
				n /= i;
			}
		}
		if(n != 1) ret.put(n, 1);
		return ret;
	}

	public static Map<Long, Integer> primeFactor(long n){
		Map<Long, Integer> ret = new HashMap<>();
		for(long i = 2; i*i <= n; i++){
			while(n % i == 0){
				int val = 1;
				if(ret.containsKey(i)) val = ret.get(i) + 1;
				ret.put(i, val);
				n /= i;
			}
		}
		if(n != 1) ret.put(n, 1);
		return ret;
	}

	/**
	 * @return TreeMap of prime factors: TreeMap<prime number, count>
	 * O(sqrt(N))
	 */
	public static Map<Integer, Integer> sortedPrimeFactor(int n){
		Map<Integer, Integer> ret = new TreeMap<>();
		for(int i = 2; i*i <= n; i++){
			while(n % i == 0){
				int val = 1;
				if(ret.containsKey(i)) val = ret.get(i) + 1;
				ret.put(i, val);
				n /= i;
			}
		}
		if(n != 1) ret.put(n, 1);
		return ret;
	}
	public static Map<Long, Integer> sortedPrimeFactor(long n){
		Map<Long, Integer> ret = new TreeMap<>();
		for(long i = 2; i*i <= n; i++){
			while(n % i == 0){
				int val = 1;
				if(ret.containsKey(i)) val = ret.get(i) + 1;
				ret.put(i, val);
				n /= i;
			}
		}
		if(n != 1) ret.put(n, 1);
		return ret;
	}

	/**
	 * @return Prime number table less than or equal to n
	 * Table to judge wheather it is a prime number
	 * O(N*log(log(N)))
	 */
	public static boolean[] primeTable(int n){
		boolean[] prime = new boolean[n+1];
		for(int i = 2; i <= n; i++) prime[i] = true;
		for(int i = 2; i*i <= n; i++){
			if(!prime[i]) continue;
			for(int j = i+i; j <= n; j += i){
				prime[j] = false;
			}
		}
		return prime;
	}

	/**
	 * @return Ascending list of prime number sequence less than n
	 * O(N*log(log(N)))
	 */
	public static List<Integer> primeSequence(int n){
		boolean[] prime = primeTable(n);
		List<Integer> seq = new ArrayList<>();
		for(int i = 0; i <= n; i++){
			if(prime[i]) seq.add(i);
		}
		return seq;
	}

	/**
	 * @return The remainder of exponentiation value divided by mod
	 * O(log(N))
	 */
	public static long modPow(long p, long n, long mod){
		long ret = 1;
		while(n > 0){
			if((n & 1) == 1) ret = ret * p % mod;
			p = p * p % mod;
			n >>= 1;
		}
		return ret;
	}

	/**
	 * @return The remainder of factorial value of n divided by mod;
	 * O(N)
	 */
	public static long modFactorial(int n, int mod){
		long ret = 1;
		for(int i = 2; i <= n; i++) ret = ret * i % mod;
		return ret;
	}

	/**
	 * @return inverse of a under mod
	 */
	public static long modInverse(long a, long mod){
		long b = mod, u = 1, v = 0;
		while(b > 0){
			long t = a / b;
			a -= t * b;
			long tmp1 = a; a = b; b = tmp1;
			u -= t * v;
			long tmp2 = u; u = v; v = tmp2;
		}
		u %= mod;
		if(u < 0) u += mod;
		return u;
	}


	/**
	 * Next permutation of given permutation
	 * @return Wheather the next permutation can be found
	 * 				 in lexicographical order 
	 */
	public static boolean nextPermutation(int[] arr){
		for(int i = arr.length-1; i > 0; i--){
			if(arr[i-1] < arr[i]){
				int left = i-1, right = arr.length;
				while(right - left > 1){
					int mid = (left + right) / 2;
					if(arr[mid] > arr[i-1]) left = mid;
					else right = mid;
				}
				int tmp = arr[left];
				arr[left] = arr[i-1];
				arr[i-1] = tmp;
				Arrays.sort(arr, i, arr.length);
				return true;
			}
		}
		return false;
	}
}
