import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int[][] way;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        way = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                bw.write(way[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
    }

    private static void bfs(int i) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int j = 1; j <= N; j++) {
                if (arr[now][j] == 1) {
                    if (way[i][j] == 0) {
                        way[i][j] = 1;
                        queue.add(j);
                    }
                }
            }
        }
    }
}