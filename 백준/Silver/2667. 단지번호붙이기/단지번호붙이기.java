import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    static class P {

        int r;
        int c;

        public P(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] rows = {-1, 0, 1, 0};
    static int[] cols = {0, 1, 0, -1};
    static int N;
    static int[][] map;
    static List<Integer> house = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String tem = br.readLine();
            for (int j = 0; j < tem.length(); j++) {
                map[i][j] = tem.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    bfs(new P(i, j));
                }
            }
        }

        Collections.sort(house);

        bw.write(house.size() + "\n");

        for (Integer n : house) {
            bw.write(n + "\n");
        }
        bw.flush();
    }

    private static void bfs(P p) {
        int count = 1;

        Queue<P> queue = new ArrayDeque<>();
        queue.add(p);

        map[p.r][p.c] = 2;

        while (!queue.isEmpty()) {
            P now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = now.r + rows[i];
                int nc = now.c + cols[i];

                if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                    if (map[nr][nc] == 1) {
                        count++;
                        map[nr][nc] = 2;
                        queue.add(new P(nr, nc));
                    }
                }
            }
        }
        house.add(count);
    }
}