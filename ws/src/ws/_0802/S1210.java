package ws._0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1210 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			String[][] ladder = new String[100][100];
			
			for (int i = 0; i < ladder.length; i++) {
				ladder[i] = br.readLine().split(" ");
			}
			
			/**
			 * 도착 지점 찾기 (시작 지점으로 사용)
			 */
			int[] start = {99, 0}; 
			for (int i = 0; i < ladder.length; i++) {
				if (ladder[99][i].equals("2")) {
					start[1] = i;
				}
			}
			
			/**
			 * 시작점으로 올라가는 과정 
			 */
			while (start[0] != 0) {
				// 이어지는 왼쪽 사다리로 이동
				if (ladder[start[0]][start[1] - 1].equals("1")) {
					while (start[1]-1 >= 0 && ladder[start[0]][start[1]-1].equals("1")) {
						start[1]--;
					}
					
				}
				// 이어지는 오른쪽  사다리로 이동
				else if (ladder[start[0]][start[1] + 1].equals("1")) {
					while (start[1]+1 < 100 && ladder[start[0]][start[1]+1].equals("1")) {
						start[1]++;
					}
				}
				start[0]--;
			}
			
			System.out.println(start[1]);
		}
	}
}


