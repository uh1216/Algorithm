import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean[] people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        people = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());

        if (p != 0) {
            for (int i = 0; i < p; i++) {
                people[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int[][] party = new int[M][];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            party[i] = new int[n];

            for (int j = 0; j < n; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean pivot;
        do {
            pivot = false;
            for (int i = 0; i < M; i++) {
                if (find(party[i])) {
                    for (int j = 0; j < party[i].length; j++) {
                        if (!people[party[i][j]]) {
                            people[party[i][j]] = true;
                            pivot = true;
                        }
                    }
                }
            }
        } while (pivot);

        int result = 0;

        for (int[] par : party) {
            if (!find(par)) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static boolean find(int[] party) {
        for (int i : party) {
            if (people[i]) {
                return true;
            }
        }
        return false;
    }
}