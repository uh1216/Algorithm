package ws._0803;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sbp {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			ArrayList<StringBuilder> map = new ArrayList<>();
			int[] tank = {0, 0}; //tank의 위치
			
			/**
			 * map을 리스트에 넣고, 현재 tank의 위치를 찾음
			 */
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				map.add(new StringBuilder().append(st.nextToken()));

				for (int j = 0; j < map.get(i).length(); j++) {
					if (map.get(i).charAt(j) == '^' || map.get(i).charAt(j) == '<' || map.get(i).charAt(j) == '>' || map.get(i).charAt(j) == 'v') {
						tank[0] = i;
						tank[1] = j;
					}
				}
			}
			
			// 현재 탱크가 보고 있는 방향
			char direction = map.get(tank[0]).charAt(tank[1]);
			// 이동 할 예정이므로, 현재 탱크가 있는 땅은 빈땅으로
			map.get(tank[0]).replace(tank[1], tank[1]+1, ".");
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			String[] actions = st.nextToken().split("");
			
			// 각 액션마다 수행
			for (String action : actions) {
				if (action.equals("S")) {
					int shoot;
					if (direction == '^') {
						shoot = tank[0]-1;
						while (shoot >= 0) {
							if (map.get(shoot).charAt(tank[1]) == '#') {
								break;
							}
							if (map.get(shoot).charAt(tank[1]) == '*') {
								map.get(shoot).replace(tank[1], tank[1]+1, ".");
								break;
							}
							shoot--;
						}
					}
					else if (direction == '<') {
						shoot = tank[1]-1;
						while (shoot >= 0) {
							if (map.get(tank[0]).charAt(shoot) == '#') {
								break;
							}
							if (map.get(tank[0]).charAt(shoot) == '*') {
								map.get(tank[0]).replace(shoot, shoot+1, ".");
								break;
							}
							shoot--;
						}
					}
					else if (direction == '>') {
						shoot = tank[1]+1;
						while (shoot < W) {
							if (map.get(tank[0]).charAt(shoot) == '#') {
								break;
							}
							if (map.get(tank[0]).charAt(shoot) == '*') {
								map.get(tank[0]).replace(shoot, shoot+1, ".");
								break;
							}
							shoot++;
						}
					}
					else if (direction == 'v'){
						shoot = tank[0]+1;
						while (shoot < H) {
							if (map.get(shoot).charAt(tank[1]) == '#') {
								break;
							}
							if (map.get(shoot).charAt(tank[1]) == '*') {
								map.get(shoot).replace(tank[1], tank[1]+1, ".");
								break;
							}
							shoot++;
						}
					}
				}
				else if (action.equals("U")) {
					if (tank[0] > 0 && map.get(tank[0]-1).charAt(tank[1]) == '.') {
						tank[0]--;
					}
					direction = '^';
				}
				else if (action.equals("D")) {
					if (tank[0] < H - 1 && map.get(tank[0]+1).charAt(tank[1]) == '.') {
						tank[0]++;
					}
					direction = 'v';
				}
				else if (action.equals("L")) {
					if (tank[1] > 0 && map.get(tank[0]).charAt(tank[1]-1) == '.') {
						tank[1]--;
					}
					direction = '<';
				}
				else {
					if (tank[1] < W - 1 && map.get(tank[0]).charAt(tank[1]+1) == '.') {
						tank[1]++;
					}
					direction = '>';
				}
			}
			// 마지막 탱크의 좌표, 보고있는 방향으로 맵에 넣음
			map.get(tank[0]).replace(tank[1], tank[1]+1, direction + "");
			
			bw.write("#" + test_case + " ");
			for (StringBuilder s : map) {
				bw.write(s.toString() + "\n");
			}
		}
		bw.close();
	}

}
