import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());

            TreeSet<Integer> ts = new TreeSet<>();
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());

                if (op == 'I') {
                    ts.add(n);

                    if (map.containsKey(n)) {
                        map.put(n, map.get(n) + 1);
                    } else {
                        map.put(n, 1);
                    }
                } else {
                    if (ts.isEmpty()) {
                        continue;
                    }
                    int out;
                    if (n == 1) {
                        out = ts.pollLast();
                    } else {
                        out = ts.pollFirst();
                    }
                    map.put(out, map.get(out) - 1);

                    if (map.get(out) != 0) {
                        ts.add(out);
                    }
                }
            }
            if (ts.isEmpty()) {
                bw.write("EMPTY" + "\n");
            } else {
                bw.write(ts.last() + " " + ts.first() + "\n");
            }
        }
        bw.flush();
    }
}