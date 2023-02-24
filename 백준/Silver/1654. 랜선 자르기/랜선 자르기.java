import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long start = 1;
        long end = 0;

        long[] lan = new long[K];
        for (int i = 0; i < K; i++) {
            lan[i] = Long.parseLong(br.readLine());
            end = Math.max(end, lan[i]);
        }

        while (start <= end) {
            long mid = (start + end) / 2;

            if (canMake(lan, N, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(end);
    }

    private static boolean canMake(long[] lan, int n, long mid) {
        int count = 0;
        for (long l : lan) {
            count += l / mid;
        }
        return count >= n;
    }
}