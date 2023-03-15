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

        int[] people = new int[N];
        PriorityQueue<Integer> time = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            time.add(Integer.parseInt(st.nextToken()));
        }

        people[0] = time.poll();
        for (int i = 1; i < N; i++) {
            people[i] = time.poll() + people[i - 1];
        }

        System.out.println(Arrays.stream(people).sum());
    }
}