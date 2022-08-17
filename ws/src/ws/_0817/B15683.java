package ws._0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15683 {
	//cctv의 좌표 저장한 리스트
	private static ArrayList<int[]> cctvL = new ArrayList<>();
	//cctv의 종류 저장한 리스트
	private static ArrayList<Integer> cctv = new ArrayList<>();
	//cctv가 볼 수 있는 방향
	private static String[] range = {"left", "right", "up", "down"};
	private static int[][] office;
	private static int N;
	private static int M;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//사무실, cctv의 종류와 위치 저장
		office = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				 int tem = Integer.parseInt(st.nextToken());
				 office[i][j] = tem;
				 
				 if (1 <= tem && tem <= 5) {
					 cctv.add(tem);
					 cctvL.add(new int[] {i, j});
				}
			}
		}
		
		String[][] cctvs = new String[cctv.size()][4];
		//cctv가 볼 수 있는 방향의 경우의 수 구하는 함수 호출
		per(cctvs, 0);
		
		System.out.println(result);
	}

	//재귀를 이용하여 cctv가 볼 수 있는 모든 가능성 탐색
	private static void per(String[][] cctvs, int start) {
		
		//cctv의 종류가 다 만들어졌다면 사무실에서 사각지대 계산하고, 현재 결과값보다 작다면 교체
		if (start == cctv.size()) {
			int[][] n_office = new int[N][];
			for (int i = 0; i < n_office.length; i++) {
				n_office[i] = office[i].clone();
			}
			
			int tem = cal(n_office, cctvs);
			result = tem < result ? tem : result;
			return;
		}
		
		//cctv의 종류에 따른 가능성
		switch (cctv.get(start)) {
		
		//cctv의 종류가 1일 경우
		case 1:
			for (int i = 0; i < 4; i++) {
				cctvs[start][0] = range[i];
				per(cctvs, start+1);
			}
			break;
			
		//cctv의 종류가 2일 경우
		case 2:
			for (int i = 0; i < 4; i+=2) {
				cctvs[start][0] = range[i];
				cctvs[start][1] = range[i+1];
				per(cctvs, start+1);
			}
			break;
			
		//cctv의 종류가 3일 경우
		case 3:
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					cctvs[start][0] = range[i];
					cctvs[start][1] = range[2];
					break;
				case 1:
					cctvs[start][0] = range[i];
					cctvs[start][1] = range[3];
					break;
				case 2:
					cctvs[start][0] = range[i];
					cctvs[start][1] = range[1];
					break;
				case 3:
					cctvs[start][0] = range[i];
					cctvs[start][1] = range[0];
					break;
				}
				per(cctvs, start+1);
			}
			break;
			
		//cctv의 종류가 4일 경우
		case 4:
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					cctvs[start][0] = range[1];
					cctvs[start][1] = range[2];
					cctvs[start][2] = range[3];
					break;
				case 1:
					cctvs[start][0] = range[0];
					cctvs[start][1] = range[2];
					cctvs[start][2] = range[3];
					break;
				case 2:
					cctvs[start][0] = range[0];
					cctvs[start][1] = range[1];
					cctvs[start][2] = range[3];
					break;
				case 3:
					cctvs[start][0] = range[0];
					cctvs[start][1] = range[1];
					cctvs[start][2] = range[2];
					break;
				}
				per(cctvs, start+1);
			}
			break;
		
		//cctv의 종류가 5일 경우
		case 5:
			cctvs[start][0] = range[0];
			cctvs[start][1] = range[1];
			cctvs[start][2] = range[2];
			cctvs[start][3] = range[3];
			per(cctvs, start+1);
			break;
		}
		
	}
	
	//사무실의 사각 지대 구하는 함수
	private static int cal(int[][] office, String[][] cctvs) {
		//모든 cctv들 확인
		for (int i = 0; i < cctvs.length; i++) {
			for (int j = 0; j < cctvs[i].length; j++) {
				
				int[] l = cctvL.get(i).clone();
				String d = cctvs[i][j];
				if (d == null) {
					break;
				}
				
				//cctv가 볼 수 있는 방향이 각각 왼쪽, 오른쪽, 아래, 위일 경우 해당 위치를 다 바꿔줌
				
				if (d.equals("left")) {
					while (office[l[0]][l[1]] != 6) {
						office[l[0]][l[1]] = -1;
						if (l[1] == 0) break;
						l[1] -= 1;
					}
				}
				else if (d.equals("right")) {
					while (office[l[0]][l[1]] != 6) {
						office[l[0]][l[1]] = -1;
						if (l[1] == M-1) break;
						l[1] += 1;
					}
				}
				else if (d.equals("down")) {
					while (office[l[0]][l[1]] != 6) {
						office[l[0]][l[1]] = -1;
						if (l[0] == N-1) break;
						l[0] += 1;
					}
				}
				else if (d.equals("up")) {
					while (office[l[0]][l[1]] != 6) {
						office[l[0]][l[1]] = -1;
						if (l[0] == 0) break;
						l[0] -= 1;
					}
				}
			}
		}
		
		//볼 수 있는 방향 확인 후 남은 사각지대 계산
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (office[i][j] == 0) count++;
			}
		}
		
		return count;
	}
}



