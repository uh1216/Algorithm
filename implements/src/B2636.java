import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2636 {
	static int R;
	static int C;
	
	static int[] rows = {1, -1, 0, 0};
	static int[] cols = {0, 0, 1, -1};
	
	static class P {
		int r;
		int c;
		
		public P(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//치즈 배열
		int[][] cheeze = new int[R][C];
		
		//남은 치즈 칸
		int left = 0;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				cheeze[i][j] = Integer.parseInt(st.nextToken());
				if (cheeze[i][j] == 1) left++;
			}
		}
		
		//걸리는 시간
		int time = 0;
		
		//전에 남은 치즈
		int before = left;
		
		//남은 치즈가 0이 될때까지
		while (left != 0) {
			//전에 남은 것을 현재 남은 치즈로 넣어줌
			before = left;
			
			//바깥과 닿는지 bfs로 확인
			bfs(cheeze);
			
			//임시 변수로 남은 치즈 확인, 없애야할 치즈 없애줌
			left = countCheeze(cheeze);
			
			time++;
		}
		
		System.out.println(time);
		System.out.println(before);
	}

	private static int countCheeze(int[][] cheeze) {
		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (cheeze[i][j] == -1) cheeze[i][j] = 0;
				else if (cheeze[i][j] == 1) count++;
			}
		}
		return count;
	}

	private static void bfs(int[][] cheeze) {
		Queue<P> queue = new ArrayDeque<>();
		queue.add(new P(0, 0));
		
		boolean[][] visited = new boolean[R][C];
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			P p = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = p.r + rows[i];
				int nc = p.c + cols[i];
				
				if (0 <= nr && nr < R && 0 <= nc && nc < C && cheeze[nr][nc] != -1 && !visited[nr][nc]) {
					if (cheeze[nr][nc] == 1) cheeze[nr][nc] = -1;
					else {
						visited[nr][nc] = true;
						queue.add(new P(nr, nc));
					}
				}
			}
		}
	}

}





