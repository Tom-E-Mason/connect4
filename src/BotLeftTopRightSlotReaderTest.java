
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotLeftTopRightSlotReaderTest {

    @Test
    void testReadFourSlots_ThreeInARow() throws ColumnFullException {

        var reader = new BotLeftTopRightSlotReader('y');
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(1, 'y');
        board.set(1, 'y');

        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');

        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');

        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');

        var move = reader.readFourSlots(board, 0, 0);
        assertEquals(0, move.column());

        move = reader.readFourSlots(board, 1, 1);
        assertEquals(4, move.column());
    }

    @Test
    void testReadFourSlots_FillTheGap() throws ColumnFullException {

        var reader = new BotLeftTopRightSlotReader('y');
        var board = new Board();

        // |   |   |   |   | y |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   |   | y | y |   |   |
        // |   | y | y | y | y |   |   |
        // | y | y | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, 'y');

        board.set(1, 'y');
        board.set(1, 'y');

        board.set(2, 'y');
        board.set(2, 'y');

        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');

        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');

        var move = reader.readFourSlots(board, 0, 0);
        assertEquals(2, move.column());

        move = reader.readFourSlots(board, 1, 1);
        assertEquals(2, move.column());
    }

    @Test
    void testReadFourSlots_ThreeInARow_RowAwareness() throws ColumnFullException {

        var reader = new BotLeftTopRightSlotReader('y');
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   | y |   |   |   |
        // |   |   | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(1, 'y');
        board.set(1, 'y');
        board.set(1, 'y');

        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');

        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');

        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');

        var move = reader.readFourSlots(board, 1, 0);
        assertNull(move);

        move = reader.readFourSlots(board, 2, 1);
        assertNull(move);
    }

    @Test
    void testReadFourSlots_FillTheGap_RowAwareness() throws ColumnFullException {

        var reader = new BotLeftTopRightSlotReader('y');
        var board = new Board();

        // |   |   |   |   | y |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   |   | y | y |   |   |
        // |   | y |   | y | y |   |   |
        // | y | y | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, 'y');

        board.set(1, 'y');
        board.set(1, 'y');

        board.set(2, 'y');

        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');

        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');
        board.set(4, 'y');

        var move = reader.readFourSlots(board, 0, 0);
        assertNull(move);

        move = reader.readFourSlots(board, 1, 1);
        assertNull(move);
    }
}