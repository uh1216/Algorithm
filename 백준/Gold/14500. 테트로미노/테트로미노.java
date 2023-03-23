import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static class P {
        int r;
        int c;

        public P(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N;
    static int M;

    static int result = 0;

    static int[][] map;

    static int[] rows = {-1, 0, 1, 0};
    static int[] cols = {0, 1, 0, -1};

    static P[] tn = {new P(0, 1), new P(0, 2), new P(1, 1)};
    static P[] th = {new P(0, 1), new P(0, 2), new P(-1, 1)};
    static P[] tk = {new P(1, 0), new P(2, 0), new P(1, 1)};
    static P[] tj = {new P(1, 0), new P(2, 0), new P(1, -1)};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                cal(new P(i, j), 1, map[i][j], visited);
                visited[i][j] = false;

                calk(new P(i, j));
            }
        }

        System.out.println(result);
    }

    private static void calk(P p) {
        int sum = map[p.r][p.c];
        getSum(p, sum, tn);
        getSum(p, sum, th);
        getSum(p, sum, tj);
        getSum(p, sum, tk);
    }

    private static void getSum(P p, int sum, P[] t) {
        for (P p1 : t) {
            int nr = p.r + p1.r;
            int nc = p.c + p1.c;

            if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                sum += map[nr][nc];
            }
            else break;
        }
        result = Math.max(result, sum);
    }

    private static void cal(P p, int count, int sum, boolean[][] visited) {
        if (count == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = p.r + rows[i];
            int nc = p.c + cols[i];

            if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]) {
                visited[nr][nc] = true;
                cal(new P(nr, nc), count + 1, sum + map[nr][nc], visited);
                visited[nr][nc] = false;
            }
        }
    }
}