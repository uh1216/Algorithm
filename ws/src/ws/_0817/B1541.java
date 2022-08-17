package ws._0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B1541 {
	private static LinkedList<Character> op = new LinkedList<>();
	private static LinkedList<Integer> nums = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		op = new LinkedList<>();
		nums = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		
		StringBuilder tem = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-' || s.charAt(i) == '+') {
				op.add(s.charAt(i));
				nums.add(Integer.parseInt(tem.toString()));
				tem = new StringBuilder();
			}
			else tem.append(s.charAt(i));
		}
		if (tem.length() != 0) nums.add(Integer.parseInt(tem.toString()));
		
		for (int i = op.size()-1; i >= 0; i--) {
			if (op.get(i) == '+') cal(i);
		}
		
		for (int i = 0, size = op.size(); i < size; i++) {
			cal(0);
		}

		System.out.println(nums.poll());
	}
	
	private static void cal(int index) {
		char o = op.remove(index);
		if (o == '+') {
			nums.set(index, nums.get(index) + nums.remove(index+1));
		}
		else nums.set(index, nums.get(index) - nums.remove(index+1));
	}

}
