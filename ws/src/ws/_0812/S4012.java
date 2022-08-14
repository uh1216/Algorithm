package ws._0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4012 {
	private static int[][] arr;
	private static int N;
	private static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					arr[j][j2] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[] visited = new boolean[N];
			comb(visited, 0, 0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	//조합 함수 호출
	private static void comb(boolean[] visited, int start, int count) {
		//식재료가 N/2개가 되면 함수 종료
		if (count == N/2) {
			//새로운 배열을 만들고 각각 가진 식재료들 배열에 넣어줌
			int[] A = new int[N/2];
			int[] B = new int[N/2];
			int a_index = 0;
			int b_index = 0;
			
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) A[a_index++] = i;
				else B[b_index++] = i;
			}
			
			//가진 식재료들을 계산하는 함수를 호출하고 그 차의 절대값을 저장
			int temp = Math.abs(cal(A) - cal(B));
			
			//결과값과 비교해서 차이가 더 작으면 교환
			result = temp > result ? result : temp;
			return;
		}
		
		//조합 재귀호출하는 함수
		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb(visited, i+1, count+1);
				visited[i] = false;
			}
		}
	}
	
	//가진 식재료들로 값을 계산하는 함수
	private static int cal(int[] food) {
		int sum = 0;
		for (int i = 0; i < food.length-1; i++) {
			for (int j = i+1; j < food.length; j++) {
				sum += arr[food[i]][food[j]] + arr[food[j]][food[i]];
			}
		}
		return sum;
	}
}



