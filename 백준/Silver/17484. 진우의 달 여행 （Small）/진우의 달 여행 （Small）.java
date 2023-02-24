import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int result = Integer.MAX_VALUE;
    static int[] cols = {0, -1, 0, 1};
    static int[][] fuel;
    static int R;
    static int C;

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
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        fuel = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                fuel[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < C; i++) {
            dfs(0, fuel[0][i], new P(0, i));
        }

        System.out.println(result);
    }

    private static void dfs(int beforeDir, int fuelSum, P p) {
        if (fuelSum > result) {
            return;
        }

        if (p.r == R - 1) {
            result = Math.min(result, fuelSum);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (beforeDir == i) {
                continue;
            }

            int nr = p.r + 1;
            int nc = p.c + cols[i];

            if (0 <= nc && nc < C) {
                dfs(i, fuelSum + fuel[nr][nc], new P(nr, nc));
            }
        }
    }
}