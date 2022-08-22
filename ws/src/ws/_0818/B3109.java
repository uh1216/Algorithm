package ws._0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3109 {
	private static int R;
	private static int C;
	private static int[] v = {-1, 0, 1};  
	private static boolean[] pipes;
	private static char[][] map;
	private static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//파이프가 왼쪽 한 지점에서 오른쪽 끝까지 연결되었는지 확인하는 배열
		pipes = new boolean[R];
		
		//지도 배열
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		
		//왼쪽 0번째 인덱스부터 R-1번째 인덱스까지 dfs호출
		for (int i = 0; i < R; i++) {
			dfs(i, i, 0);
		}
		
		System.out.println(result);
	}
	
	//dfs를 통해 왼쪽 한 지점에서 오른쪽 끝까지 파이프가 연결될 수 있는지 확인 
	private static void dfs(int startR, int row, int col) {
		//한 지점에서 시작하였지만 먼저 끝까지 연결된 함수가 있다면 나머지 함수들은 종료
		if (pipes[startR]) {
			return;
		}
		
		//시작 위치는 다음에 사용할 수 없음
		map[row][col] = 'x';
		
		//오른쪽 끝 열에 도착했다면, 연결되었는지 확인하는 배열 true로 만듬, 결과값 +1 해줌
		if (col == C-1) {
			pipes[startR] = true;
			result++;
			return;
		}
		
		//오른쪽 위, 오른쪽, 오른쪽 아래 확인하여 갈 수 있으면 이동
		for (int i = 0; i < v.length; i++) {
			int n_row = row + v[i];
			int n_col = col + 1;
			
			if (0 <= n_row && n_row < R && map[n_row][n_col] != 'x') {
				dfs(startR, n_row, n_col);	
			}
		}
	}
}




