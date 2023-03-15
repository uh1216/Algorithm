import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bfs(A, B);
        }
        bw.flush();
    }

    private static void bfs(int A, int B) throws IOException {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(A);

        int[] parent = new int[10000];
        Arrays.fill(parent, -1);
        char[] oper = new char[10000];

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == B) {
                StringBuilder sb = new StringBuilder();
                while (now != A) {
                    sb.append(oper[now]);
                    now = parent[now];
                }
                bw.write(sb.reverse() + "\n");
            }

            int D = (now * 2) % 10000;

            if (parent[D] == -1) {
                parent[D] = now;
                oper[D] = 'D';
                queue.add(D);
            }

            int S = now == 0 ? 9999 : (now - 1);

            if (parent[S] == -1) {
                parent[S] = now;
                oper[S] = 'S';
                queue.add(S);
            }

            int L = (now * 10) % 10000 + (now / 1000);

            if (parent[L] == -1) {
                parent[L] = now;
                oper[L] = 'L';
                queue.add(L);
            }

            int R = (now / 10) + ((now % 10) * 1000);

            if (parent[R] == -1) {
                parent[R] = now;
                oper[R] = 'R';
                queue.add(R);
            }
        }
    }
}