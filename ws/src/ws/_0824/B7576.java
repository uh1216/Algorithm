package ws._0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576 {
	static Queue<Point> queue = new ArrayDeque<>();
	static int[] row = {-1, 1, 0, 0};
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
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] box = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) queue.add(new Point(i, j));
			}
		}
		
		if (isAllRipe(box)) System.out.println(0);
		else System.out.println(bfs(box));
	}

	private static boolean isAllRipe(int[][] box) {
		boolean flag = true;
		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box[0].length; j++) {
				if (box[i][j] == 0) {
					flag = false;
					break;
				}
			}
			if (!flag) break;
		}
		return flag;
	}

	private static int bfs(int[][] box) {
		int result = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			while (--size >= 0) {
				Point t = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int rowN = t.r + row[i];
					int colN = t.c + col[i];
					
					if (0 <= rowN && rowN < box.length && 0 <= colN && colN < box[0].length && box[rowN][colN] == 0) {
						box[rowN][colN] = 1;
						queue.add(new Point(rowN, colN));
					}
				}
			}
			result++;
		}
		
		if (isAllRipe(box)) return result-1;
		else return -1; 
	}

}



