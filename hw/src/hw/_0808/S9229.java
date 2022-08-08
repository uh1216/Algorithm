package hw._0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S9229 {

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
			
			int[] snack = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = -1;
			for (int i = 0; i < snack.length-1; i++) {
				for (int j = i+1; j < snack.length; j++) {
					int w = snack[i] + snack[j];
					if (w <= M) {
						max = max < w ? w : max;
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
}
