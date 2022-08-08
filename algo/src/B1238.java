import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B1238 {
	private static HashMap<Integer, ArrayList<int[]>> roads;
	private static int X;
	private static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		result = new int[N+1];
		for (int i = 1; i <= N; i++) {
			result[i] = Integer.MAX_VALUE;
		}
		
		roads = new HashMap<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			
			if (roads.get(S) == null) {
				roads.put(S, new ArrayList<>());
				roads.get(S).add(new int[] {E, T});
			}
			else {
				roads.get(S).add(new int[] {E, T});
			}
		}
		
		for (int i = 1; i <= N; i++) {
			boolean[] visited = new boolean[N+1];
			visited[i] = true;
			go(visited, i, i, 0);
		}
		
		7 17 19 22 
		16
		
		System.out.println(Arrays.toString(result));
		
		int max = 0;
		for (int i = 0; i < result.length; i++) {
			max = max < result[i] ? result[i] : max;
		}
		System.out.println(max);
	}
	
	private static void go(boolean[] visited, int start, int now, int time) {
		if (now == X) {
			back(new boolean[visited.length], start, now, time);
			return;
		}
		
		visited[now] = true;
		
		ArrayList<int[]> canGo = roads.get(now);
		for (int i = 0; i < canGo.size(); i++) {
			int n_now = canGo.get(i)[0];
			int n_time = canGo.get(i)[1];
			
			if (!visited[n_now]) {
				go(visited, start, n_now, time + n_time);
			}
		}
	}
	
	private static void back(boolean[] visited, int start, int now, int time) {
		if (now == start) {
			System.out.println(time);
			result[start] = result[start] > time ? time : result[start];
			return;
		}
		
		visited[now] = true;
		
		ArrayList<int[]> canGo = roads.get(now);
		for (int i = 0; i < canGo.size(); i++) {
			int n_now = canGo.get(i)[0];
			int n_time = canGo.get(i)[1];
			
			if (!visited[n_now]) {
				back(visited, start, n_now, time + n_time);
			}
		}
	}
}
