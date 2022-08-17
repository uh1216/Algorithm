package ws._0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1861 {
	private static int[] rowV = {-1, 0, 1, 0};
	private static int[] colV = {0, 1, 0, -1};
	private static int[][] rooms;
	private static int N;
	private static int room;
	private static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			room = 0;
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			//방들의 값 넣어주기
			rooms = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//모든 방들에서 재귀 함수 실행
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					go(1, i, j, rooms[i][j]);
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(room).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	//어떤 한 방에서 어디까지 갈 수 있는지 확인하는 함수
	private static void go(int count, int row, int col, int start) {
		//4방향 중 한 방향으로라도 갈 수 있는지 확인하는 변수
		boolean canGo = false;
		
		//4방향 모두 확인
		for (int i = 0; i < 4; i++) {
			int n_row = row + rowV[i];
			int n_col = col + colV[i];
			
			//가려는 범위가 인덱스 내 범위이고, 현재 방보다 1큰 방이면
			if (0 <= n_row && n_row < N && 0 <= n_col && n_col < N && (rooms[n_row][n_col] - rooms[row][col] == 1)) {
				//재귀 함수 호출
				go(count+1, n_row, n_col, start);
				canGo = true;
				break;
			}
		}
		
		//어디로도 갈 수 없다면, 한 방에서 최대로 움직인 것으로 판단, 현재 최대 값과 비교해줌
		if (!canGo) {
			if (result < count) {
				result = count;
				room = start;
			}
			else if (result == count && room > start) {
				room = start;
			}
		}
	}

}
