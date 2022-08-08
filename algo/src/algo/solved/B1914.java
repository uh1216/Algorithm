package algo.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class B1914 {
	private static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		if (N <= 20) {
			hanoi(1, 2, 3, N);
		}
		BigInteger num = new BigInteger("2");
		System.out.println(num.pow(N).subtract(new BigInteger("1")));
		System.out.println(sb.toString());
	}
	
	private static void hanoi(int start, int temp, int end, int N) {
		if (N == 1) {
			sb.append(start).append(" ").append(end).append("\n");
			return;
		}
		hanoi(start, end, temp, N-1);
		sb.append(start).append(" ").append(end).append("\n");
		hanoi(temp, start, end, N-1);
	}
}
