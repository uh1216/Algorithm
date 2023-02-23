
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
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
		
		for (int i = towers.length - 1; i >= 0 ; i--) {	
			while (!minTower.isEmpty() && towers[i] >= minTower.peek()[0]) {
				int[] min = minTower.poll();
				result[min[1]] = i + 1;
			}
			int[] temp = {towers[i], i};
			minTower.add(temp);
		}
		for (int i = 0; i < result.length; i++) {
			bw.write(result[i] + " ");
		}
		
		bw.close();
	}

}
