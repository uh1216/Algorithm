import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5650 {

    static int result;
    static int N;

    static int[] wall1 = {1, 3, 0, 2};
    static int[] wall2 = {3, 0, 1, 2};
    static int[] wall3 = {2, 0, 3, 1};
    static int[] wall4 = {1, 2, 3, 0};
    static int[] wall5 = {1, 0, 3, 2};

    static int[] rows = {-1, 1, 0, 0};
    static int[] cols = {0, 0, -1, 1};

    static P[][] wormHole;

    static int[][] map;

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
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            result = 0;

            wormHole = new P[5][2];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    wormHole[i][j] = new P(-1, -1);
                }
            }

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (map[i][j] >= 6) {
                        int t = map[i][j] - 6;
                        if (wormHole[t][0].r != -1) {
                            wormHole[t][1].r = i;
                            wormHole[t][1].c = j;
                        }
                        else {
                            wormHole[t][0].r = i;
                            wormHole[t][0].c = j;
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        moveBall(new P(i, j));
                    }
                }
            }

            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static void moveBall(P p) {
        P first = new P(p.r, p.c);

        for (int d = 0; d < 4; d++) {
            int score = 0;
            int dir = d;

            p = new P(first.r, first.c);

            while (true) {
                int nr = p.r + rows[dir];
                int nc = p.c + cols[dir];

                if (nr < 0 || nr >= N || 0 > nc || nc >= N) {
                    score++;
                    dir = wall5[dir];
                }
                else {
                    int now = map[nr][nc];

                    //웜홀
                    if (now >= 6) {
                        now -= 6;

                        P[] wormhole = wormHole[now];

                        if (wormhole[0].r == nr && wormhole[0].c == nc) {
                            p = new P(wormhole[1].r, wormhole[1].c);
                        } else {
                            p = new P(wormhole[0].r, wormhole[0].c);
                        }
                        continue;
                    }
                    //벽
                    else if (now >= 1) {
                        if (now == 1) dir = wall1[dir];
                        else if (now == 2) dir = wall2[dir];
                        else if (now == 3) dir = wall3[dir];
                        else if (now == 4) dir = wall4[dir];
                        else dir = wall5[dir];
                        score++;
                    }
                    //종료 조건
                    if (map[nr][nc] == -1 || (first.r == nr && first.c == nc)) break;
                }

                p.r = nr;
                p.c = nc;
            }

            result = Math.max(result, score);
        }
    }

}
