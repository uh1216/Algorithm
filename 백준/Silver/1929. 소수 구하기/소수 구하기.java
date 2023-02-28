import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int M = sc.nextInt();

        boolean[] decimal = new boolean[M+1];
        decimal[0] = true;
        decimal[1] = true;

        for (int i = 2; i <= Math.sqrt(M) + 1; i++) {
            if (!decimal[i]) {
                for (int j = i + i; j <= M; j += i) {
                    decimal[j] = true;
                }
            }
        }

        for (int i = N; i <= M; i++) {
            if (!decimal[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }
}