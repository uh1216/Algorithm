import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] block = new int[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        int l, r, now;

        for (int i = 1; i < C; i++) {
            l = r = 0;
            for (int j = 0; j < i; j++) {
                if (block[j] > l) l = block[j];
            }

            for (int j = i+1; j < C; j++) {
                if (block[j] > r) r = block[j];
            }

            now = Math.min(l, r);

            if (now > block[i]) result += now - block[i];
        }

        System.out.println(result);
    }
}
