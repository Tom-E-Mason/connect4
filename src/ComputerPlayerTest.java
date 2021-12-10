
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @Test
    void testGetMove_ChooseWinnerOverPreventLoss() {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   | y | y | y |   |   |
        // |   |   | r | r | r | y |   |
        //   0   1   2   3   4   5   6

        board.set(2, 'r');
        board.set(3, 'r');
        board.set(4, 'r');
        board.set(5, 'y');

        board.set(2, 'y');
        board.set(3, 'y');
        board.set(4, 'y');

        assertEquals(5, computer.getMove(board));
    }
}