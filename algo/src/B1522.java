import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1522 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int result = Integer.MAX_VALUE;
		
		char[] ab = st.nextToken().toCharArray();
		
		int aCount = 0;
		for (char c : ab) if (c == 'a') aCount++;
		
		int currentB = 0;
		
		int left = 0;
		int right = aCount;
		for (int i = 0; i < right; i++) {
			if (ab[i] == 'b') currentB++;
		}
		
		while (left < ab.length) {
			if (ab[left++] == 'b') currentB--;
			if (ab[right++ % ab.length] == 'b') currentB++;
			
			result = result < currentB ? result : currentB;
		}
		
		System.out.println(result);
	}

}
