import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        int[] tree = new int[N];
        int end = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, tree[i]);
        }

        int start = 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (canGo(tree, mid, M)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(end);
    }

    private static boolean canGo(int[] tree, int mid, long M) {
        long sum = 0;
        for (int i : tree) {
            sum += Math.max(0, i - mid);
        }
        if (M <= sum) {
            return true;
        } else {
            return false;
        }
    }
}