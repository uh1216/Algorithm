package hw._0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B1697 {
	private static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		bfs(N, K);
		
		System.out.println(result);
	}

	private static void bfs(int N, int K) {
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(new int[] {N, 0});
		
		boolean[] visited = new boolean[100001];
		while (!queue.isEmpty()) {
			int[] n = queue.poll();
			
			if (n[0] == K) {
				result = n[1];
				return;
			}
			if (n[0]*2 <= 100000 && !visited[n[0]*2]) {
				visited[n[0]*2] = true;
				queue.add(new int[] {n[0]*2, n[1]+1});
			}
			if (0 <= n[0]-1 && !visited[n[0]-1]) {
				visited[n[0]-1] = true;
				queue.add(new int[] {n[0]-1, n[1]+1});
			}
			if (n[0]+1 <= 100000 && !visited[n[0]+1]) {
				visited[n[0]+1] = true;
				queue.add(new int[] {n[0]+1, n[1]+1});
			}
		}
	}

}
