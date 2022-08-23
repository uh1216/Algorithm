package ws._0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S3124 {
	
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<>());
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				graph.get(A).add(new int[] {B, C});
				graph.get(B).add(new int[] {A, C});
			}
			
			prim(V, 1, graph);

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void prim(int V, int node, ArrayList<ArrayList<int[]>> graph) {
		int count = 1;
		boolean[] visited = new boolean[V+1];
		visited[node] = true;
		PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		while (count != V) {
			ArrayList<int[]> nextE = graph.get(node);
			for (int i = 0; i < nextE.size(); i++) {
				heap.add(nextE.get(i).clone());
			}
			
			int[] next = heap.poll();
			while (visited[next[0]]) {
				next = heap.poll();
			}
			
			visited[next[0]] = true;
			node = next[0];
			result += next[1];
			count++;
		}
	}

}





