import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int result = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int n = (int) Math.sqrt(N);
        for (int i = n; i >= n / 2 - 1; i--) {
            sol(N, i, 1);
        }

        System.out.println(result);
    }

    private static void sol(int N, int n, int count) {
        int np = (int) Math.pow(n, 2);

        if (count >= result) {
            return;
        }
        if (N - np == 0) {
            result = count;
            return;
        }

        n = (int) Math.sqrt(N - np);

        for (int i = n; i >= n / 2 - 1; i--) {
            sol(N - np, i, count + 1);
        }
    }
}