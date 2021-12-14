import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalSlotReaderTest {

    @Test
    void testReadFourSlots_ThreeInARow() {

        var reader = new HorizontalSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   | y | y | y |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(1, Colour.YELLOW);
        board.set(2, Colour.YELLOW);
        board.set(3, Colour.YELLOW);

        var move = reader.readFourSlots(board, 0, 0);

        assertEquals(Move.Category.WINNER, move.category());
        assertEquals(0, move.column());

        move = reader.readFourSlots(board, 0, 1);

        assertEquals(4, move.column());
    }

    @Test
    void testReadFourSlots_FillTheGap() {

        var reader = new HorizontalSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   | y |   | y | y |   | y |
        //   0   1   2   3   4   5   6

        board.set(1, Colour.RED);
        board.set(3, Colour.RED);
        board.set(4, Colour.RED);
        board.set(6, Colour.RED);

        var move = reader.readFourSlots(board, 0, 1);

        assertEquals(Move.Category.PREVENT_LOSS, move.category());
        assertEquals(2, move.column());

        move = reader.readFourSlots(board, 0, 3);

        assertEquals(5, move.column());
    }

    @Test
    void testReadFourSlots_ThreeInARow_RowAwareness() {

        var reader = new HorizontalSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   |   | y | y | y |   |   |
        // |   |   | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);

        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);

        var move = reader.readFourSlots(board, 1, 1);
        assertNull(move);

        move = reader.readFourSlots(board, 1, 2);
        assertNull(move);
    }

    @Test
    void testReadFourSlots_FillTheGap_RowAwareness() {

        var reader = new HorizontalSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   | y |   | y | y |   | y |
        // |   | y |   | y | y |   | y |
        //   0   1   2   3   4   5   6

        board.set(1, Colour.YELLOW);
        board.set(1, Colour.YELLOW);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);

        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);

        board.set(6, Colour.YELLOW);
        board.set(6, Colour.YELLOW);

        var move = reader.readFourSlots(board, 1, 1);
        assertNull(move);

        move = reader.readFourSlots(board, 1, 3);
        assertNull(move);
    }
}