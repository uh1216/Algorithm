package ws._0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16926 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()) ;
		int T = Math.min(N, M) / 2;
		
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//배열 회전 시작
		int row;
		int col;
		int temp_b;
		int temp_a = 0;
		// 배열의 안쪽도 돌리기 위해 첫번째 인덱스 부터 N,M 중 작은 수 /2만큼 반복
		for (int i = 0; i < T; i++) {
			row = i;
			col = i;
			// 회전 횟수만큼 이동
			for (int j = 0; j < R; j++) {
				//전 값 임시 변수에 저장
				temp_b = arr[row][col];
				
				//왼쪽
				for (int l = i; l < N - i - 1; l++) {
					row++;
					temp_a = arr[row][col];
					arr[row][col] = temp_b;
					temp_b = temp_a;
				}
				
				//아래
				for (int l = i; l < M - i - 1; l++) {
					col++;
					temp_a = arr[row][col];
					arr[row][col] = temp_b;
					temp_b = temp_a;
				}
				
				//오른쪽
				for (int l = i; l < N - i - 1; l++) {
					row--;
					temp_a = arr[row][col];
					arr[row][col] = temp_b;
					temp_b = temp_a;
				}
				
				//위
				for (int l = i; l < M - i - 1; l++) {
					col--;
					temp_a = arr[row][col];
					arr[row][col] = temp_b;
					temp_b = temp_a;
				}
			}
		}
		
		for (int[] is : arr) {
			for (int a : is) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
