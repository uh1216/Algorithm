package ws._0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759 {
	static StringBuilder sb = new StringBuilder();
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		//암호의 자리수
		L = Integer.parseInt(st.nextToken());
		//암호에 사용될 것이라 추측되는 알파벳들
		int C = Integer.parseInt(st.nextToken());
		
		//알파벳을 넣을 배열
		char[] alpha = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		//사전 순서대로 출력해야 하니 알파벳 배열을 정렬함
		Arrays.sort(alpha);
		
		//암호로 사용될 수 있는 경우의 수를 만들기 위한 조합 함수 호출
		com(0, 0, alpha, new boolean[C]);
		
		//경우의 수들 출력
		System.out.println(sb.toString());
	}
	
	//조합 함수
	private static void com(int start, int count, char[] alpha, boolean[] visited) {
		//암호로 사용될 알파벳 후보 중 L개의 알파벳을 뽑으면 함수 종료
		if (count == L) {
			//뽑은 알파벳을 넣기 위한 배열
			char[] a = new char[L];
			//모음 개수를 담기 위한 변수
			int v = 0;
			//자음 개수를 담기 위한 변수
			int c = 0;
			int index = 0;
			//불린 배열의 값이 true이면 뽑은 알파벳
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					a[index] = alpha[i];
					//해당 알파벳이 모음일 경우 모음 변수 + 1
					if (alpha[i] == 'a' || alpha[i] == 'e' || alpha[i] == 'i' || alpha[i] == 'o' || alpha[i] == 'u') v++;
					//아닐 경우 자음 변수 + 1
					else c++;
					index++;
				}
			}
			
			//모음이 최소 1개 이상, 자음이 2개 이상일 경우
			if (v >= 1 && c >= 2) {
				//해당 값을 스트링 빌더에 넣어줌
				sb.append(new String(a)).append("\n");
			}
			return;
		}
		
		//조합 만들기
		for (int i = start; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				com(i+1, count+1, alpha, visited);
				visited[i] = false;
			}
		}
	}
}




