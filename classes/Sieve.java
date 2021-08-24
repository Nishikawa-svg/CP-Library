package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Sieve {
	int size;
	int[] table;
	boolean[] isPrime;
	List<Integer> prime;
	public Sieve(int size){
		this.size = size;
		table = new int[size+1];
		isPrime = new boolean[size+1];
		List<Integer> prime = new ArrayList<>();
		for(int i = 2; i <= size; i++){
			if(table[i] == 0){
				table[i] = i;
				prime.add(i);
				isPrime[i] = true;
				for(int j = i+i; j <= size; j += i){
					if(table[j] == 0) table[j] = i;
				}
			}
		}
	}
	public Map<Integer, Integer> primeFactors(int n){
		Map<Integer, Integer> res = new TreeMap<>();
		while(true){
			int p = table[n];
			if(res.containsKey(p))	res.put(p, res.get(p)+1);
			else res.put(p, 1);
			if(p == n) break;
			else n /= p;
		}
		return res;
	}
}
