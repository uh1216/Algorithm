import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5648 {

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

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            Atomic[] atomics = new Atomic[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atomics[i] = new Atomic(r, c, d, k);
            }

        }

    }

}
