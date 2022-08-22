package hw._0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S1238 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			
			boolean[][] pro = new boolean[101][101];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i += 2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				pro[from][to] = true;
			}
			
			boolean[] visited = new boolean[101];
			visited[S] = true;
			
			sb.append("#").append(test_case).append(" ");
			
			bfs(S, pro, visited);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void bfs(int S, boolean[][] pro, boolean[] visited) {
		LinkedList<Integer> queue = new LinkedList<>();
		LinkedList<Integer> queue_tem = new LinkedList<>();
		queue.add(S);
		
		int max = 0;
		while(!queue.isEmpty()) {
			max = 0;
			while (!queue.isEmpty()) {
				int n = queue.poll();
				max = n > max ? n : max;
				queue_tem.add(n);
			}
			
			while (!queue_tem.isEmpty()) {
				int n = queue_tem.poll();
				for (int i = 1; i < pro.length; i++) {
					if (pro[n][i] && !visited[i]) {
						visited[i] = true;
						queue.add(i);
					}
				}
			}
		}
		
		sb.append(max).append("\n");
	}

}




