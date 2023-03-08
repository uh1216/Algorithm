import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        BigInteger bigInteger = BigInteger.ONE;

        for (int i = 1; i <= N; i++) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
        }

        String num = bigInteger + "";

        int count = 0;
        for (int i = num.length()-1; i >= 0; i--) {
            if (num.charAt(i) != '0') {
                break;
            }
            count++;
        }

        System.out.println(count);
    }
}