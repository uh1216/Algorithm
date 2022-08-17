package ws._0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S5644 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int[] A = {1, 1};
			int[] B = {10, 10};
			
			//총 충전량
			int charge = 0;
			
			st = new StringTokenizer(br.readLine());
			int move = Integer.parseInt(st.nextToken());
			int Bc = Integer.parseInt(st.nextToken());
			
			//A사용자의 이동 저장
			int[] moveA = new int[move];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < move; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			
			//B사용자의 이동 저장
			int[] moveB = new int[move];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < move; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			//배터리 충전기 위치 / 거리 / 충전량 저장
			int[][] Bcs = new int[Bc][4];
			for (int i = 0; i < Bc; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					Bcs[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//위치에 따른 충전량 더하는 함수 호출
			charge += getMaxCharge(Bcs, A, B);
			
			//움직인 위치에 따라 A,B 사용자의 위치 갱신해주고, 충전량 더하는 함수 호출
			for (int i = 0; i < move; i++) {
				switch (moveA[i]) {
				case 1: A[0]--; break;
				case 2: A[1]++; break;
				case 3: A[0]++; break;
				case 4: A[1]--; break;
				}
				
				switch (moveB[i]) {
				case 1: B[0]--; break;
				case 2: B[1]++; break;
				case 3: B[0]++; break;
				case 4: B[1]--; break;
				}
				
				charge += getMaxCharge(Bcs, A, B);
			}
			
			sb.append("#").append(test_case).append(" ").append(charge).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	//충전량 더하는 함수
	private static int getMaxCharge(int[][] bcs, int[] A, int[] B) {
		ArrayList<int[]> aIn = new ArrayList<>();
		ArrayList<int[]> bIn = new ArrayList<>();
		
		//각 배터리 충전기의 좌표, 거리, 충전량을 변수에 할당
		for (int i = 0; i < bcs.length; i++) {
			int X = bcs[i][1];
			int Y = bcs[i][0];
			int C = bcs[i][2];
			int M = bcs[i][3];
			
			//A사용자의 현재 위치에서 포함될 수 있는 충전기 더해줌
			if (C >= Math.abs(X - A[0]) + Math.abs(Y - A[1])) {
				aIn.add(new int [] {M, i});
			}
			//B사용자의 현재 위치에서 포함될 수 있는 충전기 더해줌
			if (C >= Math.abs(X - B[0]) + Math.abs(Y - B[1])) {
				bIn.add(new int [] {M, i});
			}
		}
		
		//A사용자가 최대 / 두번째로 충전할 수 있는 양, B사용자가 최대 / 두번째로 충전할 수 있는 양을 구하고, 합했을 때 가장 높은 충전량을 리턴  
		int maxI = 0;
		
		int[] aMax = {0, 0};
		int[] aTwo = {0, 0};
		
		if (!aIn.isEmpty()) {
			for (int i = 0; i < aIn.size(); i++) {
				if (aMax[0] < aIn.get(i)[0]) {
					aMax[0] = aIn.get(i)[0];
					aMax[1] = aIn.get(i)[1];
					maxI = i;
				}
			}
			aIn.remove(maxI);
			
			for (int i = 0; i < aIn.size(); i++) {
				if (aTwo[0] < aIn.get(i)[0]) {
					aTwo[0] = aIn.get(i)[0];
					aTwo[1] = aIn.get(i)[1];
				}
			}
		}
		
		int[] bMax = {0, 0};
		int[] bTwo = {0, 0};
		
		if (!bIn.isEmpty()) {
			for (int i = 0; i < bIn.size(); i++) {
				if (bMax[0] < bIn.get(i)[0]) {
					bMax[0] = bIn.get(i)[0];
					bMax[1] = bIn.get(i)[1];
					maxI = i;
				}
			}
			bIn.remove(maxI);
			
			
			for (int i = 0; i < bIn.size(); i++) {
				if (bTwo[0] < bIn.get(i)[0]) {
					bTwo[0] = bIn.get(i)[0];
					bTwo[1] = bIn.get(i)[1];
				}
			}
		}
		
		int result = 0;
		if (aMax[1] == bMax[1]) {
			result = aMax[0] + bTwo[0] > aTwo[0] + bMax[0] ? aMax[0] + bTwo[0] : aTwo[0] + bMax[0];
			result = result > (aMax[0] / 2) + (bMax[0] / 2) ? result : (aMax[0] / 2) + (bMax[0] / 2);
		}
		else result = aMax[0] + bMax[0];
		
		return result;
		
	}
}




