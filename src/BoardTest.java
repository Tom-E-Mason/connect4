
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

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

        board.set(0, 0, 'r'); board.set(6, 0, 'y');
        board.set(0, 1, 'y'); board.set(6, 1, 'r');
        board.set(0, 2, 'r'); board.set(6, 2, 'y');
        board.set(0, 3, 'y'); board.set(6, 3, 'r');
        board.set(0, 4, 'r'); board.set(6, 4, 'y');
        board.set(0, 5, 'y'); board.set(6, 5, 'r');

        assertEquals(boardWithColumns, board.toString());
    }

    @Test
    void testToString_BoardWithRows() {

        Board board = new Board();

        String boardWithRows = """
                | y | y | y | r | r | r | r |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                |   |   |   |   |   |   |   |
                | r | r | r | y | y | y | y |
                  1   2   3   4   5   6   7
                """;

        board.set(0, 0, 'r'); board.set(0, 5, 'y');
        board.set(1, 0, 'r'); board.set(1, 5, 'y');
        board.set(2, 0, 'r'); board.set(2, 5, 'y');
        board.set(3, 0, 'y'); board.set(3, 5, 'r');
        board.set(4, 0, 'y'); board.set(4, 5, 'r');
        board.set(5, 0, 'y'); board.set(5, 5, 'r');
        board.set(6, 0, 'y'); board.set(6, 5, 'r');

        assertEquals(boardWithRows, board.toString());
    }
}