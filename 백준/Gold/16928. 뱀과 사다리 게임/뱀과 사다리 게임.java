import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] ladder = new int[101];
        int[] snake = new int[101];

        for (int i = 1; i <= 100; i++) {
            ladder[i] = i;
            snake[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            ladder[s] = e;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            snake[s] = e;
        }

        bfs(ladder, snake);

        System.out.println(result);
    }

    private static void bfs(int[] ladder, int[] snake) {
        boolean[] visited = new boolean[101];
        visited[1] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (--size >= 0) {
                int now = queue.poll();

                if (now == 100) {
                    return;
                }

                for (int i = 1; i <= 6; i++) {
                    int nn = now + i;

                    if (nn <= 100 && !visited[nn]) {
                        visited[nn] = true;
                        if (ladder[nn] != nn) {
                            nn = ladder[nn];
                        } else if (snake[nn] != nn) {
                            nn = snake[nn];
                        }
                        visited[nn] = true;
                        queue.add(nn);
                    }
                }
            }
            result++;
        }
    }
}