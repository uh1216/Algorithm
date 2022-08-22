package study._220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B1342 {
	static int result = 0;
	static HashSet<ArrayList<Character>> al = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		char[] word = st.nextToken().toCharArray();
		
		permu(1, new int[word.length+1], word);
		
		System.out.println(result);
	}
	
	private static void permu(int count, int[] order, char[] word) {
		if (count == order.length) {
			boolean luck = true;
			
			ArrayList<Character> newWord = new ArrayList<>();
			char b = word[order[1]-1];
			newWord.add(b);
			for (int i = 2; i < order.length; i++) {
				if (b == word[order[i]-1]) {
					luck = false;
					break;
				}
				b = word[order[i]-1];
				newWord.add(b);
			}
			
			if (luck && !al.contains(newWord)) {
				al.add(newWord);
				result++;
			}
			
			return;
		}
		
		for (int i = 1; i < order.length; i++) {
			if (order[i] == 0) {
				order[i] = count;
				permu(count+1, order, word);
				order[i] = 0;
			}
		}
	}
}
