import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line = "";
        while (!(line = br.readLine()).equals(".")) {
            sb.append(isBalance(line)).append("\n");
        }

        System.out.println(sb);
    }

    private static String isBalance(String line) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (!(c == '[' || c == ']' || c == '(' || c == ')')) {
                continue;
            }

            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }

            Character peek = stack.peek();

            if (peek == ')' || peek == ']') {
                return "no";
            }

            if (peek == '(') {
                if (c == ')') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                if (c == ']') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        if (stack.isEmpty()) {
            return "yes";
        } else {
            return "no";
        }
    }
}