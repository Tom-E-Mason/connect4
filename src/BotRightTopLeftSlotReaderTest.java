import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotRightTopLeftSlotReaderTest {

    @Test
    void testReadFourSlots_ThreeInARow() throws ColumnFullException {

        var reader = new BotRightTopLeftSlotReader('y');
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y | y |   |   |
        // |   |   | y | y | y | y |   |
        // |   |   | y | y | y | y |   |
        //   0   1   2   3   4   5   6

        board.set(2, 'y');
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

        board.set(5, 'y');
        board.set(5, 'y');

        var move = reader.readFourSlots(board, 0, 6);
        assertEquals(6, move.column());

        move = reader.readFourSlots(board, 1, 5);
        assertEquals(2, move.column());
    }

    @Test
    void testReadFourSlots_FillTheGap() throws ColumnFullException {

        var reader = new BotRightTopLeftSlotReader('y');
        var board = new Board();

        // |   |   | y |   |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y | y | y |   |
        // |   |   | y | y | y | y | y |
        //   0   1   2   3   4   5   6

        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');

        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');

        board.set(4, 'y');
        board.set(4, 'y');

        board.set(5, 'y');
        board.set(5, 'y');

        board.set(6, 'y');

        var move = reader.readFourSlots(board, 0, 6);
        assertEquals(4, move.column());

        move = reader.readFourSlots(board, 1, 5);
        assertEquals(4, move.column());
    }

    @Test
    void testReadFourSlots_ThreeInARow_RowAwareness() throws ColumnFullException {

        var reader = new BotRightTopLeftSlotReader('y');
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   | y |   |   |   |
        // |   |   | y | y | y |   |   |
        // |   |   | y | y | y | y |   |
        // |   |   | y | y | y | y |   |
        // |   |   | y | y | y | y |   |
        //   0   1   2   3   4   5   6

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

        board.set(5, 'y');
        board.set(5, 'y');
        board.set(5, 'y');

        var move = reader.readFourSlots(board, 1, 6);
        assertNull(move);

        move = reader.readFourSlots(board, 2, 5);
        assertNull(move);
    }

    @Test
    void testReadFourSlots_FillTheGap_RowAwareness() throws ColumnFullException {

        var reader = new BotRightTopLeftSlotReader('y');
        var board = new Board();

        // |   |   | y |   |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y |   | y |   |
        // |   |   | y | y | y | y | y |
        //   0   1   2   3   4   5   6

        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'y');

        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');

        board.set(4, 'y');

        board.set(5, 'y');
        board.set(5, 'y');

        board.set(6, 'y');

        var move = reader.readFourSlots(board, 0, 6);
        assertNull(move);

        move = reader.readFourSlots(board, 1, 5);
        assertNull(move);
    }
}