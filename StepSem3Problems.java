import java.util.Arrays;

public class StepSem3Problems {

    // Problem 1
    static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] = playerScores[captainIndex] * 2;
        playerScores[viceCaptainIndex] = playerScores[viceCaptainIndex] * 1.5;
    }

    // Problem 2
    static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // Problem 3
    static String findMinMaxSpread(int[] scores) {
        int min = scores[0];
        int max = scores[0];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) min = scores[i];
            if (scores[i] > max) max = scores[i];
        }

        int spread = max - min;
        return "Min: " + min + " | Max: " + max + " | Spread: " + spread;
    }

    // Problem 4
    private static double rowAverage(int[] row) {
        int sum = 0;
        for (int i = 0; i < row.length; i++) {
            sum += row[i];
        }
        return (double) sum / row.length;
    }

    static String classifyMatches(int[][] runsPerOver, int threshold) {
        String result = "";

        for (int i = 0; i < runsPerOver.length; i++) {
            double average = rowAverage(runsPerOver[i]);

            if (average >= threshold) {
                result += "Match " + i + ": Power Surge";
            } else {
                result += "Match " + i + ": Normal";
            }

            if (i < runsPerOver.length - 1) result += " | ";
        }

        return result;
    }

    // Problem 5
    static class Player implements Comparable<Player> {
        private String name;
        private int matchesPlayed;
        private double battingAverage;
        private boolean injured;

        public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        static boolean isDraftable(int matchesPlayed) {
            return matchesPlayed >= 10;
        }

        static boolean isDraftable(int matchesPlayed, boolean injured) {
            return matchesPlayed >= 5 && !injured;
        }

        @Override
        public int compareTo(Player other) {
            return Double.compare(other.battingAverage, this.battingAverage);
        }

        public String getName() {
            return name;
        }
    }

    static String draftAndRank(Player[] players) {
        int count = 0;

        for (Player player : players) {
            if (Player.isDraftable(player.matchesPlayed)
                    || Player.isDraftable(player.matchesPlayed, player.injured)) {
                count++;
            }
        }

        Player[] draftable = new Player[count];
        int index = 0;

        for (Player player : players) {
            if (Player.isDraftable(player.matchesPlayed)
                    || Player.isDraftable(player.matchesPlayed, player.injured)) {
                draftable[index] = player;
                index++;
            }
        }

        Arrays.sort(draftable);

        String result = "";
        for (int i = 0; i < draftable.length; i++) {
            result += (i + 1) + ". " + draftable[i].getName();
            if (i < draftable.length - 1) result += " | ";
        }

        return result;
    }

    public static void main(String[] args) {
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println(Arrays.toString(scores));

        String[] playerNames = {"Kohli", "Bumrah", "Kohli", "Rohit"};
        System.out.println(findDuplicatePick(playerNames));

        int[] scoresForMinMax = {45, 82, 79, 90, 33, 90, 61};
        System.out.println(findMinMaxSpread(scoresForMinMax));

        int[][] runsPerOver = {
            {4, 6, 8},
            {10, 12, 14},
            {2, 3, 1}
        };
        System.out.println(classifyMatches(runsPerOver, 8));

        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };
        System.out.println(draftAndRank(players));
    }
}
