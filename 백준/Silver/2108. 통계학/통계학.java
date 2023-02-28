import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        double sum = 0; //산술평균
        int[] nums = new int[N]; //중앙값
        //최빈값
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = -4000; i <= 4000; i++) {
            map.put(i, 0);
        }
        //범위
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(br.readLine());

            sum += now;
            nums[i] = now;
            map.put(now, map.get(now) + 1);
            max = Math.max(max, now);
            min = Math.min(min, now);
        }

        //산술 평균
        sb.append(Math.round(sum / N)).append("\n");
        //중앙값
        Arrays.sort(nums);
        sb.append(nums[N / 2]).append("\n");
        //최빈값
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        list.sort((Integer i1, Integer i2) -> map.get(i2) - map.get(i1));
        ArrayList<Integer> bean = new ArrayList<>();
        int big = list.get(0);
        bean.add(big);
        for (int i = 1; i < list.size(); i++) {
            int n = list.get(i);
            if (map.get(n) == map.get(big)) {
                bean.add(n);
            } else {
                break;
            }
        }
        Collections.sort(bean);
        if (bean.size() > 1) {
            sb.append(bean.get(1)).append("\n");
        } else {
            sb.append(bean.get(0)).append("\n");
        }
        //범위
        sb.append(max - min).append("\n");

        System.out.println(sb);
    }
}
