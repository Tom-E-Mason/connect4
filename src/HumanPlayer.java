
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ---------------------------------------------------------------------------------------
// The HumanPlayer accepts and validates user input, rejecting any non-integer input and
// any input that would cause an out-of-bounds exception if it were to be used to index
// the underlying 2D array. There is also a check for full columns.
// On invalid input, the user is prompted to try again.
// ---------------------------------------------------------------------------------------
public class HumanPlayer extends Player {

    HumanPlayer(SlotValue slotValue) {
        super(slotValue);
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
                    printInvalidInputMessage(userInput);
                    continue;
                }

                if (board.isColumnFull(move)) {
                    System.out.print("That column is full! Pick another one.\n");
                    continue;
                }

                return move;
            }
            catch (NumberFormatException e) {
                printInvalidInputMessage(userInput);
            }
            catch (IOException ignored) {
                throw new RuntimeException("Aborted: IO Error");
            }
        }
    }

    private static void printInvalidInputMessage(String userInput) {
        String msg = String.format("\"%s\" is not an integer from 1 to 7.",
                                   userInput);
        System.out.println(msg);
    }

    private final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
}
