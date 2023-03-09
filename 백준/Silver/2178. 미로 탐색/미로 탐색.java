import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

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
    static int[] rows = {-1, 0, 1, 0};
    static int[] cols = {0, 1, 0, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String tem = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tem.charAt(j) - '0';
            }
        }

        sol(new P(0, 0));

        System.out.println(map[N - 1][M - 1]);
    }

    private static void sol(P p) {
        Queue<P> queue = new ArrayDeque<>();
        queue.add(p);

        while (!queue.isEmpty()) {
            P now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now.r + rows[i];
                int nc = now.c + cols[i];

                if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                    if (map[nr][nc] == 1) {
                        map[nr][nc] = map[now.r][now.c] + 1;
                        queue.add(new P(nr, nc));
                    } else if (map[nr][nc] > 1) {
                        if (map[nr][nc] > map[now.r][now.c] + 1) {
                            map[nr][nc] = map[now.r][now.c] + 1;
                            queue.add(new P(nr, nc));
                        }
                    }
                }
            }
        }
    }
}