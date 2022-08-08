package ws._0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2023 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] nums = {2, 3, 5, 7};
		
		int N = Integer.parseInt(br.readLine());
		int p = (int) Math.pow(10, N-1);
		int x;
		for (int j = 0; j < nums.length; j++) {
			for (int i = p * nums[j]; i < p * (nums[j] + 1); i++) {
				x = p;
				while (x > 0 && sosu(i / x)) {
					x /= 10;
				}
				if (x == 0) sb.append(i + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean sosu(int i) {
		if (i == 2) {
			return true;
		}
		for (int j = 2; j < Math.sqrt(i) + 1; j++) {
			if (i % j == 0) {
				return false;
			}
		}
		return true;
	}
	

}
