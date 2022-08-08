package ws._0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B12891 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		/**
		 * 입력
		 */
		int result = 0;
		st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		String dna = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		/**
		 * Map에 A, C, G, T를 집어 넣음
		 */
		HashMap<Character, Integer> p_count = new HashMap<>();
		p_count.put('A', 0);
		p_count.put('C', 0);
		p_count.put('G', 0);
		p_count.put('T', 0);
		
		/**
		 * 투 포인터 사용
		 */
		int l = 0;
		int r = 0;
		p_count.put(dna.charAt(0), 1);
		while (l <= r) {
			//포인터가 가르키는 영역의 문자의 길이가 입력받은 부분암호 길이와 같고, 필요한 문자 수를 충족하면 결과 값 ++
			if (r - l + 1 == P && A <= p_count.get('A') && C <= p_count.get('C') && G <= p_count.get('G') && T <= p_count.get('T')) {
				result++;
			}
			//포인터가 가르키는 영역이 P보다 작거나 같으면 오른쪽 포인터 이동
			if (r < dna.length()-1 && r - l + 1 <= P) {
				r++;
				p_count.put(dna.charAt(r), p_count.get(dna.charAt(r))+1); //오른쪽 포인터 이동하면서 추가되는 문자 Map에 더해줌
			}
			//포인터가 가르키는 영역이 P보다 크거나 오른쪽 포인터가 맨 마지막 인덱스이면 왼쪽 포인터 이동
			else if (r == dna.length() -1 || r - l + 1 > P) { 
				l++;
				p_count.put(dna.charAt(l-1), p_count.get(dna.charAt(l-1))-1); //왼쪽 포인터 이동하면서 추가되는 문자 Map에 더해줌
			}

		}
		System.out.println(result);
	}
}
