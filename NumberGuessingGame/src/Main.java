import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String play = "yes";
        while(play.equals("yes")){
            System.out.println("Choose difficulty level: (1) Easy (2) Medium (3) Hard");
            int difficulty = in.nextInt();
            int maxRange = 0;
            int maxAttempts = 0;
            switch (difficulty) {
                case 1:
                    maxRange = 50;
                    maxAttempts = 10;
                    break;
                case 2:
                    maxRange = 100;
                    maxAttempts = 7;
                    break;
                case 3:
                    maxRange = 200;
                    maxAttempts = 5;
                    break;
                default:
                    System.out.println("Invalid difficulty level. Setting to Easy.");
                    maxRange = 50;
                    maxAttempts = 10;
                    break;
            }

            Random rand = new Random();
            int randInt = rand.nextInt(maxRange) + 1;
            int guess = -1;
            int tries = 0;
            long startTime = System.nanoTime();

            while(guess != randInt && tries < maxAttempts){
                System.out.println("Guess the number between 1 to " + maxRange);
                guess = in.nextInt();
                tries++;
                if (guess == randInt){
                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
                    System.out.println("Awesome! You guessed it right!!");
                    System.out.println("It took you " + tries + " tries and " + TimeUnit.MILLISECONDS.toSeconds(duration) + " seconds to guess the right number.");
                } else if (guess > randInt) {
                    System.out.println("Your guess is too high");
                } else {
                    System.out.println("Your guess is too low");
                }
            }

            if (tries >= maxAttempts) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + randInt);
            }

            System.out.println("Would you like to play again? Type 'yes' to play again, else type 'no'");
            play = in.next().toLowerCase();
        }

        in.close();
    }
}
