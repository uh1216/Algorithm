import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int N;

    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            N = Integer.parseInt(br.readLine());
            count = 0;

            dfs(0);

            bw.write(count + "\n");
        }
        bw.flush();
    }

    private static void dfs(int sum) {
        if (sum > N) {
            return;
        } else if (sum == N) {
            count++;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            dfs(sum + i);
        }
    }
}