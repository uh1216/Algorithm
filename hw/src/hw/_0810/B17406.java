package hw._0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B17406 {
	private static int[][] arr;
	private static int[][] ro;
	private static int K;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ro = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				ro[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] visited = new boolean[K];
		comb(0, visited, new ArrayList<>());
		System.out.println(result);
	}
	
	private static void comb(int count, boolean[] visited, ArrayList<int[]> order) {
		if (count == K) {
			int[][] n_arr = new int[arr.length][arr[0].length];
			for (int i = 0; i < arr.length; i++) {
				n_arr[i] = arr[i].clone();
			}
			
			for (int i = 0; i < order.size(); i++) {
				int[] o = order.get(i);
				rotate(n_arr, o[0], o[1], o[2]);
			}
			
			int temp = cal(n_arr);
			result = temp > result ? result : temp;
			
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order.add(ro[i]);
				comb(count+1, visited, order);
				visited[i] = false;
				order.remove(order.size()-1);
			}
		}
	}
	
	private static void rotate(int[][] arr, int r, int c, int s) {
		int s_row = r-s-1;
		int s_col = c-s-1;
		
		int l = (r+s-1) - s_row + 1;
		
		int temp_b = 0;
		int temp_a = 0;
		for (int i = 0; i < l / 2; i++) {
			int[] start = {s_row+i, s_col+i};
			temp_b = arr[start[0]][start[1]];
			
			for (int j = 1; j < l - (i*2); j++) {
				start[1] += 1;
				temp_a = arr[start[0]][start[1]];
				arr[start[0]][start[1]] = temp_b;
				temp_b = temp_a;
			}
			
			for (int j = 1; j < l - (i*2); j++) {
				start[0] += 1;
				temp_a = arr[start[0]][start[1]];
				arr[start[0]][start[1]] = temp_b;
				temp_b = temp_a;
			}
			
			for (int j = 1; j < l - (i*2); j++) {
				start[1] -= 1;
				temp_a = arr[start[0]][start[1]];
				arr[start[0]][start[1]] = temp_b;
				temp_b = temp_a;
			}
			
			for (int j = 1; j < l - (i*2); j++) {
				start[0] -= 1;
				temp_a = arr[start[0]][start[1]];
				arr[start[0]][start[1]] = temp_b;
				temp_b = temp_a;
			}
		}
		
	}
	
	private static int cal(int[][] arr) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr[0].length; j++) {
				sum += arr[i][j];
			}
			min = sum > min ? min : sum;
		}
		return min;
	}
}





