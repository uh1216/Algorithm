import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S5643 {
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			
			//커지는 방향의 학생들
			boolean[][] talld = new boolean[N+1][N+1]; 
					
			//작아지는 방향의 학생들
			boolean[][] smalld = new boolean[N+1][N+1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				talld[a][b] = true;
				smalld[b][a] = true;
			}
			
			int result = 0;
			for (int i = 1; i < N+1; i++) {
				
				//bfs로 돌면서 다른 노드들이 다 탐색되는지 확인
				int tall = bfs(i, talld);
				int small = bfs(i, smalld);
				
				if (tall + small == N+1) result++;
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int bfs(int i, boolean[][] people) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(i);
		
		boolean[] visited = new boolean[N+1];
		visited[i] = true;
		
		//방향에 맞게 탐색
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int j = 1; j < N+1; j++) {
				if (people[now][j] && !visited[j]) {
					visited[j] = true;
					queue.add(j);
				}
			}
		}
		
		int count = 0;
		for (int j = 0; j < visited.length; j++) {
			if (visited[j]) count++;
		}
		
		return count;
	}
}






