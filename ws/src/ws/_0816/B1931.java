package ws._0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B1931 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> meetings = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meetings.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		meetings.sort(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] < o2[1] ? -1 : o1[1] == o2[1] ? o1[0] < o2[0] ? -1 : 1 : 1;
			}
		});
		
		int result = 1;
		int end = meetings.get(0)[1];
		for (int i = 1; i < meetings.size(); i++) {
			if (end <= meetings.get(i)[0]) {
				end = meetings.get(i)[1];
				result++;
			}
		}
		
		System.out.println(result);
	}

}
