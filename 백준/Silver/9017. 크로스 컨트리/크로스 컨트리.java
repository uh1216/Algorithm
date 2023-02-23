import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static class Team {

        int teamNum;
        int count;
        int four;
        int five;

        public Team(int teamNum, int count, int four, int five) {
            this.teamNum = teamNum;
            this.count = count;
            this.four = four;
            this.five = five;
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
            int N = Integer.parseInt(st.nextToken());

            int[] record = new int[N];

            st = new StringTokenizer(br.readLine());

            //맵으로 선수 카운트
            HashMap<Integer, Integer> count = new HashMap<>();

            for (int i = 0; i < N; i++) {
                record[i] = Integer.parseInt(st.nextToken());

                if (count.containsKey(record[i])) {
                    count.put(record[i], count.get(record[i]) + 1);
                } else {
                    count.put(record[i], 1);
                }
            }

            //우승 가능한 팀 set으로
            HashSet<Integer> canWin = new HashSet<>();

            for (Integer key : count.keySet()) {
                if (count.get(key) == 6) {
                    canWin.add(key);
                }
            }

            //set 기반으로 숫자 세기
            HashMap<Integer, Team> teams = new HashMap<>();

            int nowScore = 0;
            for (int s : record) {
                if (!canWin.contains(s)) {
                    continue;
                }
                if (teams.containsKey(s)) {
                    Team team = teams.get(s);
                    if (team.count < 4) {
                        team.four += nowScore;
                    } else if (team.count == 4) {
                        team.five = nowScore;
                    }
                    team.count++;
                } else {
                    teams.put(s, new Team(s, 1, nowScore, 0));
                }
                nowScore++;
            }
            List<Team> sortedTeam = teams.values().stream().sorted((team1, team2) -> {
                if (team1.four == team2.four) {
                    return team1.five - team2.five;
                } else {
                    return team1.four - team2.four;
                }
            }).collect(Collectors.toList());

            sb.append(sortedTeam.get(0).teamNum).append("\n");
        }
        System.out.println(sb);
    }
}