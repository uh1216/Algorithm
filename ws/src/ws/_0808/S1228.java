package ws._0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class S1228 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			//원본 암호문 입력받고 링크드 리스트에 넣음
			LinkedList<String> code = new LinkedList<>();
			String[] tem = br.readLine().split(" ");
			for (String s : tem) {
				code.add(s);
			}
			
			int O = Integer.parseInt(br.readLine());
			
			//한 줄로 되어있는 명령어를 이중 배열로 만들어 사용하게 편리하게 만듦
			String[] op_tem = br.readLine().split("I");
			String[][] operations = new String[op_tem.length][];
			for (int i = 0; i < op_tem.length; i++) {
				operations[i] = op_tem[i].split(" ");
			}
			
			// 각 명령어를 실행
			for (int i = 1; i <= O; i++) {
				int index = Integer.parseInt(operations[i][1]);
				int nums = Integer.parseInt(operations[i][2]);
				
				for (int j = 0; j < nums; j++) {
					code.add(index + j, operations[i][j + 3]);
				}
			}
			
			sb.append("#").append(test_case).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(code.get(i)).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
