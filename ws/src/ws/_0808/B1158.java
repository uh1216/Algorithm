package ws._0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
 		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Integer> result = new ArrayList<>();
		
		LinkedList<Integer> circle = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			circle.add(i);
		}
		
		int l = K-1;
		while (result.size() != N) {
			result.add(circle.remove(l));
			if (circle.isEmpty()) {
				break;
			}
			l = (l + K-1) % circle.size();
		}
		
		sb.append('<');
		for (int i = 0; i < N; i++) {
			if (i != N-1) {
				sb.append(result.get(i)).append(',').append(" ");
			}
			else {
				sb.append(result.get(i));
			}
		}
		sb.append('>');
		
		System.out.println(sb.toString());
	}

}
