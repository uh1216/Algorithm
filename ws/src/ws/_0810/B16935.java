package ws._0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B16935 {
	private static ArrayList<ArrayList<Integer>> arr;
	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			arr.add(temp);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int ope = Integer.parseInt(st.nextToken());
			
			switch (ope) {
			case 1:
				one();
				break;
			case 2:
				two();
				break;
			case 3:
				three();
				break;
			case 4:
				four();
				break;
			case 5:
				five();
				break;
			case 6:
				six();
				break;
			}
		}
		
		for (ArrayList<Integer> ar : arr) {
			for (Integer a : ar) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void one() {
		for (int i = 0; i < N / 2; i++) {
			ArrayList<Integer> temp = new ArrayList<>(arr.get(i));
			arr.set(i, arr.get(N-1-i));
			arr.set(N-1-i, temp);
		}
	}
	private static void two() {
		for (int i = 0; i < M / 2; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < N; j++) {
				temp.add(arr.get(j).get(i));
			}
			for (int j = 0; j < N; j++) {
				arr.get(j).set(i, arr.get(j).get(M-1-i));
			}
			for (int j = 0; j < N; j++) {
				arr.get(j).set(M-1-i, temp.get(j));
			}
		}
	}
	private static void three() {
		ArrayList<ArrayList<Integer>> n_arr = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = N-1; j >= 0; j--) {
				temp.add(arr.get(j).get(i));
			}
			n_arr.add(temp);
		}
		arr = n_arr;
		N = arr.size();
		M = arr.get(0).size();
	}
	private static void four() {
		ArrayList<ArrayList<Integer>> n_arr = new ArrayList<>();
		for (int i = M-1; i >= 0; i--) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < N; j++) {
				temp.add(arr.get(j).get(i));
			}
			n_arr.add(temp);
		}
		arr = n_arr;
		N = arr.size();
		M = arr.get(0).size();
	}
	private static void five() {
		ArrayList<ArrayList<Integer>> n_arr = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp1 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp2 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp3 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp4 = new ArrayList<>();
		for (int i = N/2; i < N; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < M/2; j++) {
				temp.add(arr.get(i).get(j));
			}
			temp1.add(temp);
		}
		for (int i = 0; i < N/2; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < M/2; j++) {
				temp.add(arr.get(i).get(j));
			}
			temp2.add(temp);
		}
		for (int i = 0; i < N/2; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = M/2; j < M; j++) {
				temp.add(arr.get(i).get(j));
			}
			temp3.add(temp);
		}
		for (int i = N/2; i < N; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = M/2; j < M; j++) {
				temp.add(arr.get(i).get(j));
			}
			temp4.add(temp);
		}
		
		for (int i = 0; i < N/2; i++) {
			ArrayList<Integer> merge = new ArrayList<>();
			merge.addAll(temp1.get(i));
			merge.addAll(temp2.get(i));
			n_arr.add(merge);
		}
		for (int i = 0; i < N/2; i++) {
			ArrayList<Integer> merge = new ArrayList<>();
			merge.addAll(temp4.get(i));
			merge.addAll(temp3.get(i));
			n_arr.add(merge);
		}
		
		arr = n_arr;
	}
	private static void six() {
		ArrayList<ArrayList<Integer>> n_arr = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp1 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp2 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp3 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp4 = new ArrayList<>();
		for (int i = 0; i < N/2; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = M/2; j < M; j++) {
				temp.add(arr.get(i).get(j));
			}
			temp1.add(temp);
		}
		for (int i = N/2; i < N; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = M/2; j < M; j++) {
				temp.add(arr.get(i).get(j));
			}
			temp2.add(temp);
		}
		for (int i = 0; i < N/2; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < M/2; j++) {
				temp.add(arr.get(i).get(j));
			}
			temp3.add(temp);
		}
		for (int i = N/2; i < N; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < M/2; j++) {
				temp.add(arr.get(i).get(j));
			}
			temp4.add(temp);
		}
		
		for (int i = 0; i < N/2; i++) {
			ArrayList<Integer> merge = new ArrayList<>();
			merge.addAll(temp1.get(i));
			merge.addAll(temp2.get(i));
			n_arr.add(merge);
		}
		for (int i = 0; i < N/2; i++) {
			ArrayList<Integer> merge = new ArrayList<>();
			merge.addAll(temp3.get(i));
			merge.addAll(temp4.get(i));
			n_arr.add(merge);
		}
		
		arr = n_arr;
	}

}
