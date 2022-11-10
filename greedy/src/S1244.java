import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1244 {

    static StringBuilder t;
    static HashSet<Integer> visited;
    static int result;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());

            char[] tem = st.nextToken().toCharArray();
            K = Integer.parseInt(st.nextToken());

            int[] nums = new int[tem.length];
            for (int i = 0; i < tem.length; i++) {
                nums[i] = tem[i] - '0';
            }

            visited = new HashSet<>();
            result = 0;

            dfs(nums,0);

            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int[] nums, int count) {
        int r = intArrtoInt(nums);

        if (count == K) {
            result = Math.max(result, r);
            return;
        }

        if (visited.contains(r)) return;

        visited.add(r);

        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int[] nNum = swap(nums.clone(), i, j);
                dfs(nNum, count+1);
            }
        }
    }

    private static int intArrtoInt(int[] nums) {
        t = new StringBuilder();
        for (int num : nums) {
            t.append(num);
        }

        return Integer.parseInt(t.toString());
    }

    private static int[] swap(int[] nNum, int i, int j) {
        int tem = nNum[i];
        nNum[i] = nNum[j];
        nNum[j] = tem;

        return nNum;
    }
}
