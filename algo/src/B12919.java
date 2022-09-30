import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12919 {
	static int canChange = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder S = new StringBuilder();
		StringBuilder T = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		S.append(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		T.append(st.nextToken());
		
		dfs(S, T);
		
		System.out.println(canChange);
	}

	private static void dfs(StringBuilder s, StringBuilder t) {
		if (canChange == 1) return;
		
		if (s.length() == t.length()) {
			if (s.toString().equals(t.toString())) {
				canChange = 1;
				return;
			}
			return;
		}
		
		StringBuilder ns = new StringBuilder(s);
		
		ns.append("A");
		dfs(ns, t);
		
		ns = new StringBuilder(s);
		ns.append("B");
		ns.reverse();
		dfs(ns, t);
		
	}
}






