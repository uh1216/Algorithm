import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S_processer {
	static int N;
	static int coreCount;
	static int result;
	static boolean[] isSelected;
	static List<P> cores;
	static int[][] pro;
	
	static int[] rows = {1, -1, 0, 0};
	static int[] cols = {0, 0, -1, 1};
	
	static class P {
		int r;
		int c;
		
		public P(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = Integer.MAX_VALUE;
			coreCount = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			pro = new int[N][N];
			cores = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pro[i][j] = Integer.parseInt(st.nextToken());
					if (pro[i][j] == 1 && i != 0 && j != 0 && i != N-1 && j != N-1) cores.add(new P(i, j));
				}
			}
			isSelected = new boolean[cores.size()];
			
			subset(0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void subset(int count) {
		if (count == cores.size()) {
			int[][] npro = new int[N][N];
			for (int i = 0; i < N; i++) npro[i] = pro[i].clone();
			
			int cc = 0;
			for (int i = 0; i < isSelected.length; i++) if (isSelected[i]) cc++;
			
			if (cc >= coreCount) conn(npro, 0);
			return;
		}
		
		isSelected[count] = true;
		subset(count+1);
		isSelected[count] = false;
		subset(count+1);
	}

	private static void conn(int[][] pro, int index) {
		//기저조건
		if (index == cores.size()) {
			int cc = 0;
			for (int i = 0; i < isSelected.length; i++) if(isSelected[i]) cc++;
			
			coreCount = cc;
			int r = countLine(pro);
			result = r < result ? r : result;
			return;
		}
		
		//선택하지 않은 코어면 다음 dfs로 넘어감
		if (!isSelected[index]) conn(pro, index+1);
		
		//현재 코어
		P p = cores.get(index);
		
		//4방향 탐색하면서 dfs돌기
		boolean canConn;
		for (int i = 0; i < 4; i++) {
			canConn = true;
			
			//새로운 pro 배열 만들기
			int[][] npro = new int[N][N];
			for (int j = 0; j < N; j++) npro[j] = pro[j].clone(); 
			
			int nr = p.r + rows[i];
			int nc = p.c + cols[i];
			while (0 <= nr && nr < N && 0 <= nc && nc < N) {
				if (npro[nr][nc] >= 1) {
					canConn = false;
					break;
				}
				
				npro[nr][nc] = 2;
				
				nr += rows[i];
				nc += cols[i];
			}
			if (canConn) conn(npro, index+1);
		}
	}

	private static int countLine(int[][] pro) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pro[i][j] == 2) count++;
			}
		}
		return count;
	}

}
