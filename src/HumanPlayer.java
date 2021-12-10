
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
                final int move = Integer.parseInt(userInput) - 1;

                if (move < 0 || move > 6) {
                    printErrorExplanation(userInput);
                    continue;
                }

                if (board.getNumCountersInColumn(move) < board.getNumRows()) {
                    return move;
                }
                else {
                    System.out.print("That column is full! Pick another one.\n");
                }
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
