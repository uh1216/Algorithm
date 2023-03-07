import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] connect;
    static HashMap<Integer, ArrayList<Integer>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        connect = new int[N + 1][N + 1];

        map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            map.get(p1).add(p2);
            map.get(p2).add(p1);
        }

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        int result = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int s : connect[i]) {
                sum += s;
            }
            if (sum < min) {
                min = sum;
                result = i;
            }
        }
        System.out.println(result);
    }

    private static void bfs(int i) {
        boolean[] visited = new boolean[N + 1];
        visited[i] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);

        int stage = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (--size >= 0) {
                int now = queue.poll();

                for (Integer conn : map.get(now)) {
                    if (!visited[conn]) {
                        visited[conn] = true;
                        connect[i][conn] = stage;
                        queue.add(conn);
                    }
                }
            }
            stage++;
        }

    }
}