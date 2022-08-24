package ws._0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S3124 {
	//결과 값 변수
	static long result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		//테스트 케이스만큼 반복
		for (int test_case = 1; test_case <= T; test_case++) {
            result = 0;
            
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			//그래프를 표현 할 인접리스트 선언
			ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<>());
			}
			
			//인접 리스트의 값들을 채워줌
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				graph.get(A).add(new int[] {B, C});
				graph.get(B).add(new int[] {A, C});
			}
			
			//프림 알고리즘을 수행 할 함수 호출
			prim(V, 1, graph);

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	//프림 알고리즘
	private static void prim(int V, int node, ArrayList<ArrayList<int[]>> graph) {
		//노드의 개수 카운트
		int count = 1;
		//방문한 노드 확인하는 배열
		boolean[] visited = new boolean[V+1];
		visited[node] = true;
		//최소 가중치를 가진 간선을 찾기위한 힙 선언
		PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
			
			//힙에 객체가 들어가므로, 가중치를 기준으로 정렬하도록 오버라이드
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		//현재 개수가 전체 노드의 개수가 되면 반복문 종료
		while (count != V) {
			//현재 노드의 인접 노드들을 가져옴
			ArrayList<int[]> nextE = graph.get(node);
			//방문하지 않은 인접 노드들을 최소힙에 삽입하여 가장 작은 가중치를 가진 노드를 찾음
			for (int i = 0; i < nextE.size(); i++) {
                if (!visited[nextE.get(i)[0]]) heap.add(nextE.get(i));
			}
			
			//방문하지 않은 노드가 나올때까지 가장 앞의 값 빼줌
			while (visited[heap.peek()[0]]) {
				heap.poll();
			}
			//방문하지 않은 가중치가 가장 작은 다음 노드
			int[] next = heap.poll();
			
			//방문 배열을 true로 바꿔줌
			visited[next[0]] = true;
			//다음 노드 변수로 할당
			node = next[0];
			//결과값에 가중치를 더해줌
			result += next[1];
			//현재 노드 개수++
			count++;
		}
	}

}





