package ws._0804;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class S1225 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		LinkedList<Integer> queue; 
		
		for (int test_case = 0; test_case < 10; test_case++) {
			int T = Integer.parseInt(br.readLine());
			
			queue = new LinkedList<>(); //큐 선언
			
			//숫자 받아서 큐에 삽입
			String[] nums = br.readLine().split(" ");
			for (String num : nums) {
				queue.add(Integer.parseInt(num));
			}
			
			//사이클 진행
			int i = 1;
			while (true) {
				int n = queue.poll() - i;
				
				if (n <= 0) { //맨 앞 숫자가 사이클 진행 중 0보다 작아지면 반복문 탈출
					queue.add(0);
					break;
				}
				
				queue.add(n);
				
				i = (i % 5) + 1;
			}
			bw.write("#" + T + " ");
			for (int q : queue) {
				bw.write(q + " ");
			}
			bw.write("\n");
		}
		bw.close();
	}

}
