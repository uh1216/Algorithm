package ws._0801;

import java.util.Scanner;

public class S1289 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int result;
		
		for (int i = 1; i <= T; i++) {
			result = 0;
			char[] bit = sc.next().toCharArray();
			
			char p = '0';
			
			for (int j = 0; j < bit.length; j++) {
				if (bit[j] != p) {
					p = (p == '0') ? '1' : '0';
					result++;
				}
			}
			System.out.println("#" + i + " " + result);
		}
	}
}
