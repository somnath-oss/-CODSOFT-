import java.util.Scanner;
import java.util.Random;

public class numberguessing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();

        int score = 0;
        String playagain;

        System.out.println("\n Welcome to the Number Guessing Game!");

        do {
            // 1. Difficulty Selection

            System.out.println("\n Select difficulty level:");
            System.out.println("1. Easy (1-50)\n2. Medium (1-100)\n3. Hard (1-200)");
            int difficulty = scan.nextInt();
            int upperLimit;

            switch (difficulty) {
                case 1: upperLimit = 50; break;
                case 2: upperLimit = 100; break;
                case 3: upperLimit = 200; break;
                default:
                    upperLimit = 100;
                    System.out.println("Invalid input! Using Medium (1-100).");
            }

            int guessnum = ran.nextInt(upperLimit) + 1;
            int maxattempts = 10;
            int attempts = 0;
            boolean correctguess = false;
            boolean hintUsed = false;

            System.out.println("New Round Started!");
            System.out.println("I selected a number between 1 and " + upperLimit + ".");
            System.out.println("You have " + maxattempts + " attempts to guess it.");

           
            // Game Loop
            while (attempts < maxattempts) {
                System.out.print("Enter your guess: ");
                int userguess = scan.nextInt();
                attempts++;

                if (!hintUsed) {
                    System.out.print("Do you want a hint? (yes/no): ");
                    String wantHint = scan.next().toLowerCase();
                    if (wantHint.equals("yes")) {
                        if (guessnum % 2 == 0)
                            System.out.println("Hint: The number is even.");
                        else
                            System.out.println("Hint: The number is odd.");
                        hintUsed = true;
                    }
                }

                if (userguess < guessnum) {
                    System.out.println("Too low! Try again.");
                } else if (userguess > guessnum) {
                    System.out.println("Too high! Try again.");
                } else {
                    correctguess = true;
                    break;
                }
            }


            // Result and Scoring
            if (correctguess) {
                System.out.println("Congrats! You guessed it in " + attempts + " attempts.");
                int pointsthisround = 10 - (attempts - 1);
                if (pointsthisround < 1) pointsthisround = 1;
                score += pointsthisround;
                System.out.println("You earned " + pointsthisround + " points this round.");
            } else {
                System.out.println("You've used all attempts. The number was: " + guessnum);
            }


            System.out.print("Do you want to play again? (yes/no): ");
            playagain = scan.next().toLowerCase();
        } while (playagain.equals("yes"));


        // Final score
        System.out.println("Thanks for playing! Your final score: " + score);

        scan.close();
    }
}
