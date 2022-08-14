package hw._0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B3040 {
	private static int[] num = new int[9];
	private static boolean[] result = new boolean[100];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 9; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		boolean[] visited = new boolean[100];
		sum(0, 0, visited);

		for (int i = 0; i < 100; i++) {
			if (result[i]) {
				sb.append(i).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	private static void sum(int count, int sum, boolean[] visited) {
		if (count == 7) {
			if (sum == 100) {
				result = Arrays.copyOf(visited, 100);
			}
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (!visited[num[i]]) {
				visited[num[i]] = true;
				sum(count+1, sum+num[i], visited);
				visited[num[i]] = false;
			}
		}
	}

}
