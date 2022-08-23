package ws._0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3289 {
	static int[] uf;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			uf = new int[N+1];
			for (int i = 1; i <= N; i++) {
				uf[i] = i;
			}
			
			sb.append("#").append(test_case).append(" ");
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int o = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (o == 0) union(a, b);
				else if (o == 1) {
					if (find(a) == find(b)) sb.append(1);
					else sb.append(0);
				}
				
				System.out.println(Arrays.toString(uf));
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static int find(int a) {
		if (uf[a] == a) return a;
		else return uf[a] = find(uf[a]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			int max = Math.max(a, b);
			int min = Math.min(a, b);
			
			uf[max] = min;
		}
	}

}
