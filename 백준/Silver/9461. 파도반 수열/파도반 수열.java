import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] P = new long[101];
        P[1] = 1;
        P[2] = 1;
        P[3] = 1;
        P[4] = 2;
        P[5] = 2;

        for (int i = 6; i <= 100; i++) {
            P[i] = P[i - 1] + P[i - 5];
        }

        int T = Integer.parseInt(br.readLine());

        while (--T >= 0) {
            int N = Integer.parseInt(br.readLine());

            bw.write(P[N] + "\n");
        }

        bw.flush();
    }
}