
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int X;
	static int result;
	
	static class City implements Comparable<City>{
		int city;
		int time;
		
		public City(int city, int time) {
			this.city = city;
			this.time = time;
		}

		@Override
		public int compareTo(City o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<City>> graph = new ArrayList<>();
		ArrayList<ArrayList<City>> graphR = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			graphR.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new City(to, time));
			graphR.get(to).add(new City(from, time));
		}
		
		int[] XtoN = new int[N+1];
		dijkstra(graph, XtoN);
		
		int[] NtoX = new int[N+1];
		dijkstra(graphR, NtoX);
		
		for (int i = 1; i <= N; i++) {
			int student = XtoN[i] + NtoX[i];
			result = result < student ? student : result;
		}
		
		System.out.println(result);
	}

	private static void dijkstra(ArrayList<ArrayList<City>> graph, int[] d) {
		PriorityQueue<City> heap = new PriorityQueue<>();
		heap.offer(new City(X, 0));
		
		Arrays.fill(d, Integer.MAX_VALUE);
		d[X] = 0;
		
		boolean[] visited = new boolean[d.length];
		
		while (!heap.isEmpty()) {
			City next = heap.poll();
			
			if (!visited[next.city]) {
				visited[next.city] = true;
				
				for (City city : graph.get(next.city)) {
					if (!visited[city.city] && d[city.city] > d[next.city] + city.time) {
						d[city.city] = d[next.city] + city.time;
						heap.add(new City(city.city, d[city.city]));
					}
				}
			}
		}
	}
}





