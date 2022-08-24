package ws._0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236 {
	static int move = 0;
	static int size = 2;
	static int eat = 0;
	static boolean canEat = true;
	static int N;
	static int[] row = {-1, 0, 0, 1};
	static int[] col = {0, -1, 1, 0};
	static Point bs;
	
	static class Point {
		int r;
		int c;
		
		public Point() {}
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		int[][] aqua = new int[N][N];
		
		for (int i = 0; i < aqua.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < aqua.length; j++) {
				aqua[i][j] = Integer.parseInt(st.nextToken());
				if (aqua[i][j] == 9) {
					bs = new Point(i, j);
					aqua[i][j] = 0;
				}
			}
		}
		
		while (canEat) {
			bfs(new Point(bs.r, bs.c), aqua);
		}
		
		System.out.println(move);
	}

	private static boolean bfs(Point bsl, int[][] aqua) {
		int count = 0;
		
		canEat = false;
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(bsl);
		
		boolean[][] visited = new boolean[N][N];
		visited[bsl.r][bsl.c] = true;
		
		bs.r = N;
		bs.c = N;
		int db = 1;
		int da = 0;
		while (!queue.isEmpty()) {
			while (!queue.isEmpty() && db != 0) {
				bsl = queue.poll();
				
				if (0 < aqua[bsl.r][bsl.c] && aqua[bsl.r][bsl.c] < size) {
					if (bs.r > bsl.r) {
						bs.r = bsl.r;
						bs.c = bsl.c;
					}
					else if (bs.r == bsl.c) {
						bs.c = bs.c < bsl.c ? bs.c : bsl.c;
					}
					canEat = true;
				}
				
				for (int i = 0; i < 4; i++) {
					int bsR = bsl.r + row[i];
					int bsC = bsl.c + col[i];
					
					if (0 <= bsR && bsR < N && 0 <= bsC && bsC < N && aqua[bsR][bsC] <= size && !visited[bsR][bsC]) {
						visited[bsR][bsC] = true;
						queue.add(new Point(bsR, bsC));
						da++;
					}
				}
				
				db--;
			}
			
			db = da;
			da = 0;
			
			if (canEat) {
				move += count;
				
				System.out.println(bs.r);
				System.out.println(bs.c);
				
				aqua[bs.r][bs.c] = 0;
				for (int[] bs : aqua) {
					System.out.println(Arrays.toString(bs));
				}
				System.out.println();
				
				if (++eat == size) {
					eat = 0;
					size++;
				}
				break;
			}
			
			count++;
		}
		
		return canEat;
	}
}






