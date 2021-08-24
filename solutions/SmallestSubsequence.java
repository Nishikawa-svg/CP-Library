package solutions;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @verified https://atcoder.jp/contests/typical90/tasks/typical90_f
 * @return The smallest subsequence in lexicographical order
 * 	       among the k-character subsequences of the string s
 */
public class SmallestSubsequence {
	public static String solve(String s, int k){
		int n = s.length();
		int rem = n-k;
		Deque<Character> deq = new ArrayDeque<>();
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(deq.isEmpty() || rem == 0){
				deq.add(c);
				continue;
			}

			char last = deq.getLast();
			while(last > c && rem > 0){
				deq.removeLast();
				rem--;
				if(deq.isEmpty()) break;
				last = deq.getLast();
			}

			deq.add(c);
		}

		String ret = "";
		for(int i = 0; i < k; i++) ret += deq.poll();
		return ret;
	}	
}
