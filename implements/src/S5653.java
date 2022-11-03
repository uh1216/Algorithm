import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S5653 {

    static int N;
    static int M;
    static int K;

    static int[] rows = {1, -1, 0, 0};
    static int[] cols = {0, 0, -1, 1};

    static boolean[][] visited;

    static class Cell {
        int r;
        int c;
        int life;
        int now;

        public Cell(int r, int c, int life, int now) {
            this.r = r;
            this.c = c;
            this.life = life;
            this.now = now;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "r=" + r +
                    ", c=" + c +
                    ", life=" + life +
                    ", now=" + now +
                    '}';
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            visited = new boolean[N + K + 50][M + K + 50];

            List<Cell> list = new ArrayList<>();
            Queue<Cell> queue = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int l = Integer.parseInt(st.nextToken());
                    if (l != 0) {
                        visited[i + (N + K) / 2][j + (M + K) / 2] = true;
                        list.add(new Cell(i + (N + K) / 2, j + (M + K) / 2, l, l));
                    }
                }
            }

            list.sort(new Comparator<Cell>() {
                @Override
                public int compare(Cell o1, Cell o2) {
                    return o2.life - o1.life;
                }
            });

            for (int i = 0; i < list.size(); i++) {
                queue.offer(list.get(i));
            }

            int r = bfs(queue);

            sb.append("#").append(testCase).append(" ").append(r).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs(Queue<Cell> queue) {
        int count = 1;
        while (count++ <= K) {
            int size = queue.size();

            while (--size >= 0) {
                Cell now = queue.poll();

                if (now.now > 0) {
                    queue.offer(new Cell(now.r, now.c, now.life, now.now - 1));
                    continue;
                }
                else if (now.now == 0) {
                    for (int i = 0; i < 4; i++) {
                        int nr = now.r + rows[i];
                        int nc = now.c + cols[i];

                        if (!visited[nr][nc]) {
                            visited[nr][nc] = true;
                            queue.offer(new Cell(nr, nc, now.life, now.life));
                        }
                    }
                }

                if (-now.now + 1 < now.life) {
                    queue.offer(new Cell(now.r, now.c, now.life, now.now - 1));
                }
            }
        }

        return queue.size();
    }
}
