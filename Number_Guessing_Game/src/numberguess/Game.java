package numberguess;
public class Game {
    public enum Result {
        CORRECT,TOO_LOW,TOO_HIGH
    }
    public enum Difficulty {
        EASY(50,10),
        MEDIUM(100,7),
        HARD(200,5);

        public final int maxNumber;
        public final int maxAttempts;

        Difficulty(int maxNumber, int maxAttempts){
            this.maxNumber = maxNumber;
            this.maxAttempts = maxAttempts;
        }
    }
    private final int secret;
     private final int maxAttempts;
     private int attemptsUsed;
     private boolean won;

     public Game (Difficulty difficulty){
        java.util.Random rng  = new java.util.Random();
        this.secret = rng.nextInt(difficulty.maxNumber) + 1;
        this.maxAttempts = difficulty.maxAttempts;
        this.attemptsUsed = 0;
        this.won = false;
     }
     public Result checkGuess(int guess){
        attemptsUsed++;

        if (guess == secret){
            won = true;
            return Result.CORRECT;
        }
        else if (guess > secret) {
            return Result.TOO_HIGH;
            
        }
       else  return Result.TOO_LOW;
     }
     public boolean isOver(){
        return attemptsUsed >= maxAttempts;
     }
     public boolean isWon(){
        return won;
     }
     public int getAttempts(){
        return attemptsUsed;
     }
     public int getSecret(){
        return secret;
     }
     public int getMax(){
        return maxAttempts;
     }

}