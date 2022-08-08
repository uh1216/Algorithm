package algo.solved;

import java.util.LinkedList;
import java.util.Scanner;

public class B16637_2 {
	
	private static LinkedList<Character> ope;
	private static LinkedList<Integer> num;
	private static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		String s = sc.next();
		
		ope = new LinkedList<>();
		num = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '*' || s.charAt(i) == '+' || s.charAt(i) == '-') {
				ope.add(s.charAt(i));
			}
			else {
				num.add(s.charAt(i) - '0');
			}
		}
		
		result = calAll(new LinkedList<Character>(ope), new LinkedList<Integer>(num));
		
		n = (n+1) / 2;
		
		for (int i = 1; i <= n; i++) {
			int[] numbers = new int[i];
			comb(numbers, 0, ope.size(), 0, i);
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
	
	private static void comb(int[] numbers, int start, int N, int count, int ob) {
		if (count == ob) {
			LinkedList<Character> n_ope = new LinkedList<Character>(ope);
			LinkedList<Integer> n_num = new LinkedList<Integer>(num);
			
			for (int i = numbers.length - 1; i >= 0 ; i--) {
				cal(n_ope, n_num, numbers[i]);
			}
			
			int temp = calAll(n_ope, n_num);
			result = temp > result ? temp : result;
			return;
		}
		
		for (int i = start; i < N; i++) {
			numbers[count] = i;
			comb(numbers, i + 2, N, count + 1, ob);
		}
	}

}
