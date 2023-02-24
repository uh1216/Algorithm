import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, (word1, word2) -> {
            if (word1.length() == word2.length()) {
                return word1.compareTo(word2);
            } else {
                return word1.length() - word2.length();
            }
        });

        sb.append(words[0]).append("\n");

        for (int i = 1; i < words.length; i++) {
            if (!words[i].equals(words[i - 1])) {
                sb.append(words[i]).append("\n");
            }
        }

        System.out.println(sb);
    }
}