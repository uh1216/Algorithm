import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B20056 {
    static int N;

    //0부터 7까지 방향의 벡터 배열
    static int[] rows = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] cols = {0, 1, 1, 1, 0, -1, -1, -1};

    //파이어볼을 저장할 배열
    static FireBalls[][] map;

    //파이어볼 객체
    static class FireBall {
        int r;
        int c;
        int m;
        int s;
        int d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    //파이어볼이 겹쳐있는지 확인하는 객체
    static class FireBalls {
        //파이어볼 개수
        int count;
        //질량 합
        int ms;
        //속력 합
        int ss;
        //방향들 저장
        ArrayList<Integer> d;

        public FireBalls(int count, int ms, int ss, ArrayList<Integer> d) {
            this.count = count;
            this.ms = ms;
            this.ss = ss;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //입력 받기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //파이어볼들 저장할 배열 초기화
        map = new FireBalls[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new FireBalls(0, 0, 0, new ArrayList<>());
            }
        }
        //파이어볼 넣을 큐 생성
        Queue<FireBall> queue = new ArrayDeque<>();

        //입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken()) -1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            queue.offer(new FireBall(r, c, m, s, d));
        }

        //이동하기
        for (int i = 0; i < K; i++) {
            move(queue);
        }

        //이동 완료 후 큐에 남은 질량 다 더해줌
        int result = 0;
        int size = queue.size();
        while (--size >= 0) {
            result += queue.poll().m;
        }

        //결과 출력
        System.out.println(result);
    }

    //이동하는 함수
    private static void move(Queue<FireBall> queue) {
        //이동
        int size = queue.size();

        while (--size >= 0) {
            //현재 파이어볼
            FireBall now = queue.poll();

            //속력을 N으로 나눠주고 더한 다음, 0보다 작거나 N과 같거나 크면 범위 내에 들어가도록 조정
            int nr = now.r + rows[now.d] * (now.s % N);
            if (nr < 0) nr += N;
            if (nr >= N) nr %= N;

            int nc = now.c + cols[now.d] * (now.s % N);
            if (nc < 0) nc += N;
            if (nc >= N) nc %= N;

            //파이어볼들 저장하는 배열에 저장
            map[nr][nc].count++;
            map[nr][nc].ms += now.m;
            map[nr][nc].ss += now.s;
            map[nr][nc].d.add(now.d);
        }

        //저장한 배열을 돌며
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                FireBalls fireBalls = map[i][j];

                //파이어볼이 하나도 없다면 넘어감
                if (fireBalls.count == 0) continue;

                //파이어볼이 한개라면 큐에 추가해줌
                if (fireBalls.count == 1) {
                    queue.offer(new FireBall(i, j, fireBalls.ms, fireBalls.ss, fireBalls.d.get(0)));
                }
                //파이어볼이 두 개 이상이라면
                else {
                    //질량을 5로 나눠줌
                    int m = fireBalls.ms / 5;
                    //질량이 0이라면 해당 배열 초기화해주고 넘어감
                    if (m == 0) {
                        map[i][j] = new FireBalls(0, 0, 0, new ArrayList<>());
                        continue;
                    }

                    //속력을 카운트로 나눠줌
                    int s = fireBalls.ss / fireBalls.count;

                    //방향 다 짝수 or 홀수인지 확인
                    boolean all = false;
                    int oCount = 0;
                    int eCount = 0;
                    for (int k = 0; k < fireBalls.d.size(); k++) {
                        int t = fireBalls.d.get(k);

                        if (t % 2 == 0) eCount++;
                        else oCount++;
                    }

                    //다 짝수 or 홀수이면 피벗 true
                    if (oCount == fireBalls.count || eCount == fireBalls.count) all = true;

                    //피벗 true면 방향 0,2,4,6으로 큐에 추가
                    if (all) {
                        for (int k = 0; k <= 6; k+=2) {
                            queue.offer(new FireBall(i, j, m, s, k));
                        }
                    }
                    //피벗 false면 방향 1,3,5,7로 큐에 추가
                    else {
                        for (int k = 1; k <= 7; k+=2) {
                            queue.offer(new FireBall(i, j, m, s, k));
                        }
                    }
                }
                //해당 위치의 맵 초기화
                map[i][j] = new FireBalls(0, 0, 0, new ArrayList<>());
            }
        }
    }
}
