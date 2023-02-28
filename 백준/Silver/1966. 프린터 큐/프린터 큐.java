import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 1; i <= 10; i++) {
                map.put(i, 0);
            }
            Queue<Integer> queue = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                queue.add(n);
                map.put(n, map.get(n) + 1);
            }

            int count = 0;
            while (!queue.isEmpty()) {
                Integer now = queue.poll();
                if (!canOut(now, map)) {
                    queue.add(now);
                    if (M == 0) {
                        M = queue.size() - 1;
                    } else {
                        M--;
                    }
                } else {
                    map.put(now, map.get(now) - 1);
                    count++;

                    if (M == 0) {
                        break;
                    } else {
                        M--;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean canOut(Integer now, HashMap<Integer, Integer> map) {
        for (int i = now + 1; i < 10; i++) {
            if (map.get(i) != 0) {
                return false;
            }
        }
        return true;
    }
}