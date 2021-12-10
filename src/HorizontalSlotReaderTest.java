
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalSlotReaderTest {

    @Test
    void testReadFourSlots_ThreeInARow() throws ColumnFullException {

        var reader = new HorizontalSlotReader('y');
        var board = new Board();

        // |   | y | y | y |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(1, 'y');
        board.set(2, 'y');
        board.set(3, 'y');

        var move = reader.readFourSlots(board, 0, 0);

        assertEquals(Move.Category.WINNER, move.category());
        assertEquals(0, move.column());

        move = reader.readFourSlots(board, 0, 1);

        assertEquals(4, move.column());
    }

    @Test
    void testReadFourSlots_FillTheGap() throws ColumnFullException {

        var reader = new HorizontalSlotReader('y');
        var board = new Board();

        // |   | y |   | y | y |   | y |
        //   0   1   2   3   4   5   6

        board.set(1, 'r');
        board.set(3, 'r');
        board.set(4, 'r');
        board.set(6, 'r');

        var move = reader.readFourSlots(board, 0, 1);

        assertEquals(Move.Category.PREVENT_LOSS, move.category());
        assertEquals(2, move.column());

        move = reader.readFourSlots(board, 0, 3);

        assertEquals(5, move.column());
    }

    @Test
    void testReadFourSlots_ThreeInARow_RowAwareness() throws ColumnFullException {

        var reader = new HorizontalSlotReader('y');
        var board = new Board();

        // |   |   | y | y | y |   |   |
        // |   |   | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(2, 'y');
        board.set(2, 'y');

        board.set(3, 'y');
        board.set(3, 'y');

        board.set(4, 'y');
        board.set(4, 'y');

        var move = reader.readFourSlots(board, 1, 1);
        assertNull(move);

        move = reader.readFourSlots(board, 1, 2);
        assertNull(move);
    }

    @Test
    void testReadFourSlots_FillTheGap_RowAwareness() throws ColumnFullException {

        var reader = new HorizontalSlotReader('y');
        var board = new Board();

        // |   | y |   | y | y |   | y |
        // |   | y |   | y | y |   | y |
        //   0   1   2   3   4   5   6

        board.set(1, 'y');
        board.set(1, 'y');

        board.set(3, 'y');
        board.set(3, 'y');

        board.set(4, 'y');
        board.set(4, 'y');

        board.set(6, 'y');
        board.set(6, 'y');

        var move = reader.readFourSlots(board, 1, 1);
        assertNull(move);

        move = reader.readFourSlots(board, 1, 3);
        assertNull(move);
    }
}