package ws._0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B1260 {
	private static int N;
	private static int[][] pro;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		pro = new int[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			pro[from][to] = pro[to][from] = 1;
		}
		
		boolean[] visited = new boolean[N+1];
		visited[S] = true;
		
		dfs(S, visited);
		sb.append("\n");
		bfs(S);
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int S, boolean[] visited) {
		sb.append(S).append(" ");
		for (int i = 1; i <= N; i++) {
			if (pro[S][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(i, visited);
			}
		}
	}
	
	private static void bfs(int S) {
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(S);
		
		boolean[] visited = new boolean[N+1];
		visited[S] = true;
		
		while (!queue.isEmpty()) {
			int s = queue.poll();
			sb.append(s).append(" ");
			
			for (int i = 1; i <= N; i++) {
				if (pro[s][i] == 1 && !visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}



