import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] pibo = new int[n + 2];
        pibo[1] = 1;
        pibo[2] = 3;

        for (int i = 3; i <= n; i++) {
            pibo[i] = (pibo[i - 1] + pibo[i - 2] * 2) % 10007;
        }

        System.out.println(pibo[n]);
    }
}