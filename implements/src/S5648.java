import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S5648 {

    static int result;

    static int[] rows = {1, -1, 0, 0};
    static int[] cols = {0, 0, -1, 1};

    static Map<String, int[]> visited;

    static class Atomic {
        int r;
        int c;
        int d;
        int k;

        public Atomic(int r, int c, int d, int k) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.k = k;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Queue<Atomic> queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            result = 0;

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken()) * 2;
                int r = Integer.parseInt(st.nextToken()) * 2;
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                queue.add(new Atomic(r, c, d, k));
            }

            for (int i = 0; i < 2000; i++) {
                visited = new HashMap<>();
                int size = queue.size();

                while (--size >= 0) {
                    Atomic now = queue.poll();

                    int nr = now.r + rows[now.d];
                    int nc = now.c + cols[now.d];

                    String key = nr + "" + nc;
                    System.out.println(key);

                    if (!visited.containsKey(key)) {
                        visited.put(key, new int[]{1, now.k});
                    }
                    else {
                        visited.put(key,
                                new int[] {
                                        visited.get(key)[0] + 1,
                                        visited.get(key)[1] + now.k
                                });
                    }

                    queue.offer(new Atomic(nr, nc, now.d, now.k));
                }

                size = queue.size();

                while (--size >= 0) {
                    Atomic now = queue.poll();
                    int[] nowV = visited.get(now.r + "" + now.c);
                    if (nowV[0] > 1) {
                        result += nowV[1];
                        visited.put(now.r + "" + now.c, new int[] {2, 0});
                    } else queue.offer(now);

                }
            }

            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

}
