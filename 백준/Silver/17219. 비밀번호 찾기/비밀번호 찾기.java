import java.io.*;
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

        HashMap<String, String> map = new HashMap<>();

        while (--N >= 0) {
            st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            String password = st.nextToken();

            map.put(key, password);
        }

        while (--M >= 0) {
            String key = br.readLine();
            bw.write(map.get(key) + "\n");
        }

        bw.flush();
    }
}