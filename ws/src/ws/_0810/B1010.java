package ws._0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 
 * 처음에는 재귀를 이용하여 풀었으나 시간초과가 났고, 조합의 계산식을 이용하는 방식으로 문제를 풀었습니다.
 *
 */
public class B1010 {
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 0; test_case < T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			//조합 계산을 위한 bigInteger선언
			BigInteger cal = new BigInteger("1");
			
			//bigInteger이용하여 조합 계산
			for (int i = M - N + 1; i <= M; i++) {
				BigInteger num = new BigInteger(i + "");
				cal = cal.multiply(num);
			}
			for (int i = 2; i <= N; i++) {
				BigInteger num = new BigInteger(i + "");
				cal = cal.divide(num);
			}
			
			sb.append(cal).append("\n");
		}
		System.out.println(sb.toString());
	}
}
