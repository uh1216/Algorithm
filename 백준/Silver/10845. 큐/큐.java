import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String op = st.nextToken();
            int num = 0;

            if (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            }

            if (op.equals("push")) {
                operation(queue, op, num);
            } else {
                sb.append(operation(queue, op, num)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int operation(LinkedList<Integer> queue, String op, int num) {
        switch (op) {
            case "push":
                queue.add(num);
                return 0;
            case "front":
                return (!queue.isEmpty()) ? queue.peek() : -1;
            case "back":
                return (!queue.isEmpty()) ? queue.peekLast() : -1;
            case "size":
                return queue.size();
            case "empty":
                return (!queue.isEmpty()) ? 0 : 1;
            default:
                return (!queue.isEmpty()) ? queue.poll() : -1;
        }
    }
}