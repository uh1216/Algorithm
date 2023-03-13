import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (x == y) {
                bw.write(x + "\n");
                continue;
            }

            if (M > N) {
                int tem = M;
                M = N;
                N = tem;

                tem = x;
                x = y;
                y = tem;
            }

            int ny = x;
            int count = x;

            while (true) {
                int tem = (ny + M) % N;
                ny = tem == 0 ? N : tem;
                count += M;

                if (ny == y) {
                    break;
                }
                if (count > (M * N)) {
                    count = -1;
                    break;
                }
            }

            bw.write(count + "\n");
        }
        bw.flush();
    }
}