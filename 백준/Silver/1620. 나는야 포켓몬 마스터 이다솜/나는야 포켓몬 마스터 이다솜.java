import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> intKeymap = new HashMap<>();
        HashMap<String, Integer> stKeymap = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String mon = br.readLine();

            intKeymap.put(i, mon);
            stKeymap.put(mon, i);
        }

        for (int i = 0; i < M; i++) {
            String now = br.readLine();
            boolean isDigit = true;
            for (char c : now.toCharArray()) {
                if (!Character.isDigit(c)) {
                    isDigit = false;
                }
            }

            String res = "";

            if (isDigit) {
                res = intKeymap.get(Integer.parseInt(now));
            } else {
                res = stKeymap.get(now) + "";
            }
            bw.write(res + "\n");
        }
        bw.flush();
    }
}