import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static String playRound(String playerMove, String computerMove) {
        playerMove = playerMove.trim().toLowerCase();
        computerMove = computerMove.toLowerCase();

        if (playerMove.equals(computerMove)) return "Draw";
        if ((playerMove.equals("rock") && computerMove.equals("scissors"))
                || (playerMove.equals("paper") && computerMove.equals("rock"))
                || (playerMove.equals("scissors") && computerMove.equals("paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static boolean isValidMove(String move) {
        return move.equalsIgnoreCase("rock")
                || move.equalsIgnoreCase("paper")
                || move.equalsIgnoreCase("scissors");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] players = new String[5];
        String[] computers = new String[5];
        String[] results = new String[5];
        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < 5; i++) {
            do {
                System.out.print("Round " + (i + 1) + " - Player (Rock/Paper/Scissors): ");
                players[i] = scanner.nextLine().trim();
                if (!isValidMove(players[i])) {
                    System.out.println("Invalid move. Please enter Rock, Paper, or Scissors.");
                }
            } while (!isValidMove(players[i]));

            computers[i] = moves[random.nextInt(moves.length)];
            results[i] = playRound(players[i], computers[i]);

            if (results[i].equals("Player Wins")) wins++;
            else if (results[i].equals("Computer Wins")) losses++;
            else draws++;
        }

        System.out.println("\nFinal Summary");
        System.out.println("Round | Player Move | Computer Move | Result");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%5d | %-11s | %-13s | %s%n",
                    i + 1, players[i], computers[i], results[i]);
        }

        double winPercentage = wins * 100.0 / 5;
        System.out.printf("\nWins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, winPercentage);
        scanner.close();
    }
}
