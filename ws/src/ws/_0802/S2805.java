package ws._0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2805 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int result = 0;
			int n = Integer.parseInt(br.readLine());
			String[][] farm = new String[n][n];
			
			/**
			 *  농장 배열 만들기
			 */
			for (int i = 0; i < n; i++) {
				farm[i] = br.readLine().split("");
			}
			
			n /= 2;
			
			/**
			 * 마름모 영역 계산하기
			 */
			for (int i = 0; i < farm.length; i++) {
				// 위에서부터 가운데 삼각형 영역 더하기
				if (i <= n) {
					for (int j = (n - i); j < farm.length - (n - i); j++) {
						result += Integer.parseInt(farm[i][j]);
					}
				}
				// 가운데 다음부터 마지막 삼각형 영역 더하기
				else {
					for (int j = (i - n); j < farm.length - (i - n); j++) {
						result += Integer.parseInt(farm[i][j]);
					}
				}
			}
			
			System.out.println(result);
		}
	}

}
