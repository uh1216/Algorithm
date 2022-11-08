import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B20437 {

    static class Word {
        int count;
        Queue<Integer> queue;

        public Word(int count, Queue<Integer> queue) {
            this.count = count;
            this.queue = queue;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());
            char[] word = st.nextToken().toCharArray();

            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            if (K == 1) {
                sb.append(1).append(" ").append(1).append("\n");
                continue;
            }

            Word[] alpha = new Word[26];

            for (int i = 0; i < 26; i++) {
                alpha[i] = new Word(0, new ArrayDeque<>());
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < word.length; i++) {
                int w = word[i] - 'a';

                alpha[w].count++;
                if (alpha[w].count == K) {
                    alpha[w].count--;

                    int index = alpha[w].queue.poll();
                    int len = i - index + 1;

                    max = Math.max(max, len);
                    min = Math.min(min, len);
                }
                alpha[w].queue.offer(i);
            }

            if (min == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }
}
