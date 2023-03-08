import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int[][] paper;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        map.put(-1, 0);
        map.put(0, 0);
        map.put(1, 0);

        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dac(0, N, 0, N, N);

        bw.write(map.get(-1) + "\n");
        bw.write(map.get(0) + "\n");
        bw.write(map.get(1) + "");

        bw.flush();
    }

    private static void dac(int rStart, int rEnd, int cStart, int cEnd, int N) {
        //종이 확인
        int p = find(rStart, rEnd, cStart, cEnd);

        if (p != -2) {
            map.put(p, map.get(p) + 1);
        } else {
            N /= 3;
            for (int i = rStart; i < rEnd; i += N) {
                for (int j = cStart; j < cEnd; j += N) {
                    dac(i, i + N, j, j + N, N);
                }
            }
        }

        //통일되지 않으면 분배 후 재호출
    }

    private static int find(int rStart, int rEnd, int cStart, int cEnd) {
        int p = paper[rStart][cStart];
        for (int i = rStart; i < rEnd; i++) {
            for (int j = cStart; j < cEnd; j++) {
                if (p != paper[i][j]) {
                    return -2;
                }
            }
        }
        return p;
    }
}