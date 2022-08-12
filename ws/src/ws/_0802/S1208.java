package ws._0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S1208 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int result = 0;
		
		// max, min 힙 선언
		PriorityQueue<Integer> min;
		PriorityQueue<Integer> max;
		
		for (int i = 0; i < 10; i++) {
			int N = Integer.parseInt(br.readLine());
			
			/**
			 * // 매 테케마다 힙 초기화 
			 */
			min = new PriorityQueue<>(); 
			max = new PriorityQueue<>(Collections.reverseOrder()); 
			
			/**
			 * max, min 힙에 값 넣기
			 */
			st = new StringTokenizer(br.readLine(), " "); 
			for (int j = 0; j < 100; j++) {
				int box = Integer.parseInt(st.nextToken());
				
				max.add(box);	
				min.add(box);
			}
			
			/**
			 * 횟수동안 힙에서 최대값 최소값 뽑아서 차이 계산 후 다시 집어넣기
			 * 차이가 1보다 작으면 반복문 탈출
			 */
			int count = 0;
			while (count != N) {
				max.add(max.poll() - 1);
				min.add(min.poll() + 1);
				
				result = max.peek() - min.peek();
				if (result <= 1) {
					break;
				}
				count++;
			}
			
			System.out.println(result);
		}
	}

}
