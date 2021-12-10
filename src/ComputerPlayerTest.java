
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @Test
    void testGetMove_ChooseWinnerOverPreventLoss() {

        var computer = new ComputerPlayer(SlotValue.YELLOW);

        var board = new Board();

        // |   |   | y | y | y |   |   |
        // |   |   | r | r | r | y |   |
        //   0   1   2   3   4   5   6

        board.set(2, SlotValue.RED);
        board.set(3, SlotValue.RED);
        board.set(4, SlotValue.RED);
        board.set(5, SlotValue.YELLOW);

        board.set(2, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(4, SlotValue.YELLOW);

        assertEquals(5, computer.getMove(board));
    }
}