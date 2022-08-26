package ws._0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1753 {
	static int V;
	
	static class Node implements Comparable<Node>{
		int no;
		int weight;
		
		public Node(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node(to, weight));
		}
		
		int[] d = new int[V+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[S] = 0;
		
		PriorityQueue<Node> heap = new PriorityQueue<>();
		heap.add(new Node(S, 0));
		
		dijkstra(heap, graph, d);
		
		for (int i = 1; i < d.length; i++) {
			if (d[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(d[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void dijkstra(PriorityQueue<Node> heap, ArrayList<ArrayList<Node>> graph, int[] d) {
		int count = 1;
		boolean[] visited = new boolean[V+1];
		
		while (!heap.isEmpty() && count != V) {
			Node min = heap.poll();
			
			if(!visited[min.no]) {
				count++;
				visited[min.no] = true;
			}
			
			for (int i = 0; i < graph.get(min.no).size(); i++) {
				Node adj = graph.get(min.no).get(i);
				
				if (d[adj.no] > min.weight + adj.weight) {
					d[adj.no] = min.weight + adj.weight;
					heap.add(new Node(adj.no, d[adj.no]));
				}
			}
		}
	}
}





