package ws._0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3055 {
	static int result;
	static int bR;
	static int bC;
	
	static int[] row = {1, 0, -1, 0};
	static int[] col = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] go = new int [2];
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[j];
				if (map[i][j] == 'S') {
					go[0] = i;
					go[1] = j;
					map[i][j] = '.';
				}
				if (map[i][j] == 'D') {
					bR = i;
					bC = j;
				}
			}
		}
		
		bfs(go, 0, map);
		
		if (result == 0) System.out.println("KAKTUS");
		else System.out.println(result);
	}

	private static void bfs(int[] go, int count, char[][] map) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(go);
		
		boolean[][] visited = new boolean[map.length][map[0].length];
		visited[go[0]][go[1]] = true;

		int db = 1;
		int da = 0;
		while (!queue.isEmpty()) {
			
			waterDown(map);
			
			while (!queue.isEmpty() && db != 0) {
				go = queue.poll();
				db--;
				
				if (go[0] == bR && go[1] == bC) {
					result = result > count ? result : count;
					return;
				}
				
				for (int i = 0; i < 4; i++) {
					int ngoR = go[0] + row[i];
					int ngoC = go[1] + col[i];
					
					if (0 <= ngoR && ngoR < map.length && 0 <= ngoC && ngoC < map[0].length && 
							(map[ngoR][ngoC] == '.' || map[ngoR][ngoC] == 'D') && !visited[ngoR][ngoC]) {
						visited[ngoR][ngoC] = true;
						queue.add(new int [] {ngoR, ngoC});
						da++;
					}
				}
			}
			
			db = da;
			da = 0;
			count++;
		}
		
	}

	private static void waterDown(char[][] map) {
		ArrayList<int[]> waters = new ArrayList<>();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == '*') {
					waters.add(new int[] {i, j});
				}
			}
		}
		for (int[] water : waters) {
			for (int k = 0; k < 4; k++) {
				int nR = water[0] + row[k];
				int nC = water[1] + col[k];
				
				if (0 <= nR && nR < map.length && 0 <= nC && nC < map[0].length && map[nR][nC] == '.') {
					map[nR][nC] = '*';
				}
			}
		}
	}

}






