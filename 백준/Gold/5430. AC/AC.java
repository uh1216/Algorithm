import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            String op = br.readLine();

            int N = Integer.parseInt(br.readLine());
            String arr = br.readLine();

            if (N == 0 && op.contains("D")) {
                bw.write("error" + "\n");
                continue;
            }

            String[] split = arr.substring(1, arr.length() - 1).split(",");
            LinkedList<String> list = new LinkedList<>(Arrays.asList(split));

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
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if (isReverse) {
            for (int i = list.size() - 1; i >= 0; i--) {
                sb.append(list.get(i)).append(",");
            }
        } else {
            for (String s : list) {
                sb.append(s).append(",");
            }
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("]");

        return sb.toString();
    }
}