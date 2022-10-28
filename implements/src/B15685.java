import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B15685 {

    static int[][] dragon;

    static boolean[][] visited = new boolean[101][101];

    static int[] rows = {0, -1, 0, 1};
    static int[] cols = {1, 0, -1, 0};

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
        int N = Integer.parseInt(st.nextToken());

        dragon = new int[11][];
        dragon[0] = new int[1];
        dragon[0][0] = 0;
        makeDragon(dragon);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragonCurve(new P(y, x), d, g);
        }

        int result = 0;
        //visited에서 사각형 4개가 true일 경우 ++
        for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
                if (visited[j][k] && visited[j+1][k] && visited[j][k+1] && visited[j+1][k+1]) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static void makeDragon(int[][] dragon) {
        for (int i = 1; i < 11; i++) {
            int[] tem = new int[dragon[i-1].length * 2];
            int[] tem1 = dragon[i-1].clone();
            int[] tem2 = dragon[i-1].clone();

            for (int j = 0; j < tem2.length; j++) tem2[j] = (tem2[j] + 1) % 4;

            for (int j = 0; j < tem2.length / 2; j++) {
                int temp = tem2[j];
                tem2[j] = tem2[tem2.length- j - 1];
                tem2[tem2.length- j - 1] = temp;
            }

            for (int j = 0; j < tem1.length; j++) tem[j] = tem1[j];
            for (int j = 0; j < tem2.length; j++) tem[j + tem2.length] = tem2[j];

            dragon[i] = tem;
        }
    }

    private static void dragonCurve(P p, int d, int g) {
        int[] path = dragon[g];

        visited[p.r][p.c] = true;

        for (int i = 0; i < path.length; i++) {
            path[i] = (path[i] + d) % 4;

            int nr = p.r + rows[path[i]];
            int nc = p.c + cols[path[i]];

            visited[nr][nc] = true;

            p = new P(nr, nc);
        }
    }
}
