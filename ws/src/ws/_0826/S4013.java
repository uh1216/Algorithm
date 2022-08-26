package ws._0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S4013 {
	
	//몇번째 자석인지, 어느 방향으로 돌릴지 저장할 자석 클래스
	static class Magnet {
		int no;
		int d;
		
		public Magnet(int no, int d) {
			this.no = no;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		//테스트케이스 개수만큼 반복
		for (int test_case = 1; test_case <= T; test_case++) {
			//자석을 돌릴 횟수
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			// 각 자석들과 그 자석들의 각 극을 저장하는 리스트
			LinkedList<LinkedList<Integer>> magnets = new LinkedList<>();
			for (int i = 0; i < 4; i++) {
				magnets.add(new LinkedList<>());
			}
			
			//자석들 정보 입력받음
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnets.get(i).add(Integer.parseInt(st.nextToken()));
				}
			}
			
			//K번 동안 반복
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());
				
				//큐를 이용하여 돌아갈 자석들 저장
				Queue<Magnet> ro = new ArrayDeque<>();
				ro.add(new Magnet(m, d));
				
				//이미 돌아간 자석 저장
				boolean[] visited = new boolean[4];
				
				//큐가 빌때까지 반복
				while (!ro.isEmpty()) {
					Magnet magnet = ro.poll();
					
					visited[magnet.no] = true;
					
					//전 자석 확인하고 돌아갈 자석이면 큐에 저장
					if (0 <= magnet.no-1 && !visited[magnet.no-1] && magnets.get(magnet.no).get(6) != magnets.get(magnet.no-1).get(2)) {
						ro.add(new Magnet(magnet.no-1, -magnet.d));
					}
					//다음 자석 확인하고 돌아갈 자석이면 큐에 저장					
					if (magnet.no+1 < 4 && !visited[magnet.no+1] && magnets.get(magnet.no).get(2) != magnets.get(magnet.no+1).get(6)) {
						ro.add(new Magnet(magnet.no+1, -magnet.d));
					}
					//자석 돌리는 함수
					rotate(magnets, magnet.no, magnet.d);
					
				}
			}
			
			//점수 계산
			int result = 0;
			for (int i = 0; i < 4; i++) {
				if (magnets.get(i).get(0) == 1) {
					result += Math.pow(2, i);
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	//자석 돌리는 함수
	private static void rotate(LinkedList<LinkedList<Integer>> magnets, int m, int d) {
		if (d == 1) {
			magnets.get(m).addFirst(magnets.get(m).getLast());
			magnets.get(m).pollLast();
		}
		else {
			magnets.get(m).add(magnets.get(m).getFirst());
			magnets.get(m).poll();
		}
	}
}
