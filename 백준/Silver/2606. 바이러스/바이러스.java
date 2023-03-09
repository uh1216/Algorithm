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
    static HashMap<Integer, ArrayList<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            map.get(c1).add(c2);
            map.get(c2).add(c1);
        }

        System.out.println(sol() - 1);
    }

    private static int sol() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Integer computer : map.get(now)) {
                if (!visited[computer]) {
                    visited[computer] = true;
                    queue.add(computer);
                }
            }
        }

        int res = 0;
        for (boolean b : visited) {
            if (b) {
                res++;
            }
        }
        return res;
    }
}