package hw._0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10971 {
	static int N;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		int[][] W = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] visited;
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			findRoute(1, i, 0, i, W, visited);
		}
		
		System.out.println(result);
	}

	private static void findRoute(int city, int start, int count, int now, int[][] w, boolean[] visited) {
		if (count > result) return;
		
		if (city == N) {
			if (w[now][start] == 0) return;
			
			count += w[now][start];
			result = result > count ? count : result;
			return;
		}
		
		for (int i = 0; i < w.length; i++) {
			if (w[now][i] != 0 && !visited[i]) {
				visited[i] = true;
				findRoute(city+1, start, count + w[now][i], i, w, visited);
				visited[i] = false;
			}
		}
	}
}



