package algo.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B13549 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] visited = new int[100001];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
		
		int result = Integer.MAX_VALUE;
		
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(new int[] {N, 0});
		
		while(!queue.isEmpty()) {
			int[] x = queue.poll();
			
			if (x[0] == K) {
				result = result > x[1] ? x[1] : result;
				continue;
			}
			
			if (visited[x[0]] > x[1]) {
				visited[x[0]] = x[1];
				if (0 <= x[0]-1) {
					queue.add(new int[] {x[0]-1, x[1]+1});
				}
				if (x[0]+1 <= 100000) {
					queue.add(new int[] {x[0]+1, x[1]+1});
				}
				if (x[0]*2 <= 100000) {
					queue.add(new int[] {x[0]*2, x[1]});
				}
			}
		}
		
		System.out.println(result);
	}
	
	
}
