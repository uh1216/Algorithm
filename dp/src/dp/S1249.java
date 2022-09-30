package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1249 {
	static int N;
	static int[][] map;
	static int[] rows = {0, 1, 0, -1};
	static int[] cols = {1, 0, -1, 0};
	
	static class Kan {
		int r;
		int c;
		
		public Kan(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				char[] tem = st.nextToken().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = tem[j] - '0';
				}
			}
			
			int[][] nMap = new int[N][N];
			for (int i = 0; i < N; i++) nMap[i] = map[i].clone();
			
			sb.append("#").append(test_case).append(" ").append(dpBfs(nMap)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dpBfs(int[][] nMap) {
		Queue<Kan> queue = new ArrayDeque<>();
		queue.add(new Kan(0, 1));
		queue.add(new Kan(1, 0));
		
		boolean[][] visited = new boolean[map.length][map.length];
		
		while (!queue.isEmpty()) {
			Kan kan = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				Kan nKan = new Kan(kan.r + rows[i], kan.c + cols[i]);
				
				if (0 <= nKan.r && nKan.r < N && 0 <= nKan.c && nKan.c < N) {
					if (!visited[nKan.r][nKan.c]) {
						visited[nKan.r][nKan.c] = true;
						nMap[nKan.r][nKan.c] += nMap[kan.r][kan.c];
						queue.add(nKan);
					}
					else {
						if (nMap[nKan.r][nKan.c] > nMap[kan.r][kan.c] + map[nKan.r][nKan.c]) {
							nMap[nKan.r][nKan.c] = nMap[kan.r][kan.c] + map[nKan.r][nKan.c];
							queue.add(nKan);
						}
					}
				}
			}
		}
		
		return nMap[N-1][N-1];
	}
}



