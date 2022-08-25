package ws._0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1922 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new int[] {to, cost});
			graph.get(to).add(new int[] {from, cost});
		}
		
		System.out.println(prim(graph));
	}

	private static int prim(ArrayList<ArrayList<int[]>> graph) {
		int count = 1;
		int cost = 0;
		
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		
		PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		for (int[] n : graph.get(1)) {
			heap.add(n);
		};
		
		while (count != N) {
			int[] next = heap.poll();
			if (!visited[next[0]]) {
				visited[next[0]] = true;
				
				cost += next[1];
				
				for (int[] n : graph.get(next[0])) {
					if (!visited[n[0]]) heap.add(n);
				}
				
				count++;
			}
		}
		
		return cost;
	}

}




