package ws._0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class S6808 {
	private static Integer[] gu;
	private static Integer[] in;
	private static int win;
	private static int lose;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		// 1~18까지의 모든 카드 수를 집합에 넣어줌
		HashSet<Integer> all = new HashSet<>();
		for (int i = 1; i <= 18; i++) {
			all.add(i);
		}
		
		HashSet<Integer> s_in;
		HashSet<Integer> s_gu;
		for (int test_case = 1; test_case <= T; test_case++) {
			win = 0;
			lose = 0;
			
			s_in = new HashSet<>(all);
			s_gu = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			
			//규영이가 낼 카드를 확인해서 집합에 넣어주고
			for (int i = 0; i < 9; i++) {
				s_gu.add(Integer.parseInt(st.nextToken()));
			}
			//모든 카드 집합에서 규영이가 가진 카드를 빼줌
			s_in.removeAll(s_gu);
			
			//순서가 유지되어야 하니 집합에서 배열로 만들어줌
			in = s_in.toArray(new Integer[9]);
			gu = s_gu.toArray(new Integer[9]);
			
			//사용한 카드 불린배열로 저장
			boolean[] use = new boolean[19];
			
			//나올 수 있는 경우의 수들 계산
			for (int i = 0; i < 9; i++) {
				use[in[i]] = true;
				play(1, 0, in[i], 0, 0, use);
				use[in[i]] = false;
			}
			
			sb.append("#").append(test_case).append(" ").append(win).append(" ").append(lose).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	//한 번의 게임 후 다음 게임을 호출하는 함수
	private static void play(int count, int n, int in_p, int gu_score, int in_score, boolean[] use) {
		//누구 카드가 큰지 비교하고 이긴 사람 점수에 더해줌
		if (gu[n] > in_p) {
			gu_score += gu[n] + in_p;
		}
		else if (in_p > gu[n]) {
			in_score += in_p + gu[n];
		}
		
		// 게임 횟수가 9회가 되면 점수를 비교하고, 누가 이겼는지에 따라 변수에 더해주고 함수 종료
		if (count == 9) {
			if (gu_score > in_score) {
				win += 1;
			}
			else if (gu_score < in_score) {
				lose += 1;
			}
			return;
		}
		
		//다음에 낼 카드 뽑기
		for (int i = 0; i < 9; i++) {
			//사용한 카드면 넘기기
			if (use[in[i]]) {
				continue;
			}
			use[in[i]] = true;
			play(count+1, n+1, in[i], gu_score, in_score, use);
			use[in[i]] = false;
		}
	}

}




