package numberguess;
public class GameTest {
    public static void main(String[] args) {
        Game g = new Game(Game.Difficulty.MEDIUM);

        System.out.println("Secret is :"  +g.getSecret());
        System.out.println("Max attempts :"  +g.getMax());
        System.out.println("Attempts used:"  +g.getAttempts());
        System.out.println("Is game over ? :"  +g.isOver());
        System.out.println("Did you win:"  +g.isWon());

        System.out.println("\n Guessing  50 ..");
        Game.Result r = g.checkGuess(50);
        System.out.println("Result:"  +r);
        System.out.println("Attempts used:"  +g.getAttempts());


        System.out.println("\nGuessing secret directly ...");
            r = g.checkGuess(g.getSecret());
        System.out.println("Result:"  +r);
        System.out.println("Have won ?"  +g.isWon());

    }
}