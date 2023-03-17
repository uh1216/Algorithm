import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] pibo = new int[n + 1];
        pibo[0] = 1;
        pibo[1] = 1;

        for (int i = 2; i <= n; i++) {
            pibo[i] = ((pibo[i - 1] % 10007) + (pibo[i - 2] % 10007)) % 10007;
        }

        System.out.println(pibo[n]);
    }
}