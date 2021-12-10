import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

    HumanPlayer(char colour) {
        super(colour);
    }

    @Override
    protected int getMove(Board board) {

        String userInput = null;

        while (true) {
            try {
                System.out.print("Your move: ");
                userInput = input.readLine();
                final int move = Integer.parseInt(userInput);

                if (move < 1 || move > 7) {
                    printErrorExplanation(userInput);
                    continue;
                }

                return move - 1;
            }
            catch (NumberFormatException e) {
                printErrorExplanation(userInput);
            }
            catch (IOException ignored) {

            }
        }
    }

    private static void printErrorExplanation(String userInput) {
        String msg = String.format("\"%s\" is not an integer from 1 to 7.",
                                   userInput);
        System.out.println(msg);
    }

    private final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
}
