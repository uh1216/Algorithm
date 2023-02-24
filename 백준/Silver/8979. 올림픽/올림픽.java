import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    static class Country implements Comparable<Country> {

        int num;
        int gold;
        int silver;
        int bronze;

        public Country(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country o) {
            if (this.gold == o.gold) {
                if (this.silver == o.silver) {
                    return o.bronze - this.bronze;
                } else {
                    return o.silver - this.silver;
                }
            } else {
                return o.gold - this.gold;
            }
        }

        @Override
        public String toString() {
            return "Country{" +
                "num=" + num +
                ", gold=" + gold +
                ", silver=" + silver +
                ", bronze=" + bronze +
                '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Country[] countries = new Country[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            countries[i] = new Country(num, gold, silver, bronze);
        }

        Arrays.sort(countries);

        int rank = 1;

        if (countries[0].num == K) {
            System.out.println(rank);
            return;
        }

        int count = 1;
        for (int i = 1; i < N; i++) {
            if (countries[i].gold == countries[i - 1].gold
                && countries[i].silver == countries[i - 1].silver
                && countries[i].bronze == countries[i - 1].bronze) {
                count++;
            } else {
                rank += count;
                count = 1;
            }
            if (countries[i].num == K) {
                break;
            }
        }

        System.out.println(rank);
    }
}