import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotRightTopLeftSlotReaderTest {

    @Test
    void testReadFourSlots_ThreeInARow() {

        var reader = new BotRightTopLeftSlotReader(SlotValue.YELLOW);
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y | y |   |   |
        // |   |   | y | y | y | y |   |
        // |   |   | y | y | y | y |   |
        //   0   1   2   3   4   5   6

        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);

        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);

        board.set(4, SlotValue.YELLOW);
        board.set(4, SlotValue.YELLOW);
        board.set(4, SlotValue.YELLOW);

        board.set(5, SlotValue.YELLOW);
        board.set(5, SlotValue.YELLOW);

        var move = reader.readFourSlots(board, 0, 6);
        assertEquals(6, move.column());

        move = reader.readFourSlots(board, 1, 5);
        assertEquals(2, move.column());
    }

    @Test
    void testReadFourSlots_FillTheGap() {

        var reader = new BotRightTopLeftSlotReader(SlotValue.YELLOW);
        var board = new Board();

        // |   |   | y |   |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y | y | y |   |
        // |   |   | y | y | y | y | y |
        //   0   1   2   3   4   5   6

        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);

        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);

        board.set(4, SlotValue.YELLOW);
        board.set(4, SlotValue.YELLOW);

        board.set(5, SlotValue.YELLOW);
        board.set(5, SlotValue.YELLOW);

        board.set(6, SlotValue.YELLOW);

        var move = reader.readFourSlots(board, 0, 6);
        assertEquals(4, move.column());

        move = reader.readFourSlots(board, 1, 5);
        assertEquals(4, move.column());
    }

    @Test
    void testReadFourSlots_ThreeInARow_RowAwareness() {

        var reader = new BotRightTopLeftSlotReader(SlotValue.YELLOW);
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   | y |   |   |   |
        // |   |   | y | y | y |   |   |
        // |   |   | y | y | y | y |   |
        // |   |   | y | y | y | y |   |
        // |   |   | y | y | y | y |   |
        //   0   1   2   3   4   5   6

        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);

        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);

        board.set(4, SlotValue.YELLOW);
        board.set(4, SlotValue.YELLOW);
        board.set(4, SlotValue.YELLOW);
        board.set(4, SlotValue.YELLOW);

        board.set(5, SlotValue.YELLOW);
        board.set(5, SlotValue.YELLOW);
        board.set(5, SlotValue.YELLOW);

        var move = reader.readFourSlots(board, 1, 6);
        assertNull(move);

        move = reader.readFourSlots(board, 2, 5);
        assertNull(move);
    }

    @Test
    void testReadFourSlots_FillTheGap_RowAwareness() {

        var reader = new BotRightTopLeftSlotReader(SlotValue.YELLOW);
        var board = new Board();

        // |   |   | y |   |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | y | y |   | y |   |
        // |   |   | y | y | y | y | y |
        //   0   1   2   3   4   5   6

        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);
        board.set(2, SlotValue.YELLOW);

        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);
        board.set(3, SlotValue.YELLOW);

        board.set(4, SlotValue.YELLOW);

        board.set(5, SlotValue.YELLOW);
        board.set(5, SlotValue.YELLOW);

        board.set(6, SlotValue.YELLOW);

        var move = reader.readFourSlots(board, 0, 6);
        assertNull(move);

        move = reader.readFourSlots(board, 1, 5);
        assertNull(move);
    }
}