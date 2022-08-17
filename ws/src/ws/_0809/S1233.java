package ws._0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1233 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			// 계산 가능한지 판단하는 변수
			boolean canCal = true; 
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			//트리를 배열에 담음
			char[] o = new char[N+1];
			for (int i = 1; i <= N; i++) {
				String[] tem = br.readLine().split(" ");
				o[i] = tem[1].charAt(0);
			}
			
			//맨 아래 노드부터 해당 노드와 형제노드가 부모노드로 계산이 되는지 확인
			for (int i = N; i >= 3; i -= 2) {
				// 자식 노드 둘은 숫자여야 하고, 부모노드는 연산자여야 한다. 
				if (Character.isDigit(o[i]) && Character.isDigit(o[i-1]) && (o[i/2] == '*' || o[i/2] == '+' || o[i/2] == '-' || o[i/2] == '/')) {
					//조건에 맞을 경우 부모노드를 숫자로 바꿔줌
					o[i/2] = '1'; 
				}
				else {
					// 조건이 아닐 경우 연산 불가능, 반복문 탈출해줌
					sb.append("#").append(test_case).append(" ").append(0).append("\n");
					canCal = false;
					break;
				}
			}
			// 반복문 탈출하지 않았을 경우 연산 가능한 것으로 판단
			if (canCal) {
				sb.append("#").append(test_case).append(" ").append(1).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}
