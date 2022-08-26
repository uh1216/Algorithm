package ws._0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B4485_dijkstra {
	static int[] row = {-1, 1, 0, 0};
	static int[] col = {0, 0, -1, 1};
	static int N;
	
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int weight;
		
		public Node(int r, int c, int weight) {
			this.r = r;
			this.c = c;
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
		
		ArrayList<ArrayList<Node>> graph;
		
		int[][] cave;
		
		int count = 1;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		while (N != 0) {
			cave = new int[N][N];
			
			graph = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				graph.add(new ArrayList<>());
			}
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<Node> heap = new PriorityQueue<>();
			heap.add(new Node(0, 0, cave[0][0]));
			
			int result = dijkstra(cave, heap);
			
			sb.append("Problem").append(" ").append(count).append(":")
			.append(" ").append(result).append("\n");
		
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			count++;
		}
		System.out.println(sb.toString());
	}

	private static int dijkstra(int[][] cave, PriorityQueue<Node> heap) {
		int count = 1;
		
		int[][] caveCopy = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(caveCopy[i], Integer.MAX_VALUE);
		}
		caveCopy[0][0] = cave[0][0];
		
		boolean[][] visited = new boolean[N][N];
		
		while (!heap.isEmpty() && count != N * N) {
			Node now = heap.poll();
			
			if (!visited[now.r][now.c]) {
				count++;
				visited[now.r][now.c] = true; 
			}
			
			for (int i = 0; i < 4; i++) {
				int nRow = now.r + row[i];
				int nCol = now.c + col[i];
				
				if (0 <= nRow && nRow < N && 0 <= nCol && nCol < N) {
					if (caveCopy[nRow][nCol] > now.weight + cave[nRow][nCol]) {
						caveCopy[nRow][nCol] = now.weight + cave[nRow][nCol];
						heap.add(new Node(nRow, nCol, caveCopy[nRow][nCol]));
					}
				}
			}
		}
		
		return caveCopy[N-1][N-1];
	}

}





