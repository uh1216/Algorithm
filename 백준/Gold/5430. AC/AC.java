import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            String op = br.readLine();

            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");

            if (N == 0 && op.contains("D")) {
                bw.write("error" + "\n");
                continue;
            }

            LinkedList<String> list = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                list.add(st.nextToken());
            }

            bw.write(exec(op, list) + "\n");
        }
        bw.flush();
    }

    private static String exec(String op, LinkedList<String> list) {
        boolean isReverse = false;
        for (int i = 0; i < op.length(); i++) {
            char operation = op.charAt(i);
            if (operation == 'R') {
                isReverse = !isReverse;
            } else {
                if (list.isEmpty()) {
                    return "error";
                }
                if (isReverse) {
                    list.removeLast();
                } else {
                    list.remove();
                }
            }
        }
        StringBuilder sb = new StringBuilder("[");

        if (isReverse) {
            while (!list.isEmpty()) {
                sb.append(list.removeLast()).append(",");
            }
        } else {
            while (!list.isEmpty()) {
                sb.append(list.removeFirst()).append(",");
            }
        }
        if (sb.length() > 1) {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append("]");
        }

        return sb.toString();
    }
}