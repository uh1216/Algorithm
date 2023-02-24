import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int now = 666;
        int count = 0;
        while (true) {
            if ((now + "").contains("666")) {
                count++;
            }
            if (N == count) {
                break;
            }
            now += 1;
        }

        System.out.println(now);
    }
}