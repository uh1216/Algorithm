package ws._0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class B11286 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		//배열을 힙에서 사용하기 위해 정렬 기준 재정의한 힙 선언
		PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? o1[1] < o2[1] ? -1 : 1 : 1; 
			}
		}); 
		
		//힙에 배열을 저장하고 0일 경우 최소값 빼고 아닐 경우 값 추가하면서 하나씩 빼면서 계산
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n != 0) heap.add(new int[] {Math.abs(n), n});
			else {
				if (heap.isEmpty()) sb.append(0).append("\n");
				else sb.append(heap.poll()[1]).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
