package study._220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
		
		if (isDup(word)) permu(1, new int[word.length+1], word);
		else result = facto(word.length); 
		System.out.println(result);
	}
	
	private static int facto(int length) {
		int sum = 1;
		for (int i = 1; i <= length; i++) {
			sum *= i;
		}
		return sum;
	}

	private static boolean isDup(char[] word) {
		HashSet<Character> s = new HashSet<>();
		for (int i = 0; i < word.length; i++) {
			s.add(word[i]);
		}
		return s.size() != word.length;
	}

	private static void permu(int count, int[] order, char[] word) {
		if (count == order.length) {
			boolean luck = true;
			
			ArrayList<Character> newWord = new ArrayList<>();
			newWord.add(word[order[1]-1]);
			for (int i = 2; i < order.length; i++) {
				if (newWord.get(newWord.size()-1) == word[order[i]-1]) {
					luck = false;
					break;
				}
				newWord.add(word[order[i]-1]);
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
