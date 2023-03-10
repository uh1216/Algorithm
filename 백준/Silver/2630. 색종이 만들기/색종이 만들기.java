import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int white;
    static int blue;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sol(0, N - 1, 0, N - 1, N);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void sol(int rs, int re, int cs, int ce, int N) {
        int color = isUnity(rs, re, cs, ce);
        if (color == 0) {
            white++;
        } else if (color == 1) {
            blue++;
        } else {
            N /= 2;
            for (int i = rs; i < re; i += N) {
                for (int j = cs; j < ce; j += N) {
                    sol(i, i + N, j, j + N, N);
                }
            }
        }
    }

    private static int isUnity(int rs, int re, int cs, int ce) {
        int p = paper[rs][cs];
        for (int i = rs; i < re; i++) {
            for (int j = cs; j < ce; j++) {
                if (p != paper[i][j]) {
                    return -2;
                }
            }
        }
        return p;
    }
}