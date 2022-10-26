import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16234 {
    static int N;
    static int L;
    static int R;

    static int[] rows = {0, 1, 0, -1};
    static int[] cols = {1, 0, -1, 0};

    static boolean[][] visited;
    static boolean isOpen = true;

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
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while (true) {
            isOpen = false;

            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) bfs(map, new P(i, j));
                }
            }

            if (!isOpen) break;

            result++;
        }

        System.out.println(result);
    }

    private static void bfs(int[][] map, P p) {
        Queue<P> queue = new ArrayDeque<>();
        queue.offer(p);

        boolean[][] bvisited = new boolean[N][N];
        bvisited[p.r][p.c] = true;

        int sum = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            P now = queue.poll();

            int n = map[now.r][now.c];
            sum += n;
            count++;

            for (int i = 0; i < 4; i++) {
                int nr = now.r + rows[i];
                int nc = now.c + cols[i];

                if (0 <= nr && nr < N && 0 <= nc && nc < N && !bvisited[nr][nc] && !visited[nr][nc]) {
                    int d = Math.abs(n - map[nr][nc]);

                    if (L <= d && d <= R) {
                        bvisited[nr][nc] = true;
                        queue.offer(new P(nr, nc));
                    }
                }
            }
        }

        sum /= count;

        if (count > 1) {
            isOpen = true;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (bvisited[i][j]) {
                        map[i][j] = sum;
                        visited[i][j] = true;
                    }
                }
            }
        }
    }
}
