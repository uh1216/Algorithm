import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken())-1;
		
		if (N == 0) {
			System.out.println('*');
			return;
		}
		
		int start = N * 4 + 1;
		char[] odd = new char[start];
		char[] even = new char[start];
		
		String[] all = new String[start / 2 + 1];
		
		Arrays.fill(even, '*');
		
		Arrays.fill(odd, ' ');
		odd[0] = '*';
		odd[start-1] = '*';
		
		for (int i = 0; i <= start / 2 + 1; i++) {
			if (i <= start / 2 ) {
				if (i % 2 == 0) {
					all[i] = new String(even);
					even[i+1] = ' ';
					even[start-1-(i+1)] = ' ';
				}
				else {
					all[i] = new String(odd);
					odd[i+1] = '*';
					odd[start-1-(i+1)] = '*';
				}
			}
		}
		
		for (int i = 0; i < all.length; i++) {
			sb.append(all[i]).append("\n");
		}
		for (int i = all.length-2; i >= 0; i--) {
			sb.append(all[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
