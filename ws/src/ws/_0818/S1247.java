package ws._0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1247 {
	private static int N;
	private static int[] com;
	private static int[] home;
	private static int[][] customer;
	private static int result;

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
			
			st = new StringTokenizer(br.readLine());
			com = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			home = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			customer = new int[N][];
			for (int i = 0; i < N; i++) {
				customer[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			per(0, new boolean[N], com, 0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void per(int count, boolean[] visited, int[] loca, int sum) {
		if (sum > result) {
			return;
		}
		if (count == N) {
			sum += cal(loca, home);
			result = result < sum ? result : sum;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				per(count+1, visited, new int[] {customer[i][0], customer[i][1]}, sum + cal(loca, customer[i]));
				visited[i] = false;
			}
		}
	}

	private static int cal(int[] loca, int[] customer) {
		return Math.abs(loca[0] - customer[0]) + Math.abs(loca[1] - customer[1]);
	}

}





