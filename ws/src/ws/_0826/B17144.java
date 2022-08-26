package ws._0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17144 {
	static int[] row = {-1, 1, 0, 0};
	static int[] col = {0, 0, -1, 1};
	static int R;
	static int C;
	
	static class Cleaner {
		int r;
		int c;
		
		public Cleaner(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Dust {
		int r;
		int c;
		int left;
		int spread;
		
		public Dust(int r, int c, int left, int spread) {
			this.r = r;
			this.c = c;
			this.left = left;
			this.spread = spread;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		Queue<Dust> dusts = new ArrayDeque<>();
		ArrayList<Cleaner> cleaners = new ArrayList<>();
		
		int[][] room = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int input = Integer.parseInt(st.nextToken());
				
				if (input == -1) {
					cleaners.add(new Cleaner(i, j));
					room[i][j] = -1;
				}
				else if (input != 0) dusts.add(new Dust(i, j, input, input/5));
			}
		}
		
		while (true) {
			spreadDust(dusts, room);
			cleanAir(cleaners, room);
			
			if (--T == 0) break;
			
			cleaners = new ArrayList<>();
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int e = room[i][j];
					
					room[i][j] = 0;
					
					if (e == -1) {
						cleaners.add(new Cleaner(i, j));
						room[i][j] = -1;
					}
					else if (e != 0) dusts.add(new Dust(i, j, e, e/5));
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) count += room[i][j];
			}
		}
		
		System.out.println(count);
	}
	
	private static void spreadDust(Queue<Dust> dusts, int[][] room) {
		while (!dusts.isEmpty()) {
			Dust dust = dusts.poll();
			
			for (int i = 0; i < 4; i++) {
				int nRow = dust.r + row[i];
				int nCol = dust.c + col[i];
				
				if (0 <= nRow && nRow < R && 0 <= nCol && nCol < C && room[nRow][nCol] != -1) {
					dust.left -= dust.spread;
					room[nRow][nCol] += dust.spread;
				}
			}
			room[dust.r][dust.c] += dust.left;
		}
	}

	private static void cleanAir(ArrayList<Cleaner> cleaners, int[][] room) {
		Cleaner up = cleaners.get(0);
		int upR = up.r;
		up.c++;
		
		int tempB = 0;
		int tempA = 0;
		while (up.c != C-1) {
			tempA = room[up.r][up.c];
			room[up.r][up.c] = tempB;
			tempB = tempA;
			up.c++;
		}
		while (up.r != 0) {
			tempA = room[up.r][up.c];
			room[up.r][up.c] = tempB;
			tempB = tempA;
			up.r--;
		}
		while (up.c != 0) {
			tempA = room[up.r][up.c];
			room[up.r][up.c] = tempB;
			tempB = tempA;
			up.c--;
		}
		while (up.r != upR) {
			tempA = room[up.r][up.c];
			room[up.r][up.c] = tempB;
			tempB = tempA;
			up.r++;
		}
		
		Cleaner down = cleaners.get(1);
		int downR = down.r;
		down.c++;
		
		tempB = 0;
		tempA = 0;
		while (down.c != C-1) {
			tempA = room[down.r][down.c];
			room[down.r][down.c] = tempB;
			tempB = tempA;
			down.c++;
		}
		while (down.r != R-1) {
			tempA = room[down.r][down.c];
			room[down.r][down.c] = tempB;
			tempB = tempA;
			down.r++;
		}
		while (down.c != 0) {
			tempA = room[down.r][down.c];
			room[down.r][down.c] = tempB;
			tempB = tempA;
			down.c--;
		}
		while (down.r != downR) {
			tempA = room[down.r][down.c];
			room[down.r][down.c] = tempB;
			tempB = tempA;
			down.r--;
		}
	}

}






