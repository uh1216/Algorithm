package hw._0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class B2164 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		LinkedList<Integer> card = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			card.add(i);
		}
		
		while (card.size() > 1) {
			card.poll();
			card.add(card.poll());
		}
		
		System.out.println(card.poll());
	}

}
