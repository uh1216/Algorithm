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
		
		//리스트로 맵 생성 후 입력받음
		LinkedList<int[]> map = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] tem = new int[M];
			for (int j = 0; j < M; j++) {
				tem[j] = Integer.parseInt(st.nextToken());
			}
			map.add(tem);
		}
		
		//조합 함수 호출
		com(0, 0, N, M, D, map, new boolean[M]);
		System.out.println(result);
	}
	
	//조합 함수
	private static void com(int start, int count, int N, int M, int D, LinkedList<int[]> map, boolean[] visited) {
		//궁수가 3명인 경우의 수를 구하면 게임을 플레이 하는 play함수 호출
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
	
	//궁수 조합으로 게임을 진행하는 함수
	private static int play(boolean[] archer, int N, int M, int D, LinkedList<int[]> map) {
		int count = 0;
		
		//행렬 벡터
		int[] row = {0, -1, 0};
		int[] col = {-1, 0, 1};
		//맵 맨위 적들이 맨 밑으로 사라질 때까지 반복
		for (int i = 0; i < N; i++) {
			//열을 돌면서 궁수가 해당 위치에 있을 경우
			for (int j = 0; j < archer.length; j++) {
				int[] a = new int[2];
				if (archer[j]) {
					a[0] = N;
					a[1] = j;
				}
				else continue;
				
				//bfs로 궁수가 공격할 수 있는 적이 궁수의 공격 범위내에 있는지 가장 왼쪽, 위, 오른쪽 순으로 탐색
				boolean[][] visited = new boolean[N][M];
				LinkedList<int[]> queue = new LinkedList<>();
				queue.add(new int[] {a[0]-1, a[1]});
				visited[a[0]-1][a[1]] = true;
				
				while (!queue.isEmpty()) {
					int[] e = queue.poll();
					//가장 가깝고 왼쪽인 적이 있으면 해치운 적 +1 해주고 중복 방지를 위해 map에 공격받은 적을 -1로 표시해주고 반복문 탈출
					if (map.get(e[0])[e[1]] == 1) {
						count += 1;
						map.get(e[0])[e[1]] = -1;
						break;
					}
					//가장 가깝고 왼쪽인 적이 이미 공격받았으면, 아무 것도 하지 않고 반복문 탈출
					else if (map.get(e[0])[e[1]] == -1) {
						break;
					}
					//궁수의 공격 범위 전부를 탐색하도록 bfs에 더해줌
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
			//맵 가장 밑의 적들을 없애줌
			map.remove(map.size()-1);
			
			//맵 제일 위에 0으로 이루어진 요소 넣어줌(인덱스 오류 방지를 위해)
			map.offerFirst(new int[M]);
			
			//공격을 받아 -1로 바꿔놓았던 적들 0으로 다 없애줌
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if (map.get(j)[j2] == -1) map.get(j)[j2] = 0;
				}
			}
		}
		//해치운 적의 수를 리턴
		return count;
	}
}



