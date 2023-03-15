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
        int K = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];

        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int result = 0;

        int l = N - 1;
        while (K != 0) {
            while (true) {
                if (K / coin[l] != 0) {
                    result += K / coin[l];
                    K %= coin[l];
                    break;
                }
                l--;
            }
        }
        System.out.println(result);
    }
}