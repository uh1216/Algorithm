import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17472 {
	static int N;
	static int M;
	
	static boolean[][] visited;
	static int[] rows = {1, -1, 0, 0};
	static int[] cols = {0, 0, 1, -1};
	
	static List<List<P>> island;
	
	static class P {
		int r;
		int c;
		
		public P(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
	
	static class Bridge {
		int s;
		int e;
		int len;
		
		public Bridge(int s, int e, int len) {
			this.s = s;
			this.e = e;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Bridge [s=" + s + ", e=" + e + ", len=" + len + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//지도 정보 저장
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//각 섬에서 다리에 이을 수 있는 칸만 저장
		island = new ArrayList<>();
		
		visited = new boolean[N][M];
		
		//섬찾고 각 섬의 다리를 이을 수 있는 칸 찾기
		int islandCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					findIsland(map, new P(i, j), islandCount);
					islandCount++;
				}
			}
		}
		
//		for (int i = 0; i < island.size(); i++) {
//			System.out.println(island.get(i));
//		}
		
		List<Bridge> bridges = new ArrayList<>();
		
		//각 섬에서 다른 섬까지 연결할 수 있는 다리 만들기
		//i는 전체 반복
		for (int i = 0; i < island.size()-1; i++) {
			//j는 island의 i번째 항목들
			for (int j = 0; j < island.get(i).size(); j++) {
				//k는 i+1의 전체 반복
				for (int k = i+1; k < island.size(); k++) {
					//l은 island의 k번째 항목들
					for (int l = 0; l < island.get(k).size(); l++) {
						P nowI = island.get(i).get(j);
						P otherI = island.get(k).get(l);
						
						if (nowI.r == otherI.r) {
							
							
							int len = Math.abs(nowI.c - otherI.c);
							if (len > 1) bridges.add(new Bridge(i, k, len));
						}
						else if (nowI.c == otherI.c) {
							int len = Math.abs(nowI.r - otherI.r);
							if (len > 1) bridges.add(new Bridge(i, k, len));
						}
					}
				}
			}
		}
		
		System.out.println(bridges);
	}
	

	private static void findIsland(int[][] map, P p, int islandCount) {
		Queue<P> queue = new ArrayDeque<>();
		queue.add(p);
		
		visited[p.r][p.c] = true;
		
		while (!queue.isEmpty()) {
			P now = queue.poll();
			
			boolean isSide = false;
			
			for (int i = 0; i < 4; i++) {
				int nr = now.r + rows[i];
				int nc = now.c + cols[i];
				
				if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]) {
					if (map[nr][nc] == 0) {
						isSide = true;
						continue;
					}
					visited[nr][nc] = true;
					queue.add(new P(nr, nc));
				}
			}
			
			if (island.size() <= islandCount) {
				island.add(new ArrayList<>());
			}
			
			if (isSide) island.get(islandCount).add(new P(now.r, now.c));
		}
	}

}
