import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B17143 {
	
	static class Shark {
		int r;
		int c;
		int speed;
		int dir;
		int size;
		
		public Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		//상어 리스트
		List<Shark> sharks = new ArrayList<>();
		
		//상어 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sharks.add(new Shark(Integer.parseInt(st.nextToken())-1,
						Integer.parseInt(st.nextToken())-1,
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())));
		}
		
		//잡은 상어 크기의 합을 구한 결과값
		int result = 0;
		
		//낚시왕 초기 위치
		int fishKing = -1;
		
		//초기 상어 배열
		Shark[][] map = new Shark[R][C];
		for (Shark shark : sharks) map[shark.r][shark.c] = shark;
		
		//이동한 낚시왕이 C와 같거나 더 커질 때까지 반복
		while (++fishKing < C) {
			//상어 잡기
			for (int i = 0; i < R; i++) {
				if (map[i][fishKing] != null) {
					result += map[i][fishKing].size;
					//잡은 상어 지우기
					sharks.remove(map[i][fishKing]);
					break;
				}
			}
			
			//상어 이동
			for (Shark shark : sharks) {
				/**
				 * 남은 칸 수 빼고
				 * C-1로 나누고
				 * 나눈 수 만큼 이동하면 어느 방향인지 확인
				 * 남은 칸 만큼 반대 방향으로 이동
				 */
				int speed = shark.speed;
				int left = 0;
				
				switch (shark.dir) {
				case 1:
					if (shark.r - speed >= 0) {
						shark.r -= speed;
						break;
					}
					
					speed -= shark.r;
					left = speed % (R-1);
					speed /= (R-1);
					//나눈게 홀수일때
					if (speed % 2 == 1) {
						//방향 바꾸기
						if (left == 0) {
							shark.dir = 2;
							shark.r = R-1;
						}
						else shark.r = R-1 - left;
						
					}
					//나눈게 짝수일때
					else if (speed % 2 == 0) {
						//방향 바꾸기
						if (left == 0) shark.r = 0;
						else {
							shark.dir = 2;
							shark.r = left;
						}
					}
					
					break;
				case 2:
					if (speed + shark.r <= R-1) {
						shark.r += speed;
						break;
					}
					
					speed -= R-1 - shark.r;
					left = speed % (R-1);
					speed /= (R-1);
					//나눈게 홀수일때
					if (speed % 2 == 1) {
						//방향 바꾸기
						if (left == 0) {
							shark.dir = 1;
							shark.r = 0;
						}
						else shark.r = left;
						
					}
					//나눈게 짝수일때
					else if (speed % 2 == 0) {
						//방향 바꾸기
						if (left == 0) shark.r = R-1;
						else {
							shark.dir = 1;
							shark.r = R-1 - left;
						}
					}
					break;
					
				case 3:
					if (speed + shark.c <= C-1) {
						shark.c += speed;
						break;
					}
					
					speed -= C-1 - shark.c;
					left = speed % (C-1);
					speed /= (C-1);
					//나눈게 홀수일때
					if (speed % 2 == 1) {
						//방향 바꾸기
						if (left == 0) {
							shark.dir = 4;
							shark.c = 0;
						}
						else shark.c = left;
						
					}
					//나눈게 짝수일때
					else if (speed % 2 == 0) {
						//방향 바꾸기
						if (left == 0) shark.c = C-1;
						else {
							shark.dir = 4;
							shark.c = C-1 - left;
						}
					}
					break;
				case 4:
					if (shark.c - speed >= 0) {
						shark.c -= speed;
						break;
					}
					
					speed -= shark.c;
					left = speed % (C-1);
					speed /= (C-1);
					//나눈게 홀수일때
					if (speed % 2 == 1) {
						//방향 바꾸기
						if (left == 0) {
							shark.dir = 3;
							shark.c = C-1;
						}
						else shark.c = C-1 - left;
						
					}
					//나눈게 짝수일때
					else if (speed % 2 == 0) {
						//방향 바꾸기
						if (left == 0) shark.c = 0;
						else {
							shark.dir = 3;
							shark.c = left;
						}
					}
					break;
				}
			}
			
			List<Shark> del = new ArrayList<>();
			
			//새로운 상어 배열
			map = new Shark[R][C];
			for (int i = 0; i < sharks.size(); i++) {
				Shark shark = sharks.get(i);
				if (map[shark.r][shark.c] != null) {
					if (map[shark.r][shark.c].size < shark.size) {
						del.add(map[shark.r][shark.c]);
						map[shark.r][shark.c] = shark;
					}
					else del.add(shark);
				}
				else map[shark.r][shark.c] = shark;
			}
			
			for (int i = 0; i < del.size(); i++) {
				sharks.remove(del.get(i));
			}
		}
		
		System.out.println(result);
	}

}
