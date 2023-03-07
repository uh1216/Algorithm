import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int result;

    static int N;

    static int[] buttons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        result = Math.abs(N - 100);
        int broken = Integer.parseInt(br.readLine());

        buttons = new int[10 - broken];

        HashSet<Integer> brokenButtons = new HashSet<>();

        if (broken != 0) {
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < broken; i++) {
                brokenButtons.add(Integer.parseInt(st.nextToken()));
            }
        }

        int l = 0;
        for (int i = 0; i < 10; i++) {
            if (brokenButtons.contains(i)) {
                continue;
            }
            buttons[l] = i;
            l++;
        }

        combinations(0, "");

        System.out.println(result);
    }

    private static void combinations(int count, String num) {
        if (count >= 1) {
            result = Math.min(result, Math.abs(N - Integer.parseInt(num)) + count);
        }

        if (count == (N + "").length() + 1) {
            return;
        }

        for (int i = 0; i < buttons.length; i++) {
            combinations(count + 1, num + buttons[i]);
        }
    }
}