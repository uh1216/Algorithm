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
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			LinkedList<LinkedList<Integer>> magnets = new LinkedList<>();
			for (int i = 0; i < 4; i++) {
				magnets.add(new LinkedList<>());
			}
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnets.get(i).add(Integer.parseInt(st.nextToken()));
				}
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());
				
				Queue<Magnet> ro = new ArrayDeque<>();
				ro.add(new Magnet(m, d));
				
				boolean[] visited = new boolean[4];
				
				while (!ro.isEmpty()) {
					Magnet magnet = ro.poll();
					
					visited[magnet.no] = true;
					
					if (0 <= magnet.no-1 && !visited[magnet.no-1] && magnets.get(magnet.no).get(6) != magnets.get(magnet.no-1).get(2)) {
						ro.add(new Magnet(magnet.no-1, -magnet.d));
					}
					
					if (magnet.no+1 < 4 && !visited[magnet.no+1] && magnets.get(magnet.no).get(2) != magnets.get(magnet.no+1).get(6)) {
						ro.add(new Magnet(magnet.no+1, -magnet.d));
					}
					
					rotate(magnets, magnet.no, magnet.d);
					
				}
			}
			
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
