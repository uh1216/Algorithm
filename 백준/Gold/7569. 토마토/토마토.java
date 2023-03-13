import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class P {

        int r;
        int c;
        int h;

        public P(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }

    static int[] rows = {-1, 0, 1, 0};
    static int[] cols = {0, 1, 0, -1};
    static int[] height = {1, -1};

    static int R;
    static int C;
    static int H;

    static int rTomato;
    static int nrTomato;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        Queue<P> queue = new ArrayDeque<>();

        int[][][] tomatoes = new int[H][R][C];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < R; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < C; k++) {
                    tomatoes[i][j][k] = Integer.parseInt(st.nextToken());

                    if (tomatoes[i][j][k] == 1) {
                        rTomato++;
                        queue.add(new P(j, k, i));
                    } else if (tomatoes[i][j][k] == 0) {
                        nrTomato++;
                    }
                }
            }
        }

        int result = 0;
        while (nrTomato != 0 && !queue.isEmpty()) {
            int size = queue.size();

            while (--size >= 0) {
                P now = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = now.r + rows[i];
                    int nc = now.c + cols[i];

                    if (0 <= nr && nr < R && 0 <= nc && nc < C && tomatoes[now.h][nr][nc] == 0) {
                        tomatoes[now.h][nr][nc] = 1;
                        nrTomato--;
                        queue.add(new P(nr, nc, now.h));
                    }
                }

                for (int i = 0; i < 2; i++) {
                    int nh = now.h + height[i];

                    if (0 <= nh && nh < H && tomatoes[nh][now.r][now.c] == 0) {
                        tomatoes[nh][now.r][now.c] = 1;
                        nrTomato--;
                        queue.add(new P(now.r, now.c, nh));
                    }
                }
            }

            result++;
        }

        System.out.println(nrTomato == 0 ? result : -1);
    }
}