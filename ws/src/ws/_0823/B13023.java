package ws._0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B13023 {
	//결과값 변수
	static boolean result = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//그래프의 상태를 표현하기 위한 인접 리스트 선언
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		//인접 리스트에 값 더해줌
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		//1부터 N까지 dfs로 탐색
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			visited[i] = true;
			dfs(0, i, visited, graph);
		}
		
		if (result) System.out.println(1);
		else System.out.println(0);
	}

	//문제로 주어진 조건에 만족하는 관계가 있는지 dfs로 확인하는 함수
	private static void dfs(int count, int start, boolean[] visited, ArrayList<ArrayList<Integer>> graph) {
		//조건을 만족하는 관계가 하나라도 있다면 다른 모든 함수 종료 
		if (result) return;
		
		//조건을 만족한다면, 결과값 변수를 true로 만들고 종료
		if (count == 4) {
			result = true;
			return;
		}
		
		//한 정점에서 방문하지 않은 다른 정점들로 가는 길이 있는지 확인하면서 끝까지 탐색 
		ArrayList<Integer> c = graph.get(start);
		for (Integer i : c) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(count+1, i, visited, graph);
				visited[i] = false;
			}
		}
	}
}
