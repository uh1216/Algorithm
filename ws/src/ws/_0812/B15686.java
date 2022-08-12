package ws._0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15686 {
	private static ArrayList<int[]> houses;
	private static ArrayList<int[]> chickens;
	private static int M;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//도시에 있는 치킨집과 가정집들 위치를 리스트로 저장
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					houses.add(new int[] {i, j});
				}
				else if (temp == 2) {
					chickens.add(new int[] {i, j});
				}
			}
		}
		
		boolean[] visited = new boolean[chickens.size()];
		//조합 호출
		comb(visited, 0, 0);
		
		System.out.println(result);
	}
	
	private static void comb(boolean[] visited, int start, int count) {
		//치킨집의 개수가 M일 경우 재귀 함수 종료
		if (count == M) {
			//해당되는 치킨집 위치들을 새로운 배열에 넣어놓음
			ArrayList<int[]> t_chicken = new ArrayList<>();
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					t_chicken.add(chickens.get(i));
				}
			}
			
			//새로만든 배열을 통해 치킨거리 계산 함수 호출
			int c = cal(t_chicken);
			//치킨 거리가 최소일 경우 결과값과 교환
			result = c > result ? result : c;
			return;
		}
		
		//조합 재귀호출
		for (int i = start; i < chickens.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb(visited, i+1, count+1);
				visited[i] = false;
			}
		}
	}
	
	//각 가정집과 치킨집간의 거리를 구해 치킨거리를 구하는 함수
	private static int cal(ArrayList<int[]> chickens) {
		int min = 0;
		for (int i = 0; i < houses.size(); i++) {
			int[] h = houses.get(i);
			int m1 = Integer.MAX_VALUE;
			for (int j = 0; j < chickens.size(); j++) {
				int[] c = chickens.get(j);
				
				int temp = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
				m1 = m1 > temp ? temp : m1;
			}
			min += m1;
		}
		return min;
	}

}
