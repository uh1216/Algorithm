import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17471 {
	static int result = Integer.MAX_VALUE;
	static int N;
	
	static int[][] conn;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		p = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		conn = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < n; j++) {
				conn[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//나올 수 있는 구역 조합 구하기
		for (int i = 1; i < N; i++) {
			combi(1, 0, new boolean[N+1], i);
		}
		
		if (result == Integer.MAX_VALUE) result = -1;
		
		System.out.println(result);
	}

	//조합 함수
	private static void combi(int start, int count, boolean[] visited, int t) {
		if (count == t) {
			int[] s1 = new int[N];
			int[] s2 = new int[N];
			
			int sizes1 = 0;
			int sizes2 = 0;
			for (int i = 1; i <= N; i++) {
				if (visited[i]) s1[sizes1++] = i;
				else s2[sizes2++] = i;
			}
			
			//조합을 구했으면, 각 선거구의 구역들이 이어져있는지 확인
			if (isConn(s1, s2)) {
				//이어져있다면 선거구의 인구수 차이를 구하고 결과값에 넣는다
				int r = pd(s1, s2);
				result = result > r ? r : result;
			};

			return;
		}
		
		for (int i = start; i <= N; i++) {
			visited[i] = true;
			combi(i+1, count+1, visited, t);
			visited[i] = false;
		}
	}

	private static int pd(int[] s1, int[] s2) {
		int sum1 = 0;
		for (int i = 0; i < s1.length; i++) {
			sum1 += p[s1[i]]; 
		}
		
		int sum2 = 0;
		for (int i = 0; i < s2.length; i++) {
			sum2 += p[s2[i]];
		}
		
		return Math.abs(sum1 - sum2);
	}

	//선거구의 구역들이 이어져있는지 확인하는 bfs 함수
	private static boolean isConn(int[] s1, int[] s2) {
		
		//s1이 연결되어 있는지 확인
		HashSet<Integer> set = new HashSet<>();
		int start = 0;
		for (int i = 0; i < s1.length; i++) {
			if (s1[i] != 0) set.add(s1[i]);
			if (start == 0 && s1[i] != 0) start = s1[i];
		}
		if (!bfs(start, set)) return false;
		
		//s2가 연결되어 있는지 확인
		set = new HashSet<>();
		start = 0;
		for (int i = 0; i < s2.length; i++) {
			if (s2[i] != 0) set.add(s2[i]);
			if (start == 0 && s2[i] != 0) start = s2[i];
		}
		if (!bfs(start, set)) return false;
		
		return true;
	}

	private static boolean bfs(int start, HashSet<Integer> s) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int i = 0; i < N; i++) {
				for (int b : conn[now]) {
					if (s.contains(b) && !visited[b]) {
						visited[b] = true;
						queue.add(b);
					}
				}
			}
		}
		
		int size = 0;
		for (int i = 0; i < visited.length; i++) if (visited[i]) size++;
		
		return s.size() == size;
	}

}
