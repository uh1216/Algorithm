package algo.solved;

import java.util.LinkedList;
import java.util.Scanner;

public class B16637 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int result = 0;
		
		int n = sc.nextInt();
		String s = sc.next();
		
		LinkedList<Character> ope = new LinkedList<>();
		LinkedList<Integer> num = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-') {
				ope.add(s.charAt(i));
			}
			else {
				num.add(s.charAt(i) - '0');
			}
		}
		
		LinkedList<Character> n_ope = new LinkedList<>(ope);
		LinkedList<Integer> n_num = new LinkedList<>(num);
		result = calAll(n_ope, n_num);
		
		for (int i = 0; i < ope.size(); i++) {
			n_ope = new LinkedList<>(ope);
			n_num = new LinkedList<>(num);
			
			cal(n_ope, n_num, i);
			
			LinkedList<Character> n2_ope;
			LinkedList<Integer> n2_num;
			
			for (int j = i + 1; j < n_ope.size(); j++) {
				n2_ope = new LinkedList<>(n_ope);
				n2_num = new LinkedList<>(n_num);
				cal(n2_ope, n2_num, j);
				
				LinkedList<Character> n3_ope;
				LinkedList<Integer> n3_num;
				
				for (int k = j + 1; k < n2_ope.size(); k++) {
					n3_ope = new LinkedList<>(n2_ope);
					n3_num = new LinkedList<>(n2_num);
					
					cal(n3_ope, n3_num, k);
					
					LinkedList<Character> n4_ope;
					LinkedList<Integer> n4_num;
					
					for (int l = k + 1; l < n3_ope.size(); l++) {
						n4_ope = new LinkedList<>(n3_ope);
						n4_num = new LinkedList<>(n3_num);
						
						cal(n4_ope, n4_num, l);
						
						LinkedList<Character> n5_ope;
						LinkedList<Integer> n5_num;
						
						for (int m = l + 1; m < n4_ope.size(); m++) {
							n5_ope = new LinkedList<>(n4_ope);
							n5_num = new LinkedList<>(n4_num);
							
							cal(n5_ope, n5_num, m);
							
							int temp5 = calAll(n5_ope, n5_num);
							result = temp5 > result ? temp5 : result;
						}
						int temp4 = calAll(n4_ope, n4_num);
						result = temp4 > result ? temp4 : result;
					}
					int temp3 = calAll(n3_ope, n3_num);
					result = temp3 > result ? temp3 : result;
				}
				int temp2 = calAll(n2_ope, n2_num);
				result = temp2 > result ? temp2 : result;
			}
			int temp = calAll(n_ope, n_num);
			result = temp > result ? temp : result; 
		}
		System.out.println(result);
	}


	private static void cal(LinkedList<Character> ope, LinkedList<Integer> num, int i) {
		char operation = ope.remove(i);
		if (operation == '+') {
			num.add(i, num.remove(i) + num.remove(i));
		}
		else if (operation == '*') {
			num.add(i, num.remove(i) * num.remove(i));
		}
		else {
			num.add(i, num.remove(i) - num.remove(i));
		}
	}
	

	private static int calAll(LinkedList<Character> ope, LinkedList<Integer> num) {
		while (!ope.isEmpty()) {
			char operation = ope.poll();
			
			if (operation == '+') {
				num.addFirst(num.poll() + num.poll());
			}
			else if (operation == '*') {
				num.addFirst(num.poll() * num.poll());
			}
			else {
				num.addFirst(num.poll() - num.poll());
			}
		}
		return num.poll();
	}

}
