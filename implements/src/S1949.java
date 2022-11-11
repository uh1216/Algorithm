import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1949 {

    static int result;

    static int N;
    static int K;
    static int max;

    static int[] rows = {-1, 1, 0, 0};
    static int[] cols = {0, 0, 1, -1};

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

        for (int testCase = 1; testCase <= T; testCase++) {
            result = 0;
            max = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    max = Math.max(max, map[i][j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) {
                        boolean[][] visited = new boolean[N][N];
                        visited[i][j] = true;
                        dfs(map, visited, new P(i, j), 1, 1);
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int[][] map, boolean[][] visited, P p, int count, int left) {
        result = Math.max(result, count);

        int now = map[p.r][p.c];

        for (int i = 0; i < 4; i++) {
            int nr = p.r + rows[i];
            int nc = p.c + cols[i];

            if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
                int next = map[nr][nc];

                if (now > next) {
                    visited[nr][nc] = true;
                    dfs(map, visited, new P(nr, nc), count + 1, left);
                    visited[nr][nc] = false;
                }
                else if (left != 0) {
                    if (next - (now-1) <= K) next = now-1;

                    if (now > next) {
                        int temp = map[nr][nc];

                        map[nr][nc] = next;
                        visited[nr][nc] = true;
                        dfs(map, visited, new P(nr, nc), count + 1, left-1);
                        visited[nr][nc] = false;
                        map[nr][nc] = temp;
                    }
                }
            }
        }
    }
}
