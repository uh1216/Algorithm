import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] count = new int[10001];

        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(br.readLine());
            count[now]++;
        }

        for (int i = 1; i <= 10000; i++) {
            for (int j = 0; j < count[i]; j++) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }
}