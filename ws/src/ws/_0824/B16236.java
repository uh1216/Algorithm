package ws._0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236 {
	//움직인 총 횟수
	static int move = 0;
	//아기 상어의 크기
	static int size = 2;
	//상어가 먹은 먹이 수
	static int eat = 0;
	//상어가 먹을 물고기가 있는지 확인하는 변수
	static boolean canEat = true;
	static int N;
	static int[] row = {-1, 0, 0, 1};
	static int[] col = {0, -1, 1, 0};
	//상어의 현재 위치
	static Point bs;
	
	//위치를 나타내기 위해 사용 할 클래스
	static class Point {
		int r;
		int c;
		
		public Point() {}
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		//수족관 크기
		int[][] aqua = new int[N][N];
		
		//수족관 상태를 입력받음
		for (int i = 0; i < aqua.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < aqua.length; j++) {
				aqua[i][j] = Integer.parseInt(st.nextToken());
				//해당 값이 9일 경우 상어의 초기 위치 넣어줌
				if (aqua[i][j] == 9) {
					bs = new Point(i, j);
					aqua[i][j] = 0;
				}
			}
		}
		
		//상어가 먹을 먹이가 있는 동안 반복
		while (canEat) {
			//bfs로 상어가 먹을 먹이가 있는지 탐색
			bfs(new Point(bs.r, bs.c), aqua);
		}
		
		//총 움직인 거리 출력
		System.out.println(move);
	}
	
	//상어가 먹을 먹이가 있는지 탐색
	private static boolean bfs(Point bsl, int[][] aqua) {
		//하나의 먹이를 향해 이동하는데 걸린 시간
		int count = 0;
		
		//먹이가 있는지 확인하는 변수
		canEat = false;
		
		//큐를 선언하고 초기값 넣어줌
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(bsl);
		
		//중복 방지 위해 불린배열로 방문한 곳 체크
		boolean[][] visited = new boolean[N][N];
		visited[bsl.r][bsl.c] = true;
		
		//다음 먹이를 먹는 곳 위치 즉, 다음 상어의 위치를 저장하기 위해 가장 큰 값으로 초기화
		bs.r = N;
		bs.c = N;
		while (!queue.isEmpty()) {
			int qSize = queue.size();
			
			//위에서 저장한 사이즈만큼만 반복 - 같은 거리 단위로 돌리기 위해
			while (--qSize >= 0) {
				//큐에 있는 맨 앞의 값 poll
				bsl = queue.poll();
				
				//이동한 값이 0보다 크고 상어 사이즈 보다 작으면 먹이이므로, 현재 상어의 위치 값 먹이의 위치로 변경해줌
				//같은 거리에 있는 모든 영역들을 탐색해야 함
				if (0 < aqua[bsl.r][bsl.c] && aqua[bsl.r][bsl.c] < size) {
					if (bs.r > bsl.r) {
						bs.r = bsl.r;
						bs.c = bsl.c;
					}
					else if (bs.r == bsl.r) {
						bs.c = bs.c < bsl.c ? bs.c : bsl.c;
					}
					//먹이 있음
					canEat = true;
				}
				
				//4방향 탐색하고 갈 수 있는 곳 큐에 추가
				for (int i = 0; i < 4; i++) {
					int bsR = bsl.r + row[i];
					int bsC = bsl.c + col[i];
					
					if (0 <= bsR && bsR < N && 0 <= bsC && bsC < N && aqua[bsR][bsC] <= size && !visited[bsR][bsC]) {
						visited[bsR][bsC] = true;
						queue.add(new Point(bsR, bsC));
					}
				}
			}
			
			//먹이가 있다면
			if (canEat) {
				//움직인 횟수 전체 움직인 횟수에 더해줌
				move += count;
				//먹이 먹고난 자리 0으로 바꿔줌
				aqua[bs.r][bs.c] = 0;
				
				//먹은 물고기 수 더해주고, 먹은 물고기 수와 현재 사이즈가 같다면 사이즈를++ 해주고 먹은 물고기 수 초기화 
				if (++eat == size) {
					eat = 0;
					size++;
				}
				break;
			}
			//움직인 횟수++
			count++;
		}
		
		//먹이가 있는지 여부 리턴
		return canEat;
	}
}






