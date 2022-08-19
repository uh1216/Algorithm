package ws._0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3234 {
	private static int[] weight;
	private static int N;
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			//가진 추 전체의 무게
			int all = 0;
			//각 추의 무게 배열
			weight = new int[N];
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
				all += weight[i];
			}
			
			//팩토리얼 배열로 만들어놓음
			int[] facto = new int[10];
			facto[0] = 1;
			for (int i = 1; i < facto.length; i++) {
				facto[i] = 1;
				for (int j = 1; j <= i; j++) {
					facto[i] *= j; 
				}
			}
			
			//2의 n승 배열로 만들어놓음
			int[] pow = new int[10];
			for (int i = 0; i < pow.length; i++) {
				pow[i] = (int) Math.pow(2, i);
			}
			
			//dfs호출
			dfs(0, 0, all, new boolean[N], facto, pow, 0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	
	private static void dfs(int left, int right, int all, boolean[] visited, int[] facto, int[] pow, int count) {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				
				//만약 왼쪽 추들의 무게가 오른쪽 추들의 무게 + 남은 추들의 무게보다 크다면 나머지 경우의 수들은 모두 올릴 수 있는 경우이므로, 
				//dfs를 타지 않고 경우의 수만 계산해서 결과값에 더해줌
				if (left + weight[i] >= right + all - weight[i]) {
					result += pow[N - (count+1)] * facto[N - (count+1)];
				}
				//아닐 경우 왼쪽에 추 하나 올림
				else {
					dfs(left + weight[i], right, all - weight[i], visited, facto, pow, count+1);
				}
				//오른쪽에 추 하나 올릴 수 있을 경우 추 올림
				if (left >= right + weight[i]) {
					dfs(left, right + weight[i], all - weight[i], visited, facto, pow, count+1);
				}
				visited[i] = false;
			}
		}
	}
}




