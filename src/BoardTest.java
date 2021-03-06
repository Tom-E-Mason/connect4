
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testSetAndGet() {

        Board board = new Board();

        assertNull(board.get(0, 0));

        int row = board.set(0, Colour.RED);
        assertEquals(Colour.RED, board.get(0, 0));
        assertEquals(0, row);

        assertNull(board.get(0, 1));

        row = board.set(0, Colour.RED);
        assertEquals(Colour.RED, board.get(0, 1));
        assertEquals(1, row);
    }

    @Test
    void testIsValidMove() {

        var board = new Board();

        assert(0 == board.getNumCountersInColumn(0));
        assert(0 == board.getNumCountersInColumn(6));

        assert(!(1 == board.getNumCountersInColumn(0)));
        assert(!(1 == board.getNumCountersInColumn(6)));
    }

    @Test
    void testColumnOverflow() {

        Board board = new Board();

        assertEquals(0, board.getNumCountersInColumn(0));

        board.set(0, Colour.RED);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.RED);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.RED);
        board.set(0, Colour.YELLOW);

        assertEquals(6, board.getNumCountersInColumn(0));
        assertThrows(ColumnFullException.class, () -> board.set(0, Colour.RED));
    }

    @Test
    void testToString_EmptyBoard() {

        Board board = new Board();

        String emptyBoard = """
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                  1   2   3   4   5   6   7
                """;


        assertEquals(emptyBoard, board.toString());
    }

    @Test
    void testToString_BoardWithColumns() {

        Board board = new Board();

        String boardWithColumns = """
                | y |   |   |   |   |   | r |
                | r |   |   |   |   |   | y |
                | y |   |   |   |   |   | r |
                | r |   |   |   |   |   | y |
                | y |   |   |   |   |   | r |
                | r |   |   |   |   |   | y |
                  1   2   3   4   5   6   7
                """;

        board.set(0, Colour.RED); board.set(6, Colour.YELLOW);
        board.set(0, Colour.YELLOW); board.set(6, Colour.RED);
        board.set(0, Colour.RED); board.set(6, Colour.YELLOW);
        board.set(0, Colour.YELLOW); board.set(6, Colour.RED);
        board.set(0, Colour.RED); board.set(6, Colour.YELLOW);
        board.set(0, Colour.YELLOW); board.set(6, Colour.RED);

        assertEquals(boardWithColumns, board.toString());
    }

    @Test
    void testToString_BoardWithRows() {

        Board board = new Board();

        String boardWithRows = """
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                | y | y | y | r | r | r | r |
                | r | r | r | y | y | y | y |
                  1   2   3   4   5   6   7
                """;

        board.set(0, Colour.RED); board.set(0, Colour.YELLOW);
        board.set(1, Colour.RED); board.set(1, Colour.YELLOW);
        board.set(2, Colour.RED); board.set(2, Colour.YELLOW);
        board.set(3, Colour.YELLOW); board.set(3, Colour.RED);
        board.set(4, Colour.YELLOW); board.set(4, Colour.RED);
        board.set(5, Colour.YELLOW); board.set(5, Colour.RED);
        board.set(6, Colour.YELLOW); board.set(6, Colour.RED);

        assertEquals(boardWithRows, board.toString());
    }

    @Test
    void test_ConnectFour_Horizontal() {

        var board = new Board();

        board.set(0, Colour.RED);
        board.set(1, Colour.RED);
        board.set(2, Colour.RED);
        board.set(3, Colour.RED);

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        board.set(3, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(5, Colour.YELLOW);
        board.set(6, Colour.YELLOW);

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));

        board = new Board();

        board.set(0, Colour.RED);
        board.set(1, Colour.YELLOW);
        board.set(2, Colour.RED);
        board.set(3, Colour.RED);

        assert(!board.connectFour(0));
        assert(!board.connectFour(1));
        assert(!board.connectFour(2));
        assert(!board.connectFour(3));

        board = new Board();

        board.set(0, Colour.RED);
        board.set(0, Colour.RED);

        board.set(4, Colour.RED);

        board.set(5, Colour.RED);

        board.set(6, Colour.RED);

        assert(!board.connectFour(6));
    }

    @Test
    void test_ConnectFour_Vertical() {

        var board = new Board();

        // | r |   |   |   |   |   |   |
        // | r |   |   |   |   |   |   |
        // | r |   |   |   |   |   |   |
        // | r |   |   |   |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, Colour.RED);
        board.set(0, Colour.RED);
        board.set(0, Colour.RED);
        board.set(0, Colour.RED);

        assert(board.connectFour(0));

        board = new Board();

        // |   |   |   |   |   |   | y |
        // |   |   |   |   |   |   | y |
        // |   |   |   |   |   |   | y |
        // |   |   |   |   |   |   | y |
        //   0   1   2   3   4   5   6

        board.set(6, Colour.YELLOW);
        board.set(6, Colour.YELLOW);
        board.set(6, Colour.YELLOW);
        board.set(6, Colour.YELLOW);

        assert(board.connectFour(6));

        board = new Board();

        // | r |   |   |   |   |   |   |
        // | r |   |   |   |   |   |   |
        // | y |   |   |   |   |   |   |
        // | r |   |   |   |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, Colour.RED);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.RED);
        board.set(0, Colour.RED);

        assert(!board.connectFour(0));

        board = new Board();

        // | r |   |   |   |   |   |   |
        // | r |   |   |   |   |   |   |
        // | r |   |   |   |   |   |   |
        // | y |   |   |   |   |   |   |
        // | y |   |   |   |   |   |   |
        // | y |   |   |   |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, Colour.YELLOW);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.RED);
        board.set(0, Colour.RED);
        board.set(0, Colour.RED);

        board.set(1, Colour.RED);

        assert(!board.connectFour(0));
    }

    @Test
    void test_ConnectFour_Diagonal_BotLeft_To_TopRight() {

        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // |   |   |   | r |   |   |   |
        // |   |   | r | y |   |   |   |
        // |   | r | y | y |   |   |   |
        // | r | y | y | y |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, Colour.RED);

        board.set(1, Colour.YELLOW);
        board.set(1, Colour.RED);

        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);
        board.set(2, Colour.RED);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.RED);

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        // |   |   |   |   |   |   | y |
        // |   |   |   |   |   | y | r |
        // |   |   |   |   | y | r | r |
        // |   |   |   | y | r | r | r |
        //   0   1   2   3   4   5   6

        board.set(3, Colour.YELLOW);

        board.set(4, Colour.RED);
        board.set(4, Colour.YELLOW);

        board.set(5, Colour.RED);
        board.set(5, Colour.RED);
        board.set(5, Colour.YELLOW);

        board.set(6, Colour.RED);
        board.set(6, Colour.RED);
        board.set(6, Colour.RED);
        board.set(6, Colour.YELLOW);

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));

        board = new Board();

        // |   |   |   | y |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   | y | r | r |   |   |   |
        // | y | r | y | r |   |   |   |
        // | r | y | y | r |   |   |   |
        // | r | r | r | y |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, Colour.RED);
        board.set(0, Colour.RED);
        board.set(0, Colour.YELLOW);

        board.set(1, Colour.RED);
        board.set(1, Colour.YELLOW);
        board.set(1, Colour.RED);
        board.set(1, Colour.YELLOW);

        board.set(2, Colour.RED);
        board.set(2, Colour.YELLOW);
        board.set(2, Colour.YELLOW);
        board.set(2, Colour.RED);
        board.set(2, Colour.YELLOW);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.RED);
        board.set(3, Colour.RED);
        board.set(3, Colour.RED);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        // |   |   |   |   |   |   | y |
        // |   |   |   |   |   | y | y |
        // |   |   |   |   | y | r | r |
        // |   |   |   | y | r | y | r |
        // |   |   |   | r | y | y | r |
        // |   |   |   | r | r | r | y |
        //   0   1   2   3   4   5   6

        board.set(3, Colour.RED);
        board.set(3, Colour.RED);
        board.set(3, Colour.YELLOW);

        board.set(4, Colour.RED);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.RED);
        board.set(4, Colour.YELLOW);

        board.set(5, Colour.RED);
        board.set(5, Colour.YELLOW);
        board.set(5, Colour.YELLOW);
        board.set(5, Colour.RED);
        board.set(5, Colour.YELLOW);

        board.set(6, Colour.YELLOW);
        board.set(6, Colour.RED);
        board.set(6, Colour.RED);
        board.set(6, Colour.RED);
        board.set(6, Colour.YELLOW);
        board.set(6, Colour.YELLOW);

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));
    }

    @Test
    void test_ConnectFour_Diagonal_BotRight_To_TopLeft() {

        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // | r |   |   |   |   |   |   |
        // | y | r |   |   |   |   |   |
        // | y | y | r |   |   |   |   |
        // | y | y | y | r |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(3, Colour.RED);

        board.set(2, Colour.YELLOW);
        board.set(2, Colour.RED);

        board.set(1, Colour.YELLOW);
        board.set(1, Colour.YELLOW);
        board.set(1, Colour.RED);

        board.set(0, Colour.YELLOW);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.RED);

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   |   |   |   |   |
        // |   |   |   | r |   |   |   |
        // |   |   |   | y | r |   |   |
        // |   |   |   | y | y | r |   |
        // |   |   |   | y | y | y | r |
        //   0   1   2   3   4   5   6

        board.set(6, Colour.RED);

        board.set(5, Colour.YELLOW);
        board.set(5, Colour.RED);

        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.RED);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.RED);

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));

        board = new Board();

        // | r |   |   |   |   |   |   |
        // | y | r |   |   |   |   |   |
        // | r | r | r |   |   |   |   |
        // | r | y | r | r |   |   |   |
        // | r | y | y | y |   |   |   |
        // | y | r | r | r |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(3, Colour.RED);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.RED);

        board.set(2, Colour.RED);
        board.set(2, Colour.YELLOW);
        board.set(2, Colour.RED);
        board.set(2, Colour.RED);

        board.set(1, Colour.RED);
        board.set(1, Colour.YELLOW);
        board.set(1, Colour.YELLOW);
        board.set(1, Colour.RED);
        board.set(1, Colour.RED);

        board.set(0, Colour.YELLOW);
        board.set(0, Colour.RED);
        board.set(0, Colour.RED);
        board.set(0, Colour.RED);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.RED);

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        // |   |   |   | r |   |   |   |
        // |   |   |   | y | r |   |   |
        // |   |   |   | r | r | r |   |
        // |   |   |   | r | y | r | r |
        // |   |   |   | r | y | y | y |
        // |   |   |   | y | r | r | r |
        //   0   1   2   3   4   5   6

        board.set(6, Colour.RED);
        board.set(6, Colour.YELLOW);
        board.set(6, Colour.RED);

        board.set(5, Colour.RED);
        board.set(5, Colour.YELLOW);
        board.set(5, Colour.RED);
        board.set(5, Colour.RED);

        board.set(4, Colour.RED);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.YELLOW);
        board.set(4, Colour.RED);
        board.set(4, Colour.RED);

        board.set(3, Colour.YELLOW);
        board.set(3, Colour.RED);
        board.set(3, Colour.RED);
        board.set(3, Colour.RED);
        board.set(3, Colour.YELLOW);
        board.set(3, Colour.RED);

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));
    }

    @Test
    void testIsFull() {

        var board = new Board();

        assertFalse(board.isColumnFull(0));
        assertFalse(board.isFull());

        for (int column = 0; column < board.getNumColumns(); ++column) {

            board.set(column, Colour.RED);
            board.set(column, Colour.RED);
            board.set(column, Colour.RED);
            board.set(column, Colour.RED);
            board.set(column, Colour.RED);
            board.set(column, Colour.RED);
        }

        assertTrue(board.isColumnFull(0));
        assertTrue(board.isFull());
    }
}