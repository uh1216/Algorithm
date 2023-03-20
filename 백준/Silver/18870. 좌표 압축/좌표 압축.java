import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] original = new int[N];
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            original[i] = nums[i];
        }

        Arrays.sort(nums);

        HashMap<Integer, Integer> numIdx = new HashMap<>();
        numIdx.put(nums[0], 0);

        int idx = 0;
        for (int i = 1; i < N; i++) {
            if (nums[i] != nums[i - 1]) {
                idx++;
            }
            numIdx.put(nums[i], idx);
        }

        for (int i : original) {
            bw.write(numIdx.get(i) + " ");
        }

        bw.flush();
    }
}