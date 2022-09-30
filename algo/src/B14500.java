import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B14500 {
	static int result = 0;
	static int[] rows = {1, 0, 0, -1};
	static int[] cols = {0, 1, -1, 0};
	static int N;
	static int M;
	static int paper[][];
	
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
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		boolean[][] visitedCopy = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ArrayList<Kan> kans = new ArrayList<>();
				kans.add(new Kan(i, j));
				
				visited[i][j] = true;
				
				for (int v = 0; v < visitedCopy.length; v++) {
					visitedCopy[v] = visited[v].clone();
				}
				
				dfs(kans, visitedCopy);
			}
		}
	}

	private static void dfs(ArrayList<Kan> kans, boolean[][] visited) {
		if (kans.size() == 4) {
			int sum = 0;
			for (Kan kan : kans) sum += paper[kan.r][kan.c];
			result = sum < result ? result : sum;
			return;
		}
		
		if (kans.size() == 3) {
			Kan maxKan = new Kan(0, 0);
			int max = 0;
			
			for (int i = 0; i < kans.size(); i++) {
				Kan now = kans.get(i);
				for (int j = 0; j < 4; j++) {
					Kan nKan = new Kan(now.r + rows[j], now.c + cols[j]);
					
					if (0 <= nKan.r && nKan.r < N && 0 <= nKan.c && nKan.c < M && !visited[nKan.r][nKan.c]) {
						if (paper[nKan.r][nKan.c] > max) {
							max = paper[nKan.r][nKan.c];
							maxKan = new Kan(nKan.r, nKan.c);
						}
					}
				}
			}
			
			kans.add(maxKan);
			dfs(kans, visited);
		}
		
		else {
			for (int i = 0; i < kans.size(); i++) {
				Kan now = kans.get(i);
				for (int j = 0; j < 4; j++) {
					Kan nKan = new Kan(now.r + rows[j], now.c + cols[j]);
					
					if (0 <= nKan.r && nKan.r < N && 0 <= nKan.c && nKan.c < M && !visited[nKan.r][nKan.c]) {
						visited[nKan.r][nKan.c] = true;
						kans.add(nKan);
						dfs(kans, visited);
						visited[nKan.r][nKan.c] = false;
					}
				}
			}
		}
	}

}









