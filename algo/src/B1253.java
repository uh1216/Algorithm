import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B1253 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		HashMap<Long, Integer> nums = new HashMap<>();
		long[] n = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			n[i] = Long.parseLong(st.nextToken());
			
			if (nums.containsKey(n[i])) nums.put(n[i], nums.get(n[i])+1);
			else nums.put(n[i], 1);
		}
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				long key = n[i] + n[j];
				
				if (n[i] == 0 || n[j] == 0) {
					if (nums.get(key) > 1) {
						result += nums.get(key);
						nums.put(key, 0);
					}
					else nums.put(key, 0);
				}
				
				if (nums.containsKey(key) && nums.get(key) != 0) {
					 result += nums.get(key);
					 nums.put(key, 0);
				}
			}
		}
		
		System.out.println(result);
	}

}
