package ws._0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3289 {
	//유니온-파인드 사용할 배열
	static int[] uf;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		//테스트 케이스 수 만큼 반복
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			//각 부분 집합의 루트를 자신으로 해줌 
			uf = new int[N+1];
			for (int i = 1; i <= N; i++) {
				uf[i] = i;
			}
			
			sb.append("#").append(test_case).append(" ");
			
			//연산을 입력받고
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int o = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				//연산이 0이라면, 입력받은 a, b집합을 유니온함
				if (o == 0) union(a, b);
				//연산이 1이라면
				else if (o == 1) {
					//입력받은 a, b 각각의 루트를 찾고 같다면 1을, 다르다면 0을 스트링 빌더에 추가함
					if (find(a) == find(b)) sb.append(1);
					else sb.append(0);
				}
				
				System.out.println(Arrays.toString(uf));
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	//한 원소의 루트를 찾는 파인드 함수
	private static int find(int a) {
		if (uf[a] == a) return a;
		else return uf[a] = find(uf[a]);
	}

	//두 원소를 합해 하나의 집합으로 만드는 유니온 함수
	private static void union(int a, int b) {
		//두 원소의 루트를 찾고
		a = find(a);
		b = find(b);
		
		//두 원소의 루트가 다를때만 합침
		if (a != b) {
			int max = Math.max(a, b);
			int min = Math.min(a, b);
			
			uf[max] = min;
		}
	}

}
