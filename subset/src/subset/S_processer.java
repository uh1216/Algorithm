package subset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class S_processer {
	static char[] directions = {'u', 'r', 'd', 'l'};
	static int N;
	static boolean[] isSelected;
	static List<Core> cores;
	static int[][] pro;
	
	static class Core {
		int r;
		int c;
		
		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			pro = new int[N][N];
			cores = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pro[i][j] = Integer.parseInt(st.nextToken());
					if (i != 0 && j != 0 && pro[i][j] == 1) cores.add(new Core(i, j));
				}
			}
			isSelected = new boolean[cores.size()]; 
			subset(0);
		}
	}

	private static void subset(int count) {
		if (count == isSelected.length) {
			int[][] nPro = new int[N][N];
			for (int i = 0; i < pro.length; i++) nPro[i] = pro[i].clone(); 
			dfs(isSelected, 0, 0, nPro);
			System.out.println(Arrays.toString(isSelected));
			return;
		}
		
		isSelected[count] = true;
		subset(count+1);
		isSelected[count] = false;
		subset(count+1);
	}

	private static void dfs(boolean[] isSelected, int index, int conn, int[][] pro) {
		if (index == isSelected.length) {
			return;
		}
		
		if (!isSelected[index]) dfs(isSelected, index+1, conn, pro);
		
		Core core = cores.get(index);
		for (int i = 0; i < 4; i++) {
			int t;
			boolean canConn;
			switch (directions[i]) {
			case 'u':
				t = core.r;
				canConn = true;
				while (t >= 0) {
					if (pro[t][core.c] != 0) {
						canConn = false;
						break;
					}
					t--;
				}
				if (canConn) dfs(isSelected, index+1, conn+1, pro);
				break;

			case 'r':
				t = core.c;
				canConn = true;
				while (t < N) {
					if (pro[core.r][t] != 0) {
						canConn = false;
						break;
					}
					t++;
				}
				if (canConn) dfs(isSelected, index+1, conn+1, pro);
				break;
				
			case 'd':
				t = core.r;
				canConn = true;
				while (t >= 0) {
					if (pro[t][core.c] != 0) {
						canConn = false;
						break;
					}
					t--;
				}
				if (canConn) dfs(isSelected, index+1, conn+1, pro);
				break;
				
			case 'l':
				t = core.c;
				canConn = true;
				while (t >= 0) {
					if (pro[t][core.c] != 0) {
						canConn = false;
						break;
					}
					t--;
				}
				if (canConn) dfs(isSelected, index+1, conn+1, pro);
				break;
			}
		}
	}
}





