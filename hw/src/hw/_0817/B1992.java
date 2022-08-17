package hw._0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1992 {
	private static StringBuilder sb = new StringBuilder(); 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] video = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			
			for (int j = 0; j < N; j++) {
				video[i][j] = s.charAt(j) - '0';
			}
		}
		
		dfs(video);
		System.out.println(sb.toString());
	}
	
	private static void dfs(int[][] video) {
		boolean zip = true;
		int dot = video[0][0];
		for (int i = 0; i < video.length; i++) {
			for (int j = 0; j < video.length; j++) {
				if (dot != video[i][j]) {
					zip = false;
					break;
				}
			}
			if (!zip) break;
		}
		
		if (zip) sb.append(dot);
		else {
			sb.append("(");
			int n = video.length / 2;
			int[][] one = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					one[i][j] = video[i][j];
				}
			}
			dfs(one);
			
			int[][] two = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					two[i][j] = video[i][j + n];
				}
			}
			dfs(two);
			
			int[][] three = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					three[i][j] = video[i+n][j];
				}
			}
			dfs(three);
			
			int[][] four = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					four[i][j] = video[i + n][j + n];
				}
			}
			dfs(four);
			
			sb.append(")");
		}
	}

}
