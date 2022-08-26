package ws._0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4485_bfsDp {
	static int N;
	static int[] row = {1, -1, 0, 0};
	static int[] col = {0, 0, -1, 1};
	
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int count = 1;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		while (N != 0) {
			int[][] cave = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<Point> queue = new ArrayDeque<>();
			queue.add(new Point(0, 0));
			
			int result = bfsDp(queue, cave);
			
			sb.append("Problem").append(" ").append(count).append(":")
				.append(" ").append(result).append("\n");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			count++;
		}
		System.out.println(sb.toString());
	}

	private static int bfsDp(Queue<Point> queue, int[][] cave) {
		boolean[][] visited = new boolean[N][N]; 
		visited[0][0] = true;
		
		int[][] caveCopy = new int[N][N];
		for (int i = 0; i < N; i++) {
			caveCopy[i] = cave[i].clone();
		}
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nRow = now.r + row[i];
				int nCol = now.c + col[i];
				
				if (0 <= nRow && nRow < N && 0 <= nCol && nCol < N) {
					if (visited[nRow][nCol]) {
						if (caveCopy[nRow][nCol] > caveCopy[now.r][now.c] + cave[nRow][nCol]) {
							caveCopy[nRow][nCol] = caveCopy[now.r][now.c] + cave[nRow][nCol];
							queue.add(new Point(nRow, nCol));
						}
					}
					else {
						visited[nRow][nCol] = true;
						caveCopy[nRow][nCol] += caveCopy[now.r][now.c];
						queue.add(new Point(nRow, nCol));
					}
				}
			}
		}
		
		return caveCopy[N-1][N-1];
	}
}




