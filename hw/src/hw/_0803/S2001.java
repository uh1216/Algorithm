package hw._0803;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S2001 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int max = 0;
			String[] nm = br.readLine().split(" ");
			int n = Integer.parseInt(nm[0]);
			int m = Integer.parseInt(nm[1]);
			
			String[][] board = new String[n][n];
			for (int i = 0; i < board.length; i++) {
				board[i] = br.readLine().split(" ");
			}
			
			for (int i = 0; i <= n - m; i++) {
				for (int j = 0; j <= n - m; j++) {
					int sum = 0;
					for (int j2 = i; j2 < i+m; j2++) {
						for (int k = j; k < j+m; k++) {
							sum += Integer.parseInt(board[j2][k]);
						}
					}
					max = sum > max ? sum : max;
				}
			}
			
			bw.write("#" + test_case + " " + max + "\n");
		}
		bw.close();
	}

}
