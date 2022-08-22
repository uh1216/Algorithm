package hw._0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class J1828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		LinkedList<int[]> materials = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			materials.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		materials.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] < o2[1] ? 1 : -1;
			}
		});
		
		int result = 1;
		int low = materials.get(0)[0];
		int high = materials.get(0)[1];
		for (int i = 1; i < materials.size(); i++) {
			if (low <= materials.get(i)[1] && materials.get(i)[1] <= high) {
				low = low < materials.get(i)[0] ? materials.get(i)[0] : low;
				high = high < materials.get(i)[1] ? high : materials.get(i)[1];
			}
			else {
				result++;
				low = materials.get(i)[0];
				high = materials.get(i)[1];
			}
		}
		System.out.println(result);
	}

}
