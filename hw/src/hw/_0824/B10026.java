package hw._0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class B10026 {
	static int[] row = {0, 1, 0, -1};
	static int[] col = {1, 0, -1, 0};
	static int N;
	
	static class Color {
		char color;
		int r;
		int c;
		
		public Color() {
		}

		public Color(char color, int r, int c) {
			this.color = color;
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		char[][] painting = new char[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			painting[i] = st.nextToken().toCharArray();
		}
		
		char[][] paintingCopy = new char[N][N];
		for (int i = 0; i < N; i++) {
			paintingCopy[i] = painting[i].clone();
		}
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (paintingCopy[i][j] != 'X') {
					bfs(new Color(paintingCopy[i][j], i, j), paintingCopy);
					count++;
				}
			}
		}
		
		paintingCopy = new char[N][N];
		for (int i = 0; i < N; i++) {
			paintingCopy[i] = painting[i].clone();
		}
		
		int countCb = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (paintingCopy[i][j] != 'X') {
					bfsCb(new Color(paintingCopy[i][j], i, j), paintingCopy);
					countCb++;
				}
			}
		}
		
		sb.append(count).append(" ").append(countCb);
		System.out.println(sb.toString());
	}

	private static void bfsCb(Color color, char[][] painting) {
		Queue<Color> queue = new ArrayDeque<>();
		queue.add(color);
		
		while (!queue.isEmpty()) {
			Color c = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int rowN = c.r + row[i];
				int colN = c.c + col[i];
				
				if (c.color == 'R' || c.color == 'G') {
					if (0 <= rowN && rowN < N && 0 <= colN && colN < N && (painting[rowN][colN] == 'R' || painting[rowN][colN] == 'G')) {
						painting[rowN][colN] = 'X';
						queue.add(new Color(c.color, rowN, colN));
					}
				}
				else {
					if (0 <= rowN && rowN < N && 0 <= colN && colN < N && painting[rowN][colN] == c.color) {
						painting[rowN][colN] = 'X';
						queue.add(new Color(c.color, rowN, colN));
					}
				}
				
			}
		}
	}

	private static void bfs(Color color, char[][] painting) {
		Queue<Color> queue = new ArrayDeque<>();
		queue.add(color);
		
		while (!queue.isEmpty()) {
			Color c = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int rowN = c.r + row[i];
				int colN = c.c + col[i];
				
				if (0 <= rowN && rowN < N && 0 <= colN && colN < N && painting[rowN][colN] == c.color) {
					painting[rowN][colN] = 'X';
					queue.add(new Color(c.color, rowN, colN));
				}
			}
		}
	}



}
