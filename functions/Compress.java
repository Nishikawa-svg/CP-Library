package functions;

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

public class Compress {
	static int[] compress(int[] x){
		Set<Integer> t = new TreeSet<>();
		for(int el : x) t.add(el);
		int[] c = new int[t.size()];
		int cnt = 0;
		for(int el : t)	c[cnt++] = el;
		int[] cc = new int[x.length];
		for(int i = 0; i < x.length; i++){
			cc[i] = Arrays.binarySearch(c, x[i]);
		}
		return cc;
	}

	static int[] compress(int[] x1, int[] x2){
		Set<Integer> t = new TreeSet<>();
		for(int el : x1) {
			t.add(el);
			t.add(el+1);
		}
		for(int el : x2) {
			t.add(el);
			t.add(el+1);
		}
		int[] c = new int[t.size()];
		int cnt = 0;
		for(int el : t)	c[cnt++] = el;
		for(int i = 0; i < x1.length; i++){
			x1[i] = Arrays.binarySearch(c, x1[i]);
			x2[i] = Arrays.binarySearch(c, x2[i]);
		}
		return c;
	}
}
