import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> time = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            time.add(Integer.parseInt(st.nextToken()));
        }

        int sum = 0;
        int sum2 = 0;
        for (int i = 0; i < N; i++) {
            sum2 += time.poll();
            sum += sum2;
        }

        System.out.println(sum);
    }
}