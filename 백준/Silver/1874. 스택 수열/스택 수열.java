import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(solution());
    }

    private static String solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        int num = 0;
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(br.readLine());

            while (num < now) {
                sb.append("+").append("\n");
                stack.push(++num);
            }

            if (stack.peek() == now) {
                sb.append("-").append("\n");
                stack.pop();
            } else {
                return "NO";
            }
        }
        return sb.toString();
    }
}