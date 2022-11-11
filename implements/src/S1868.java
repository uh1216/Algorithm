import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1868 {

    static int result;
    static int left;
    static int N;

    static int[] rows = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] cols = {0, 1, 1, 1, 0, -1, -1, -1};

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
            left = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            char[][] map = new char[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                map[i] = st.nextToken().toCharArray();
            }

            List<P> zero = new ArrayList<>();

            //0인 곳 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != '*' && search(map, new P(i, j), '*')) {
                        map[i][j] = '0';
                        zero.add(new P(i, j));
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.'){
                        if (search(map, new P(i, j), '0')) {
                            map[i][j] = '1';
                            result++;
                        }
                        else left++;
                    }
                }
            }

            for (int i = 0; i < zero.size(); i++) {
                P z = zero.get(i);

                if (map[z.r][z.c] != '1') {
                    result++;
                    bfs(map, zero.get(i));
                }
            }

            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(char[][] map, P p) {
        Queue<P> queue = new ArrayDeque<>();
        queue.offer(p);

        while (!queue.isEmpty()) {
            P now = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nr = now.r + rows[i];
                int nc = now.c + cols[i];

                if (0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] != '1') {
                    if (map[nr][nc] == '0') {
                        queue.offer(new P(nr, nc));
                    }
                    else {
                        left--;
                    }
                    map[nr][nc] = '1';
                }
            }
        }
    }

    private static boolean search(char[][] map, P p, char c) {
        for (int i = 0; i < 8; i++) {
            int nr = p.r + rows[i];
            int nc = p.c + cols[i];

            if (0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] == c) {
                return false;
            }
        }
        return true;
    }
}
