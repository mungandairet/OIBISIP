package numberguess;
import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game...");

        Scanner scanner = new Scanner(System.in);
        Game game = new Game(Game.Difficulty.MEDIUM);

        System.out.println(" You only have "  +game.getMax()+ "Attempts.All the best");

        while(!game.isOver()){
        System.out.print(" Enter your guess ");
        int guess = scanner.nextInt();
        Game.Result result = game.checkGuess(guess);

        if(result  == Game.Result.CORRECT){
            System.out.println("Correct, You won "  + game.getAttempts()+ "attempts");
            break;
        } else if(result ==Game.Result.TOO_HIGH){
            System.out.println("Retry, Guess is too high !");
        }
        else{
            System.out.println("Too low , Retry ");
        }
    }
        if(!game.isWon()){
            System.out.println("You have lost unfortunately: " + game.getSecret() + " was the correct number");
        }

       scanner.close();
    }
    private static Game.Difficulty chooseDifficulty(Scanner scanner){
        while(true){
            System.out.print("Choose difficulty (easy,medium,hard)");
            String input = scanner.next().toLowerCase();
            switch (input){
                case "easy":
                return Game.Difficulty.EASY;
                case "medium":
                return Game.Difficulty.MEDIUM;
                case "hard":
                return Game.Difficulty.HARD;
                default:
                    System.out.println("Invalid choice");
                
            }
        }
    }
}
