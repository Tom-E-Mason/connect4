
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @Test
    void testGetMove_ChooseWinnerOverPreventLoss() {

        var computer = new ComputerPlayer(Colour.YELLOW);

        var board = new Board();

        // |   |   | y | y | y |   |   |
        // |   |   | r | r | r | y |   |
        //   0   1   2   3   4   5   6

        board.set(2, Colour.RED);
        board.set(3, Colour.RED);
        board.set(4, Colour.RED);
        board.set(5, Colour.YELLOW);

        board.set(2, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(4, Colour.YELLOW);

        assertEquals(5, computer.getMove(board));
    }
}