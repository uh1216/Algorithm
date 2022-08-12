package hw._0802;

import java.util.Scanner;

public class S1954 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			int[][] snail = new int[n][n];
			
			int i = 2;
			int row = 0;
			int col = 0;
			
			snail[row][col] = 1;

			while (i <= n * n) {
				while (col+1 < n && snail[row][col+1] == 0) {
					snail[row][++col] = i;
					i++;
				}
				while (row+1 < n && snail[row+1][col] == 0) {
					snail[++row][col] = i;
					i++;
				}
				while (col-1 >= 0 && snail[row][col-1] == 0) {
					snail[row][--col] = i;
					i++;
				}
				while (row-1 >= 0 && snail[row-1][col] == 0) {
					snail[--row][col] = i;
					i++;
				}
			}
			
			System.out.println("#" + test_case);
			for (int[] sn : snail) {
				for (int s : sn) {
					System.out.print(s + " ");
				}
				System.out.println();
			}
		}
	}
}
