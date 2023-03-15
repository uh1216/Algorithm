import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        HashSet<Integer> set = new HashSet<>();

        while (--N >= 0) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            int n = 0;

            if (st.hasMoreTokens()) {
                n = Integer.parseInt(st.nextToken());
            }

            switch (op) {
                case "add":
                    set.add(n);
                    break;
                case "check":
                    if (set.contains(n)) {
                        bw.write(1 + "\n");
                    } else {
                        bw.write(0 + "\n");
                    }
                    break;
                case "remove":
                    set.remove(n);
                    break;
                case "toggle":
                    if (set.contains(n)) {
                        set.remove(n);
                    } else {
                        set.add(n);
                    }
                    break;
                case "all":
                    set.clear();
                    for (int i = 1; i <= 20; i++) {
                        set.add(i);
                    }
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }
        bw.flush();
    }
}