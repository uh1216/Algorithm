package ws._0804;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class S1218 {
	private static char B_OPEN = '['; 
	private static char B_CLOSE = ']'; 
	private static char M_OPEN = '{'; 
	private static char M_CLOSE = '}'; 
	private static char S_OPEN = '('; 
	private static char S_CLOSE = ')'; 
	private static char A_OPEN = '<'; 
	private static char A_CLOSE = '>';
	private static LinkedList<Character> stack;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			char[] brakets = br.readLine().toCharArray();
			
			stack = new LinkedList<>();
			
			int result = 1;
			
			for (char c : brakets) {
				if (c == B_OPEN || c == M_OPEN || c == S_OPEN || c == A_OPEN) {
					stack.addFirst(c);
				}
				else {
					if (stack.isEmpty()) {
						result = 0;
						break;
					}
					
					char b = stack.poll();
					if ((b == B_OPEN && c == B_CLOSE) || (b == M_OPEN && c == M_CLOSE) || (b == S_OPEN && c == S_CLOSE) || (b == A_OPEN && c == A_CLOSE)) {
						continue;
					}
					
					result = 0;
					break;
				}
			}
			bw.write("#" + " " + result + "\n");
		}
		bw.close();
	}
}
