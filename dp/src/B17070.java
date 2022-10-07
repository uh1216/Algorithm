

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17070 {
	static int N;
	static int[] rows = {0, -1, -1};
	static int[] cols = {-1, 0, -1};
	
	//각 칸에 들어갈 클래스 선언
	static class Kan {
		boolean isWall;
		int w;
		int h;
		int d;
		
		public Kan(boolean isWall, int w, int h, int d) {
			this.isWall = isWall;
			this.w = w;
			this.h = h;
			this.d = d;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		//Kan을 넣을 Dp배열 선언
		Kan[][] house = new Kan[N][N];
		
		//DP배열에 Kan객체를 넣어주고, 1일 경우 벽 표시를 해줌
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int count = Integer.parseInt(st.nextToken());
				house[i][j] = new Kan(false, 0, 0, 0);
				
				if (count == 1) house[i][j].isWall = true;
			}
		}
		//시작 점 방법 +1
		house[0][1].w = 1;
		
		//모든 칸을 돌며 Dp함수 실행
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!house[i][j].isWall) dp(house, i, j);
			}
		}
		
		//모든 방법들을 다 더하여 결과값 출력
		System.out.println(house[N-1][N-1].w + house[N-1][N-1].h + house[N-1][N-1].d);
	}

	//Dp 함수
	private static void dp(Kan[][] house, int r, int c) {
		//현재 칸의 객체
		Kan kan = house[r][c];
		
		//왼쪽, 위, 왼쪽위 대각선 탐색
		for (int i = 0; i < 3; i++) {
			int nR = r + rows[i];
			int nC = c + cols[i];
			
			//해당 위치가 범위 안에 있고, 벽이 아니라면
			if (0 <= nR && nR < N && 0 <= nC && nC < N && !house[nR][nC].isWall) {
				Kan bKan = house[nR][nC];
				//왼쪽을 볼 경우 전 칸의 가로, 대각선 방향의 경우의 수를 더해줌
				if (i == 0) {
					kan.w += bKan.w + bKan.d;
				}
				//오른쪽을 볼 경우 전 칸의 세로, 대각선 방향의 경우의 수를 더해줌
				else if (i == 1) {
					kan.h = bKan.h + bKan.d;
				}
				//왼쪽 위를 볼 경우 전 칸의 모든 방향의 경우의 수를 더해줌
				else if (i == 2 
							&& !house[r + rows[0]][c + cols[0]].isWall
							&& !house[r + rows[1]][c + cols[1]].isWall) {
					kan.d += bKan.w + bKan.h + bKan.d;
				}
			}
		}
	}
}


