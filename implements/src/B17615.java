import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] balls = st.nextToken().toCharArray();

        int red = 0;
        int blue = 0;
        for (char ball : balls) {
            if (ball == 'R') red++;
            else blue++;
        }

        if (red == N || blue == N) {
            System.out.println(0);
            return;
        }

        int result = Integer.MAX_VALUE;
        //파란색 움직이기
        result = moveBall(balls, 'B', blue, result);
        //빨간색 움직이기
        result = moveBall(balls, 'R', red, result);

        System.out.println(result);
    }

    private static int moveBall(char[] balls, char color, int count, int result) {
        if (balls[0] == color && balls[balls.length-1] == color) {
            int i = 0;
            int front = 0;
            int back = 0;
            while (balls[i] == color) i++;
            front = i;

            i = balls.length-1;
            while (balls[i] == color) i--;
            back = balls.length - i - 1;

            result = Math.min(result, count - Math.max(front, back));
        } else if (balls[0] == color && balls[balls.length-1] != color) {
            int i = 0;
            while (balls[i] == color) i++;
            result = Math.min(result, count - i);
        } else if (balls[0] != color && balls[balls.length-1] == color) {
            int i = balls.length - 1;
            while (balls[i] == color) i--;
            result = Math.min(result, count - (balls.length - i - 1));
        } else {
            result = Math.min(result, count);
        }

        return result;
    }
}
