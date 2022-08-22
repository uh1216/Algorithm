package ws._0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class S1244 {
	private static boolean isDu;
	private static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			String num = st.nextToken();
			int[] nums = new int[num.length()];
			int count = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < num.length(); i++) {
				nums[i] = num.charAt(i) - '0';
			}
			
			HashSet<Integer> du = new HashSet<>();
			for (int i = 0; i < nums.length; i++) {
				du.add(nums[i]);
			}
			
			isDu = !(nums.length == du.size());
			
			change(count, nums, 0);
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void change(int count, int[] nums, int start) {
		if (start == nums.length-1) {
			count %= 2;
			if (isDu && count == 1) count--;
			else if (!isDu && count == 1) {
				int temp = nums[nums.length-2];
				nums[nums.length-2] = nums[nums.length-1];
				nums[nums.length-1] = temp;
				count--;
			}
		}

		if (count == 0) {
			StringBuilder tem = new StringBuilder();
			for (int i : nums) {
				tem.append(i);
			}
			int n = Integer.parseInt(tem.toString());
			result = result < n ? n : result;
			return;
		}
		
		int max = 0;
		for (int i = start+1; i < nums.length; i++) {
			max = max < nums[i] ? nums[i] : max;
		}
		
		for (int i = start+1; i < nums.length; i++) {
			if (nums[i] == max && max > nums[start]) {
				int[] n_nums = nums.clone();
				int temp = n_nums[start];
				n_nums[start] = n_nums[i];
				n_nums[i] = temp;
				change(count-1, n_nums, start+1);
			}
		}
		change(count, nums, start+1);
	}

}
