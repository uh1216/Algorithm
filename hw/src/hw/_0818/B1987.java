package hw._0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B1987 {
	private static int R;
	private static int C;
	private static int[] rv = {1, 0, -1, 0};
	private static int[] cv = {0, -1, 0, 1};
	private static char[][] board;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			board[i] = st.nextToken().toCharArray();
		}
		
		dfs(new int[] {0, 0}, new HashSet<>(Arrays.asList(board[0][0])), 1);
		
		System.out.println(result);
	}

	private static void dfs(int[] loca, HashSet<Character> visited, int count) {
		for (int i = 0; i < 4; i++) {
			int n_row = loca[0] + rv[i];
			int n_col = loca[1] + cv[i];
			
			if (0 <= n_row && n_row < R && 0 <= n_col && n_col < C && !visited.contains(board[n_row][n_col])) {
				visited.add(board[n_row][n_col]);
				dfs(new int[] {n_row, n_col}, visited, count+1);
				visited.remove(board[n_row][n_col]);
			}
		}
		result = result < count ? count : result;
	}
	
	
}
