package ws._0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B13023 {
	static boolean result = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			visited[i] = true;
			dfs(0, i, visited, graph);
		}
		
		if (result) System.out.println(1);
		else System.out.println(0);
	}

	private static void dfs(int count, int start, boolean[] visited, ArrayList<ArrayList<Integer>> graph) {
		if (result) return;
		
		if (count == 4) {
			result = true;
			return;
		}
		
		ArrayList<Integer> c = graph.get(start);
		for (Integer i : c) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(count+1, i, visited, graph);
				visited[i] = false;
			}
		}
	}
}
