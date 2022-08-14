package ws._0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 재귀를 이용하여 4개의 배열의 크기가 1이 될때까지 작아지는 형태로 코드를 작성하였습니다.
 */
public class B1074 {
	private static int N;
	private static int result;
	private static int R;
	private static int C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//배열 한 행의 크기 2의 N제곱
		N = (int) Math.pow(2, N);
		
		search(0);
		System.out.println(result);
	}
	
	//재귀 함수
	private static void search(int count) {
		// 배열의 크기가 1이 되면, 해당 위치를 발견한 것으로 생각하고 함수 탈출, 현재까지 더한 값 리턴
		if (N == 1) {
			result = count;
			return;
		}
		
		// 현재 배열을 4개의 배열로 나누고 목표하는 좌표가 어디에 속하는지 확인
		
		//1사분면일 경우
		if (0 <= R && R < N/2 && 0 <= C && C < N/2) {
			//배열을 1/4해줌
			N /= 2;
			//재귀 재호출
			search(count);
		}
		//2사분면일 경우
		else if (0 <= R && R < N/2 && N/2 <= C && C < N) {
			N /= 2;
			//목표 열을 N만큼 빼줌
			C -= N;
			//재귀 재호출, 지나간 행렬의 크기만큼 카운트에 더해줌
			search(count + N * N);
		}
		//3사분면
		else if (N/2 <= R && R < N && 0 <= C && C < N/2) {
			N /= 2;
			//목표 행을 N만큼 빼줌
			R -= N;
			//재귀 재호출, 지나간 행렬의 크기만큼 카운트에 더해줌
			search(count + N * N * 2);
		}
		//4사분면
		else if (N/2 <= R && R < N && N/2 <= C && C < N) {
			N /= 2;
			//목표 행과 열을 N만큼 빼줌
			R -= N;
			C -= N;
			//재귀 재호출, 지나간 행렬의 크기만큼 카운트에 더해줌
			search(count + N * N * 3);
		}
	}

}
