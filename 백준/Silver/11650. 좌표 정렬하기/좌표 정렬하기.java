import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static class P implements Comparable<P> {
        int x;
        int y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            return (this.x - o.x > 0) ? 1 : (this.x - o.x == 0) ? (this.y - o.y) : -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        P[] p = new P[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = new P(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(p);

        for (P p1 : p) {
            bw.write(p1.x + " " + p1.y + "\n");
        }
        bw.flush();
    }
}