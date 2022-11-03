import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S5658 {

    static Set<Integer> num;
    static StringBuilder n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            num = new HashSet<>();

            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            char[] nums = st.nextToken().toCharArray();

            //4개씩 나누고 10진수로 숫자 구한 후 숫자 이동
            for (int i = 0; i < N-1; i++) {
                makenum(nums, N / 4);
                rotate(nums);
                System.out.println(Arrays.toString(nums));
            }

            //K번째로 큰 수 구하기

            List<Integer> result = new ArrayList<>(num);
            result.sort(Collections.reverseOrder());

            sb.append("#").append(testCase).append(" ").append(result.get(K - 1)).append("\n");
        }
        System.out.println(sb);
    }

    private static void makenum(char[] nums, int c) {
        for (int i = 0; i < nums.length; i+=c) {
            n = new StringBuilder();
            for (int j = 0; j < c; j++) {
                n.append(nums[i + j]);
            }
            num.add(Integer.parseInt(n.toString(), 16));
        }
    }

    private static void rotate(char[] nums) {
        for (int i = 1; i < nums.length; i++) {
            char tem = nums[i];
            nums[i] = nums[0];
            nums[0] = tem;
        }
    }
}
