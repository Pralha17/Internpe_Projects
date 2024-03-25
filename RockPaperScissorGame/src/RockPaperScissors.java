import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    private static int userScore = 0;
    private static int computerScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] choices = {"rock", "paper", "scissors"};

        while (true) {
            System.out.println("Enter your choice (rock, paper, or scissors) or 'exit' to quit:");
            String userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("exit")) {
                System.out.println("Final Score: You - " + userScore + ", Computer - " + computerScore);
                System.out.println("Exiting the game...");
                break;
            }

            if (!isValidChoice(userChoice)) {
                System.out.println("Invalid choice! Please enter 'rock', 'paper', or 'scissors'.");
                continue;
            }

            int computerIndex = random.nextInt(3);
            String computerChoice = choices[computerIndex];

            System.out.println("Computer's choice: " + computerChoice);

            determineWinner(userChoice, computerChoice);
        }

        scanner.close();
    }

    private static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }

    private static void determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            System.out.println("It's a tie!");
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            System.out.println("You win!");
            userScore++;
        } else {
            System.out.println("Computer wins!");
            computerScore++;
        }

        System.out.println("Score: You - " + userScore + ", Computer - " + computerScore);
    }
}
