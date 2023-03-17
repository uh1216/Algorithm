import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int v;
        Node next;

        public Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node[] adjList = new Node[N + 1];
        int[] graph = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        int num = 1;
        for (int i = 1; i <= N; i++) {
            if (graph[i] != 0) {
                continue;
            }

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(i);

            while (!queue.isEmpty()) {
                int now = queue.poll();
                graph[now] = num;

                for (Node n = adjList[now]; n != null; n = n.next) {
                    if (graph[n.v] == 0) {
                        graph[n.v] = num;
                        queue.add(n.v);
                    }
                }
            }
            num++;
        }
        System.out.println(num - 1);
    }
}