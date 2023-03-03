import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> stack = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String op = st.nextToken();
            int num = 0;
            if (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            }

            if (op.equals("push")) {
                operation(stack, op, num);
            } else sb.append(operation(stack, op, num)).append("\n");
        }

        System.out.println(sb);
    }

    private static int operation(ArrayList<Integer> stack, String op, int num) {
        if (op.equals("push")) {
            stack.add(num);
            return 0;
        } else if (op.equals("top")) {
            return (stack.size() != 0) ? stack.get(stack.size() - 1) : -1;
        } else if (op.equals("size")) {
            return stack.size();
        } else if (op.equals("empty")) {
            return (stack.size() == 0) ? 1 : 0;
        } else {
            return (stack.size() != 0) ? stack.remove(stack.size() - 1) : -1;
        }
    }
}