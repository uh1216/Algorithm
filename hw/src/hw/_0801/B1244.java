package hw._0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1244 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] sw = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			sw[i] = Integer.parseInt(st.nextToken());
		}
		
		int student = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < student; i++) {
			int g, num;
			st = new StringTokenizer(br.readLine());
			g = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken()) - 1;
			
			if (g == 1) {
				for (int j = num; j < n; j += num + 1) {
					sw[j] = sw[j] == 0 ? 1 : 0;
				}
			}
			else {
				sw[num] = sw[num] == 0 ? 1 : 0;
				
				int l = num;
				int r = num;
				while (0 <= l && r < n && sw[l] == sw[r]) {
					sw[l] = sw[l] == 0 ? 1 : 0;
					sw[r] = sw[r] == 0 ? 1 : 0;
					l--;
					r++;
				}
			}
		}
		
		for (int i = 1; i <= sw.length; i++) {
			System.out.print(sw[i-1] + " ");
			
			if (i % 20 == 0) {
				System.out.println();
			}
		}
	}
}
