package ws._0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2839 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int result = Integer.MAX_VALUE;
		for (int i = 1; i <= N / 5; i++) {
			int temp = N - (5 * i);
			if (temp % 3 == 0) {
				int r_temp = i + (temp / 3);
				result = r_temp < result ? r_temp : result;
			}
		}
		
		if (result == Integer.MAX_VALUE) {
			if (N % 3 == 0) {
				result = N / 3 < result ? N / 3 : result;
			}
		}
		
		if (result == Integer.MAX_VALUE) {
			result = -1;
		}
		
		System.out.println(result);
	}

}
