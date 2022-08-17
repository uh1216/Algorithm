package hw._0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2563 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int result = 0;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] papers = new int[100][100];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < 10; j++) {
				for (int j2 = 0; j2 < 10; j2++) {
					papers[row + j][col + j2] = 1;
				}
			}
		}
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (papers[i][j] == 1) result += 1;
			}
		}
		
		System.out.println(result);
	}

}
