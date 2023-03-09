import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int result = 0;
    static int N;
    static Set<Integer> contain = new HashSet<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                contain.add(Integer.parseInt(st.nextToken()));
            }
        }

        sol(0, new HashSet<>(contain));

        System.out.println(result);
    }

    private static void sol(int count, HashSet<Integer> left) {
        if (count == N) {
            if (left.isEmpty()) {
                result++;
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (left.remove(i)) {
                sol(count + 1, left);
                left.add(i);
            } else {
                sol(count + 1, left);
            }
        }
    }
}