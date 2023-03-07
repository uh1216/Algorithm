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
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] ground = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = Integer.MAX_VALUE;
        int h = 256;
        for (int i = 256; i >= 0; i--) {
            int temTime = 0;
            int lower = 0;
            int block = B;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (ground[j][k] <= i) {
                        lower += i - ground[j][k];
                        temTime += i - ground[j][k];
                    } else {
                        block += ground[j][k] - i;
                        temTime += (ground[j][k] - i) * 2;
                    }
                }
            }

            if (lower <= block && temTime < time) {
                time = temTime;
                h = i;
            }
        }
        System.out.println(time + " " + h);
    }
}