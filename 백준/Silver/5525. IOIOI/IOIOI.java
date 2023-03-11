import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        StringBuilder sb = new StringBuilder("I");
        for (int i = 0; i < N; i++) {
            sb.append("OI");
        }

        String P = sb.toString();
        int end = P.length() - 1;

        int result = 0;

        StringBuilder tem = new StringBuilder(S.substring(0, end + 1));
        while (true) {
            if (tem.toString().equals(P)) {
                result++;
            }

            if (++end >= S.length()) {
                break;
            }

            tem.deleteCharAt(0);
            tem.append(S.charAt(end));
        }

        System.out.println(result);
    }
}