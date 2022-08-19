package ws._0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B17135 {
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		LinkedList<int[]> map = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] tem = new int[M];
			for (int j = 0; j < M; j++) {
				tem[j] = Integer.parseInt(st.nextToken());
			}
			map.add(tem);
		}
		
		com(0, 0, N, M, D, map, new boolean[M]);
		System.out.println(result);
	}

	private static void com(int start, int count, int N, int M, int D, LinkedList<int[]> map, boolean[] visited) {
		if (count == 3) {
			LinkedList<int[]> n_map = new LinkedList<>();
			for (int i = 0; i < map.size(); i++) {
				n_map.add(map.get(i).clone());
			}
			int p = play(visited, N, M, D, n_map);
			result = p > result ? p : result;
			return;
		}
		
		for (int i = start; i < M; i++) {
			if (!visited[i]) {
				visited[i] = true;
				com(i+1, count+1, N, M, D, map, visited);
				visited[i] = false;
			}
		}
	}
	
	private static int play(boolean[] archer, int N, int M, int D, LinkedList<int[]> map) {
		int count = 0;
		int[] row = {0, -1, 0};
		int[] col = {-1, 0, 1};
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < archer.length; j++) {
				int[] a = new int[2];
				if (archer[j]) {
					a[0] = N;
					a[1] = j;
				}
				else continue;
				
				boolean[][] visited = new boolean[N][M];
				LinkedList<int[]> queue = new LinkedList<>();
				queue.add(new int[] {a[0]-1, a[1]});
				visited[a[0]-1][a[1]] = true;
				
				while (!queue.isEmpty()) {
					int[] e = queue.poll();
					if (map.get(e[0])[e[1]] == 1) {
						count += 1;
						map.get(e[0])[e[1]] = -1;
					}
					else if (map.get(e[0])[e[1]] == -1) {
						continue;
					}
					else {
						for (int k = 0; k < 3; k++) {
							int n_row = e[0] + row[k];
							int n_col = e[1] + col[k];
							int d = Math.abs(a[0] - n_row) + Math.abs(a[1] - n_col);
							if (d <= D && 0 <= n_row && 0 <= n_col && n_col < M && !visited[n_row][n_col]) {
								visited[n_row][n_col] = true;
								queue.add(new int[] {n_row, n_col});
							}
						}
					}
				}
			}
			
			map.remove(map.size()-1);
			map.offerFirst(new int[M]);
			
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if (map.get(j)[j2] == -1) map.get(j)[j2] = 0;
				}
			}
		}
		return count;
	}
}


