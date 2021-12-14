import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotLeftTopRightSlotReaderTest {

    @Test
    void testReadFourSlots_ThreeInARow() {

        var reader = new BotLeftTopRightSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(1, Colour.YELLOW);
        board.set(1, Colour.YELLOW);

        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);

        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);

        var move = reader.readFourSlots(board, 0, 0);
        assertEquals(0, move.column());

        move = reader.readFourSlots(board, 1, 1);
        assertEquals(4, move.column());
    }

    @Test
    void testReadFourSlots_FillTheGap() {

        var reader = new BotLeftTopRightSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   |   |   |   | y |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   |   | y | y |   |   |
        // |   | y | y | y | y |   |   |
        // | y | y | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, Colour.YELLOW);

        board.set(1, Colour.YELLOW);
        board.set(1, Colour.YELLOW);

        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);

        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);

        var move = reader.readFourSlots(board, 0, 0);
        assertEquals(2, move.column());

        move = reader.readFourSlots(board, 1, 1);
        assertEquals(2, move.column());
    }

    @Test
    void testReadFourSlots_ThreeInARow_RowAwareness() {

        var reader = new BotLeftTopRightSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   | y |   |   |   |
        // |   |   | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        // |   | y | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(1, Colour.YELLOW);
        board.set(1, Colour.YELLOW);
        board.set(1, Colour.YELLOW);

        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);

        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);

        var move = reader.readFourSlots(board, 1, 0);
        assertNull(move);

        move = reader.readFourSlots(board, 2, 1);
        assertNull(move);
    }

    @Test
    void testReadFourSlots_FillTheGap_RowAwareness() {

        var reader = new BotLeftTopRightSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   |   |   |   | y |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   |   | y | y |   |   |
        // |   | y |   | y | y |   |   |
        // | y | y | y | y | y |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, Colour.YELLOW);

        board.set(1, Colour.YELLOW);
        board.set(1, Colour.YELLOW);

        board.set(2, Colour.YELLOW);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);

        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);

        var move = reader.readFourSlots(board, 0, 0);
        assertNull(move);

        move = reader.readFourSlots(board, 1, 1);
        assertNull(move);
    }
}