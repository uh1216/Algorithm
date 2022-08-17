package study._220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B17281 {
	private static int scores[][];
	private static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		scores = new int[N][9];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] visited = new int[10];
		per(visited, 2);
		System.out.println(result);
	}
	
	private static void per(int[] visited, int index) {
		if (index == 10) {
			LinkedList<Integer> player = new LinkedList<>();
			for (int i = 2; i < visited.length; i++) {
				player.add(visited[i]);
			}
			player.add(3, 1);
			play(player);
			return;
		}
		
		for (int i = 2; i <= 9; i++) {
			if (visited[i] == 0) {
				visited[i] = index;
				per(visited, index+1);
				visited[i] = 0;
			}
		}
	}
	
	private static void play(LinkedList<Integer> player) {
		int p = 0;
		int score = 0;
		for (int i = 0; i < scores.length; i++) {
			int[] in = {0, 0, 0};
			int out = 0;
			
			while (out != 3) {
				int ru = scores[i][player.get(p)-1];
				int c;
				
				switch (ru) {
				case 0: out++; break;
				case 1:
					if (in[2] == 1) score += 1;
					int temp_b = in[0];
					int temp_a = 0;
					for (int j = 1; j < in.length; j++) {
						temp_a = in[j];
						in[j] = temp_b;
						temp_b = temp_a;
					}
					in[0] = 1;
					break;
				case 2:
					c = 0;
					for (int j = 1; j < in.length; j++) {
						if (in[j] == 1) c++;
					}
					score += c;
					if (in[0] == 1) in = new int[] {0, 1, 1};
					else in = new int[] {0, 1, 0};
					break;
				case 3:
					c = 0;
					for (int j = 0; j < in.length; j++) {
						if (in[j] == 1) c++;
					}
					score += c;
					in = new int[] {0, 0, 1};
					break;
				case 4:
					c = 1;
					for (int j = 0; j < in.length; j++) {
						if (in[j] == 1) c++;
					}
					score += c;
					in = new int[] {0, 0, 0};
					break;
				}
				
				p = (p + 1) % 9;
			}
		}
		
		result = result > score ? result : score;
		return;
	}
}



