package ws._0805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B2493 {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//힙 선언하고, 정렬 기준 오버라이드
		PriorityQueue<int[]> minTower = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] < o2[0] ? -1 : 1;
			}
		});
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] towers = new int[N];
		int[] result = new int[N];
		for (int i = 0; i < towers.length; i++) {
			towers[i] = Integer.parseInt(st.nextToken());
		}
		
		//타워 맨 뒤에서부터 반복
		for (int i = towers.length - 1; i >= 0 ; i--) {	
			// 만약 힙이 비어있지 않고, 현재 인덱스의 타워가 전의 타워들 보다 크다면, 힙을 한개씩 빼면서 힙의 가장 작은 타워가 현재 타워보다 클 때까지 반복
			while (!minTower.isEmpty() && towers[i] >= minTower.peek()[0]) {
				int[] min = minTower.poll();
				result[min[1]] = i + 1; // 결과 배열에 힙에 들어있던 타워의 인덱스에 현재 타워의 인덱스를 넣어줌 
			}
			int[] temp = {towers[i], i}; // 현재 타워의 탑 높이와 인덱스를 힙에 넣어줌
			minTower.add(temp);
		}
		for (int i = 0; i < result.length; i++) {
			bw.write(result[i] + " ");
		}
		
		bw.close();
	}

}
