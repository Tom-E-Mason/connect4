
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testSetAndGet() throws ColumnFullException {

        Board board = new Board();

        assertEquals('\0', board.get(0, 0));

        int row = board.set(0, 'r');
        assertEquals('r', board.get(0, 0));
        assertEquals(0, row);

        assertEquals('\0', board.get(0, 1));

        row = board.set(0,'r');
        assertEquals('r', board.get(0, 1));
        assertEquals(1, row);
    }

    @Test
    void testColumnOverflow() throws ColumnFullException {

        Board board = new Board();

        board.set(0, 'r');
        board.set(0, 'y');
        board.set(0, 'r');
        board.set(0, 'y');
        board.set(0, 'r');
        board.set(0, 'y');

        assertThrows(ColumnFullException.class, () -> board.set(0, 'r'));
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
    void testToString_BoardWithColumns() throws ColumnFullException {

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

        board.set(0, 'r'); board.set(6, 'y');
        board.set(0, 'y'); board.set(6, 'r');
        board.set(0, 'r'); board.set(6, 'y');
        board.set(0, 'y'); board.set(6, 'r');
        board.set(0, 'r'); board.set(6, 'y');
        board.set(0, 'y'); board.set(6, 'r');

        assertEquals(boardWithColumns, board.toString());
    }

    @Test
    void testToString_BoardWithRows() throws ColumnFullException {

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

        board.set(0, 'r'); board.set(0, 'y');
        board.set(1, 'r'); board.set(1, 'y');
        board.set(2, 'r'); board.set(2, 'y');
        board.set(3, 'y'); board.set(3, 'r');
        board.set(4, 'y'); board.set(4, 'r');
        board.set(5, 'y'); board.set(5, 'r');
        board.set(6, 'y'); board.set(6, 'r');

        assertEquals(boardWithRows, board.toString());
    }

    @Test
    void test_connectFour_Horizontal() throws ColumnFullException {

        var board = new Board();

        board.set(0, 'r');
        board.set(1, 'r');
        board.set(2, 'r');
        board.set(3, 'r');

        assert(board.connectFour());

        board = new Board();

        board.set(3, 'y');
        board.set(4, 'y');
        board.set(5, 'y');
        board.set(6, 'y');

        assert(board.connectFour());

        board = new Board();

        board.set(0, 'r');
        board.set(1, 'y');
        board.set(2, 'r');
        board.set(3, 'r');

        assert(!board.connectFour());

        board = new Board();

        board.set(0, 'r');
        board.set(0, 'r');

        board.set(4, 'r');

        board.set(5, 'r');

        board.set(6, 'r');

        assert(!board.connectFour());
    }

    @Test
    void test_connectFour_Vertical() throws ColumnFullException {

        var board = new Board();

        board.set(0, 'r');
        board.set(0, 'r');
        board.set(0, 'r');
        board.set(0, 'r');

        assert(board.connectFour());

        board = new Board();

        board.set(6, 'y');
        board.set(6, 'y');
        board.set(6, 'y');
        board.set(6, 'y');

        assert(board.connectFour());

        board = new Board();

        board.set(0, 'r');
        board.set(0, 'y');
        board.set(0, 'r');
        board.set(0, 'r');

        assert(!board.connectFour());

        board = new Board();

        board.set(0, 'a');
        board.set(0, 'b');
        board.set(0, 'c');
        board.set(0, 'r');
        board.set(0, 'r');
        board.set(0, 'r');

        board.set(1, 'r');

        assert(!board.connectFour());
    }

    @Test
    void test_connectFour_Diagonal_BotLeft_To_TopRight() throws ColumnFullException {

        var board = new Board();

        board.set(0, 'r');
        board.set(1, 'y');
        board.set(1, 'r');
        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'r');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'r');

        assert(board.connectFour());

        board = new Board();

        board.set(3, 'y');

        board.set(4, 'a');
        board.set(4, 'y');

        board.set(5, 'b');
        board.set(5, 'c');
        board.set(5, 'y');

        board.set(6, 'd');
        board.set(6, 'e');
        board.set(6, 'f');
        board.set(6, 'y');

        assert(board.connectFour());

        board = new Board();

        board.set(0, 'a');
        board.set(0, 'b');
        board.set(0, 'y');

        board.set(1, 'c');
        board.set(1, 'd');
        board.set(1, 'e');
        board.set(1, 'y');

        board.set(2, 'f');
        board.set(2, 'g');
        board.set(2, 'h');
        board.set(2, 'i');
        board.set(2, 'y');

        board.set(3, 'j');
        board.set(3, 'k');
        board.set(3, 'l');
        board.set(3, 'm');
        board.set(3, 'n');
        board.set(3, 'y');

        assert(board.connectFour());

        board = new Board();

        board.set(3, 'a');
        board.set(3, 'b');
        board.set(3, 'y');

        board.set(4, 'c');
        board.set(4, 'd');
        board.set(4, 'e');
        board.set(4, 'y');

        board.set(5, 'f');
        board.set(5, 'g');
        board.set(5, 'h');
        board.set(5, 'i');
        board.set(5, 'y');

        board.set(6, 'j');
        board.set(6, 'k');
        board.set(6, 'l');
        board.set(6, 'm');
        board.set(6, 'n');
        board.set(6, 'y');

        assert(board.connectFour());
    }

    @Test
    void test_connectFour_Diagonal_BotRight_To_TopLeft() throws ColumnFullException {

        var board = new Board();

        board.set(3, 'r');

        board.set(2, 'a');
        board.set(2, 'r');

        board.set(1, 'b');
        board.set(1, 'c');
        board.set(1, 'r');

        board.set(0, 'd');
        board.set(0, 'e');
        board.set(0, 'f');
        board.set(0, 'r');

        assert(board.connectFour());

        board = new Board();

        board.set(3, 'r');

        board.set(4, 'a');
        board.set(4, 'r');

        board.set(5, 'b');
        board.set(5, 'c');
        board.set(5, 'r');

        board.set(6, 'd');
        board.set(6, 'e');
        board.set(6, 'f');
        board.set(6, 'r');

        assert(board.connectFour());

        board = new Board();

        board.set(3, 'a');
        board.set(3, 'b');
        board.set(3, 'r');

        board.set(2, 'c');
        board.set(2, 'd');
        board.set(2, 'e');
        board.set(2, 'r');

        board.set(1, 'f');
        board.set(1, 'g');
        board.set(1, 'h');
        board.set(1, 'i');
        board.set(1, 'r');

        board.set(0, 'j');
        board.set(0, 'k');
        board.set(0, 'l');
        board.set(0, 'm');
        board.set(0, 'n');
        board.set(0, 'r');

        assert(board.connectFour());

        board = new Board();

        board.set(6, 'a');
        board.set(6, 'b');
        board.set(6, 'r');

        board.set(5, 'c');
        board.set(5, 'd');
        board.set(5, 'e');
        board.set(5, 'r');

        board.set(4, 'f');
        board.set(4, 'g');
        board.set(4, 'h');
        board.set(4, 'i');
        board.set(4, 'r');

        board.set(3, 'j');
        board.set(3, 'k');
        board.set(3, 'l');
        board.set(3, 'm');
        board.set(3, 'n');
        board.set(3, 'r');

        assert(board.connectFour());
    }

    @Test
    void test_indexedConnectFour_Horizontal() throws ColumnFullException {

        var board = new Board();

        board.set(0, 'r');
        board.set(1, 'r');
        board.set(2, 'r');
        board.set(3, 'r');

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        board.set(3, 'y');
        board.set(4, 'y');
        board.set(5, 'y');
        board.set(6, 'y');

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));

        board = new Board();

        board.set(0, 'r');
        board.set(1, 'y');
        board.set(2, 'r');
        board.set(3, 'r');

        assert(!board.connectFour(0));
        assert(!board.connectFour(1));
        assert(!board.connectFour(2));
        assert(!board.connectFour(3));

        board = new Board();

        board.set(0, 'r');
        board.set(0, 'r');

        board.set(4, 'r');

        board.set(5, 'r');

        board.set(6, 'r');

        assert(!board.connectFour(6));
    }

    @Test
    void test_indexedConnectFour_Vertical() throws ColumnFullException {

        var board = new Board();

        board.set(0, 'r');
        board.set(0, 'r');
        board.set(0, 'r');
        board.set(0, 'r');

        assert(board.connectFour(0));

        board = new Board();

        board.set(6, 'y');
        board.set(6, 'y');
        board.set(6, 'y');
        board.set(6, 'y');

        assert(board.connectFour(6));

        board = new Board();

        board.set(0, 'r');
        board.set(0, 'y');
        board.set(0, 'r');
        board.set(0, 'r');

        assert(!board.connectFour(0));

        board = new Board();

        board.set(0, 'a');
        board.set(0, 'b');
        board.set(0, 'c');
        board.set(0, 'r');
        board.set(0, 'r');
        board.set(0, 'r');

        board.set(1, 'r');

        assert(!board.connectFour(0));
    }

    @Test
    void test_indexedConnectFour_Diagonal_BotLeft_To_TopRight() throws ColumnFullException {

        var board = new Board();

        board.set(0, 'r');
        board.set(1, 'y');
        board.set(1, 'r');
        board.set(2, 'y');
        board.set(2, 'y');
        board.set(2, 'r');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'y');
        board.set(3, 'r');

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        board.set(3, 'y');

        board.set(4, 'a');
        board.set(4, 'y');

        board.set(5, 'b');
        board.set(5, 'c');
        board.set(5, 'y');

        board.set(6, 'd');
        board.set(6, 'e');
        board.set(6, 'f');
        board.set(6, 'y');

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));

        board = new Board();

        board.set(0, 'a');
        board.set(0, 'b');
        board.set(0, 'y');

        board.set(1, 'c');
        board.set(1, 'd');
        board.set(1, 'e');
        board.set(1, 'y');

        board.set(2, 'f');
        board.set(2, 'g');
        board.set(2, 'h');
        board.set(2, 'i');
        board.set(2, 'y');

        board.set(3, 'j');
        board.set(3, 'k');
        board.set(3, 'l');
        board.set(3, 'm');
        board.set(3, 'n');
        board.set(3, 'y');

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        board.set(3, 'a');
        board.set(3, 'b');
        board.set(3, 'y');

        board.set(4, 'c');
        board.set(4, 'd');
        board.set(4, 'e');
        board.set(4, 'y');

        board.set(5, 'f');
        board.set(5, 'g');
        board.set(5, 'h');
        board.set(5, 'i');
        board.set(5, 'y');

        board.set(6, 'j');
        board.set(6, 'k');
        board.set(6, 'l');
        board.set(6, 'm');
        board.set(6, 'n');
        board.set(6, 'y');

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));
    }

    @Test
    void test_indexedConnectFour_Diagonal_BotRight_To_TopLeft() throws ColumnFullException {

        var board = new Board();

        board.set(3, 'r');

        board.set(2, 'a');
        board.set(2, 'r');

        board.set(1, 'b');
        board.set(1, 'c');
        board.set(1, 'r');

        board.set(0, 'd');
        board.set(0, 'e');
        board.set(0, 'f');
        board.set(0, 'r');

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        board.set(3, 'r');

        board.set(4, 'a');
        board.set(4, 'r');

        board.set(5, 'b');
        board.set(5, 'c');
        board.set(5, 'r');

        board.set(6, 'd');
        board.set(6, 'e');
        board.set(6, 'f');
        board.set(6, 'r');

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));

        board = new Board();

        board.set(3, 'a');
        board.set(3, 'b');
        board.set(3, 'r');

        board.set(2, 'c');
        board.set(2, 'd');
        board.set(2, 'e');
        board.set(2, 'r');

        board.set(1, 'f');
        board.set(1, 'g');
        board.set(1, 'h');
        board.set(1, 'i');
        board.set(1, 'r');

        board.set(0, 'j');
        board.set(0, 'k');
        board.set(0, 'l');
        board.set(0, 'm');
        board.set(0, 'n');
        board.set(0, 'r');

        assert(board.connectFour(0));
        assert(board.connectFour(1));
        assert(board.connectFour(2));
        assert(board.connectFour(3));

        board = new Board();

        board.set(6, 'a');
        board.set(6, 'b');
        board.set(6, 'r');

        board.set(5, 'c');
        board.set(5, 'd');
        board.set(5, 'e');
        board.set(5, 'r');

        board.set(4, 'f');
        board.set(4, 'g');
        board.set(4, 'h');
        board.set(4, 'i');
        board.set(4, 'r');

        board.set(3, 'j');
        board.set(3, 'k');
        board.set(3, 'l');
        board.set(3, 'm');
        board.set(3, 'n');
        board.set(3, 'r');

        assert(board.connectFour(3));
        assert(board.connectFour(4));
        assert(board.connectFour(5));
        assert(board.connectFour(6));
    }

}