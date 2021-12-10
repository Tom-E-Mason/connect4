import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @Test
    void testGetMove_HorizontalWinningMove() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // | y | y | y |   |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, 'y');
        board.set(1, 'y');
        board.set(2, 'y');

        assertEquals(3, computer.getMove(board));
    }

    @Test
    void testGetMove_PreventLoss() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   |   | y | y | y |
        //   0   1   2   3   4   5   6

        board.set(4, 'r');
        board.set(5, 'r');
        board.set(6, 'r');

        assertEquals(3, computer.getMove(board));
    }

    @Test
    void testGetMove_HeightAware_EmptyThree() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   | y | y | y |   |   |
        // |   |   | r | y | y | r |   |
        //   0   1   2   3   4   5   6

        board.set(2, 'r');
        board.set(3, 'y');
        board.set(4, 'y');
        board.set(5, 'r');

        board.set(2, 'y');
        board.set(3, 'y');
        board.set(4, 'y');

        assertEquals(5, computer.getMove(board));
    }

    @Test
    void testGetMove_HeightAware_OneEmptyTwo() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   | y |   | y | y | y |   |
        // |   | r |   | y | r | r | r |
        //   0   1   2   3   4   5   6

        board.set(1, 'r');
        board.set(3, 'y');
        board.set(4, 'y');
        board.set(5, 'r');

        board.set(1, 'y');
        board.set(3, 'y');
        board.set(4, 'y');
        board.set(5, 'y');
        board.set(6, 'r');

        assertEquals(6, computer.getMove(board));
    }

    @Test
    void testGetMove_HeightAware_TwoEmptyOne() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // | y | y |   | y | y | y |   |
        // | r | y |   | y | r | y | r |
        //   0   1   2   3   4   5   6

        board.set(0, 'r');
        board.set(1, 'y');
        board.set(3, 'y');
        board.set(4, 'r');
        board.set(5, 'y');
        board.set(6, 'r');

        board.set(0, 'y');
        board.set(1, 'y');
        board.set(3, 'y');
        board.set(4, 'y');
        board.set(5, 'y');

        assertEquals(6, computer.getMove(board));
    }

    @Test
    void testGetMove_HeightAware_ThreeEmpty() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   | y | y | y |   |   |   |
        // |   | y | r | y | r |   |   |
        //   0   1   2   3   4   5   6

        board.set(1, 'y');
        board.set(2, 'r');
        board.set(3, 'y');
        board.set(4, 'r');

        board.set(1, 'y');
        board.set(2, 'y');
        board.set(3, 'y');

        assertEquals(4, computer.getMove(board));
    }

    @Test
    void testGetMove_ChooseWinnerOverPreventLoss() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   | y | y | y |   |
        // |   |   | y | r | r | r |   |
        //   0   1   2   3   4   5   6

        board.set(2, 'y');
        board.set(3, 'r');
        board.set(4, 'r');
        board.set(5, 'r');

        board.set(3, 'y');
        board.set(4, 'y');
        board.set(5, 'y');

        assertEquals(2, computer.getMove(board));
    }

    @Test
    void testGetMove_VerticalWinningMove() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   |   |   |   |   |
        // | y |   |   |   |   |   |   |
        // | y |   |   |   |   |   |   |
        // | y |   |   |   |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, 'y');
        board.set(0, 'y');
        board.set(0, 'y');

        assertEquals(0, computer.getMove(board));
    }

    @Test
    void testGetMove_DiagonalBotLeftTopRightThreeEmpty() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   | y | r | y |   |   |   |
        // | y | r | y | r |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, 'y');
        board.set(1, 'r');
        board.set(2, 'y');
        board.set(3, 'r');

        board.set(1, 'y');
        board.set(2, 'r');
        board.set(3, 'y');

        board.set(2, 'y');
        board.set(3, 'y');

        assertEquals(3, computer.getMove(board));
    }

    @Test
    void testGetMove_DiagonalBotLeftTopRightEmptyThree() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   | y |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   | y | r | r |   |   |   |
        // |   | r | y | r |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(1, 'r');
        board.set(2, 'y');
        board.set(3, 'r');

        board.set(1, 'y');
        board.set(2, 'r');
        board.set(3, 'r');

        board.set(2, 'y');
        board.set(3, 'y');

        board.set(3, 'y');

        assertEquals(0, computer.getMove(board));
    }

    @Test
    void testGetMove_DiagonalBotLeftTopRightOneEmptyTwo() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   | y |   |   |   |
        // |   |   | y | y |   |   |   |
        // |   |   | r | r |   |   |   |
        // | y | r | y | r |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, 'y');
        board.set(1, 'r');
        board.set(2, 'y');
        board.set(3, 'r');

        board.set(2, 'r');
        board.set(3, 'r');

        board.set(2, 'y');
        board.set(3, 'y');

        board.set(3, 'y');

        assertEquals(1, computer.getMove(board));
    }

    @Test
    void testGetMove_DiagonalBotLeftTopRightTwoEmptyOne() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   | y |   |   |   |
        // |   |   |   | y |   |   |   |
        // |   | y | r | r |   |   |   |
        // | y | r | y | r |   |   |   |
        //   0   1   2   3   4   5   6

        board.set(0, 'y');
        board.set(1, 'r');
        board.set(2, 'y');
        board.set(3, 'r');

        board.set(1, 'y');
        board.set(2, 'r');
        board.set(3, 'r');

        board.set(3, 'y');

        board.set(3, 'y');

        assertEquals(2, computer.getMove(board));
    }

    @Test
    void testGetMove_DiagonalBotRightTopLeftThreeEmpty() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   |   |   |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   |   | y | r | y |   |
        // |   |   |   | r | y | y | y |
        //   0   1   2   3   4   5   6

        board.set(3, 'r');
        board.set(4, 'y');
        board.set(5, 'r');
        board.set(6, 'y');

        board.set(3, 'y');
        board.set(4, 'r');
        board.set(5, 'y');

        board.set(3, 'y');
        board.set(4, 'y');

        assertEquals(3, computer.getMove(board));
    }

    @Test
    void testGetMove_DiagonalBotRightTopLeftEmptyThree() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   | y |   |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   |   | r | r | y |   |
        // |   |   |   | r | y | r |   |
        //   0   1   2   3   4   5   6

        board.set(3, 'r');
        board.set(4, 'y');
        board.set(5, 'r');

        board.set(3, 'r');
        board.set(4, 'r');
        board.set(5, 'y');

        board.set(3, 'y');
        board.set(4, 'y');

        board.set(3, 'y');

        assertEquals(6, computer.getMove(board));
    }

    @Test
    void testGetMove_DiagonalBotRightTopLeftOneEmptyTwo() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   | y |   |   |   |
        // |   |   |   | y | y |   |   |
        // |   |   |   | r | r |   |   |
        // |   |   |   | r | y | r | y |
        //   0   1   2   3   4   5   6

        board.set(3, 'r');
        board.set(4, 'y');
        board.set(5, 'r');
        board.set(6, 'y');

        board.set(3, 'r');
        board.set(4, 'r');

        board.set(3, 'y');
        board.set(4, 'y');

        board.set(3, 'y');

        assertEquals(5, computer.getMove(board));
    }

    @Test
    void testGetMove_DiagonalBotRightTopLeftTwoEmptyOne() throws ColumnFullException {

        var computer = new ComputerPlayer('y');

        var board = new Board();

        // |   |   |   | y |   |   |   |
        // |   |   |   | y |   |   |   |
        // |   |   |   | r | r | y |   |
        // |   |   |   | r | y | r | y |
        //   0   1   2   3   4   5   6

        board.set(3, 'r');
        board.set(4, 'y');
        board.set(5, 'r');
        board.set(6, 'y');

        board.set(3, 'r');
        board.set(4, 'r');
        board.set(5, 'y');

        board.set(3, 'y');

        board.set(3, 'y');

        assertEquals(4, computer.getMove(board));
    }

}