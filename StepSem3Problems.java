import java.util.Arrays;

public class StepSem3Problems {

    // Problem 1
    static void curveScores(int[] scores, int bonus) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus;
        }
    }

    // Problem 2
    static String findDuplicateTeam(String[] teamNames) {
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // Problem 3
    static int[] findTopThreeScores(int[] scores) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int score : scores) {
            if (score >= first) {
                third = second;
                second = first;
                first = score;
            } else if (score >= second) {
                third = second;
                second = score;
            } else if (score > third) {
                third = score;
            }
        }

        return new int[]{first, second, third};
    }

    // Problem 4
    private static double rowAverage(int[] row) {
        int sum = 0;
        for (int score : row) {
            sum += score;
        }
        return (double) sum / row.length;
    }

    static String classifyRows(int[][] seatingScores, int threshold) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < seatingScores.length; i++) {
            double average = rowAverage(seatingScores[i]);

            if (average < threshold) {
                result.append("Row ").append(i).append(": Quiet Zone");
            } else {
                result.append("Row ").append(i).append(": Buzzing Zone");
            }

            if (i < seatingScores.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    // Problem 5
    static class Candidate implements Comparable<Candidate> {
        private String name;
        private double cgpa;
        private int codingScore;

        public Candidate(String name, double cgpa, int codingScore) {
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
        }

        static boolean isEligible(double cgpa) {
            return cgpa >= 8.0;
        }

        static boolean isEligible(double cgpa, int codingScore) {
            return cgpa >= 6.5 && codingScore >= 60;
        }

        private double compositeScore() {
            return cgpa * 10 + codingScore * 0.5;
        }

        @Override
        public int compareTo(Candidate other) {
            return Double.compare(other.compositeScore(), this.compositeScore());
        }

        @Override
        public String toString() {
            return name + " (" + compositeScore() + ")";
        }
    }

    static String shortlistAndRank(Candidate[] candidates) {
        int count = 0;

        for (Candidate candidate : candidates) {
            if (Candidate.isEligible(candidate.cgpa)
                    || Candidate.isEligible(candidate.cgpa, candidate.codingScore)) {
                count++;
            }
        }

        Candidate[] shortlisted = new Candidate[count];
        int index = 0;

        for (Candidate candidate : candidates) {
            if (Candidate.isEligible(candidate.cgpa)
                    || Candidate.isEligible(candidate.cgpa, candidate.codingScore)) {
                shortlisted[index++] = candidate;
            }
        }

        Arrays.sort(shortlisted);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shortlisted.length; i++) {
            result.append(i + 1).append(". ").append(shortlisted[i]);

            if (i < shortlisted.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Problem 1
        int[] scores = {70, 85, 60};
        curveScores(scores, 10);
        System.out.println(Arrays.toString(scores));

        // Problem 2
        String[] teamNames = {"ByteForce", "CodeCrafters", "ByteForce"};
        System.out.println(findDuplicateTeam(teamNames));

        // Problem 3
        int[] podiumScores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println(Arrays.toString(findTopThreeScores(podiumScores)));

        // Problem 4
        int[][] seatingScores = {
            {40, 50, 45},
            {85, 90, 95},
            {30, 20, 25}
        };
        System.out.println(classifyRows(seatingScores, 60));

        // Problem 5
        Candidate[] candidates = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };
        System.out.println(shortlistAndRank(candidates));
    }
}
