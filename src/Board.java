// ---------------------------------------------------------------------------------------
// Representing the board as a class works well for a number of reasons.
//     - Counters can only be dropped in, preventing any errors that could have occurred
//       accessing a 2D array directly.
//     - It is a very natural idea meaning the API is expressive and easy to understand
//       e.g. print(board) does exactly what you would expect.
//     - The board can be tested based on the most recent move to see if a player has won.
// ---------------------------------------------------------------------------------------
public class Board {

    public Board() {
        board = new TwoDimensionalArray(6, 7);
    }

    public Board(Board other) {
        board = new TwoDimensionalArray(other.board);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int row = getNumRows() - 1; row >= 0; --row) {

            for (int column = 0; column < getNumColumns(); ++column) {

                char centre = ' ';
                if (board.get(row, column) != null) {
                    centre = Colours.toCharacter(board.get(row, column));
                }

                sb.append(String.format("| %c ", centre));
            }

            sb.append("|\n");
        }

        for (int i = 0; i < getNumColumns(); ++i) {
            sb.append(String.format("  %d ", i + 1));
        }

        sb.setCharAt(sb.length() - 1, '\n');

        return sb.toString();
    }

    public SlotValue get(int x, int y) {
        return board.get(y, x);
    }

    public int set(int column, SlotValue slotValue) {

        assert(slotValue != null);

        for (int i = 0; i < getNumRows(); ++i) {
            if (board.get(i, column) == null) {
                board.set(i, column, slotValue);
                return i;
            }
        }

        throw new ColumnFullException();
    }

    // -----------------------------------------------------------------------------------
    // The four checks for a win are split into four private methods, to make it clearer
    // what connectFour does. The checks only consider possible winning sequences that
    // are related to the most recent move, as there is no point checking the rest of the
    // board.
    // -----------------------------------------------------------------------------------
    public boolean connectFour(int column) {

        final int row = getTopCounterRow(column);

        if (connectFourHorizontal(row, column)) {
            return true;
        }
        else if (connectFourVertical(row, column)) {
            return true;
        }
        else if (connectFourDiagonalBotLeftTopRight(row, column)) {
            return true;
        }
        else {
            return connectFourDiagonalBotRightTopLeft(row, column);
        }
    }

    private int getTopCounterRow(int column) {

        for (int row = getNumRows() - 1; row >= 0; --row) {
            if (board.get(row, column) != null) {
                return row;
            }
        }

        throw new ColumnEmptyException();
    }

    private boolean connectFourHorizontal(int counterRow, int counterColumn) {

        int length = 1;
        final SlotValue counter = board.get(counterRow, counterColumn);

        for (int column = counterColumn - 1; column >= 0; --column) {

            if (board.get(counterRow, column) == counter) {
                ++length;
            }
            else {
                break;
            }
        }

        for (int column = counterColumn + 1; column < getNumColumns(); ++column) {

            if (board.get(counterRow, column) == counter) {
                ++length;
            }
            else {
                break;
            }
        }

        return length >= 4;
    }

    private boolean connectFourVertical(int counterRow, int counterColumn) {

        int length = 1;
        final SlotValue counter = board.get(counterRow, counterColumn);

        for (int row = counterRow + 1; row < getNumRows(); ++row) {

            if (board.get(row, counterColumn) == counter) {
                ++length;
            }
            else {
                break;
            }
        }

        for (int row = counterRow - 1; row >= 0; --row) {

            if (board.get(row, counterColumn) == counter) {
                ++length;
            }
            else {
                break;
            }
        }

        return length >= 4;
    }

    private boolean connectFourDiagonalBotLeftTopRight(int counterRow, int counterColumn) {

        int length = 1;
        final SlotValue counter = board.get(counterRow, counterColumn);

        int row = counterRow + 1;
        int column = counterColumn + 1;

        while (row < getNumRows() && column < getNumColumns()) {

            if (board.get(row, column) == counter) {
                ++length;
            }
            else {
                break;
            }

            ++row;
            ++column;
        }

        row = counterRow - 1;
        column = counterColumn - 1;

        while (row >= 0 && column >= 0) {

            if (board.get(row, column) == counter) {
                ++length;
            }
            else {
                break;
            }

            --row;
            --column;
        }

        return length >= 4;
    }

    private boolean connectFourDiagonalBotRightTopLeft(int counterRow, int counterColumn) {

        int length = 1;
        final SlotValue counter = board.get(counterRow, counterColumn);

        int row = counterRow + 1;
        int column = counterColumn - 1;

        while (row < getNumRows() && column >= 0) {

            if (board.get(row, column) == counter) {
                ++length;
            }
            else {
                break;
            }

            ++row;
            --column;
        }

        row = counterRow - 1;
        column = counterColumn + 1;

        while (row >= 0 && column < getNumColumns()) {

            if (board.get(row, column) == counter) {
                ++length;
            }
            else {
                break;
            }

            --row;
            ++column;
        }

        return length >= 4;
    }

    public boolean isFull() {

        for (int i = 0; i < getNumColumns(); ++i) {
            if (getNumCountersInColumn(i) < getNumRows()) {
                return false;
            }
        }

        return true;
    }

    public int getNumRows() {
        return board.getNumRows();
    }

    public int getNumColumns() {
        return board.getNumColumns();
    }

    public int getNumCountersInColumn(int column) {

        int height = 0;

        while (height < getNumRows() && board.get(height, column) != null) {
            ++height;
        }

        return height;
    }

    TwoDimensionalArray board;
}
