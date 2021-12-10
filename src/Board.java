
public class Board {

    public Board() {
        board = new char[6][7];
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int row = numRows() - 1; row >= 0; --row) {

            for (int column = 0; column < numColumns(); ++column) {

                char centre = ' ';
                if (board[row][column] != BLANK_SLOT) {
                    centre = board[row][column];
                }

                sb.append(String.format("| %c ", centre));
            }

            sb.append("|\n");
        }

        for (int i = 0; i < numColumns(); ++i) {
            sb.append(String.format("  %d ", i + 1));
        }

        sb.setCharAt(sb.length() - 1, '\n');

        return sb.toString();
    }

    public boolean isValidMove(int row, int column) {
        return row == 0 || get(column, row - 1) != BLANK_SLOT;
    }

    public char get(int x, int y) {
        return board[y][x];
    }

    public int set(int column, char value) throws ColumnFullException {

        for (int i = 0; i < numRows(); ++i) {
            if (board[i][column] == '\0') {
                board[i][column] = value;
                return i;
            }
        }

        throw new ColumnFullException();
    }

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

        for (int row = numRows() - 1; row >= 0; --row) {
            if (board[row][column] != BLANK_SLOT) {
                return row;
            }
        }

        return -1; // replace with exception or assertion?
    }

    private boolean connectFourHorizontal(int counterRow, int counterColumn) {

        int length = 1;
        final char counter = board[counterRow][counterColumn];

        for (int column = counterColumn - 1; column >= 0; --column) {

            if (board[counterRow][column] == counter) {
                ++length;
            }
            else {
                break;
            }
        }

        for (int column = counterColumn + 1; column < numColumns(); ++column) {

            if (board[counterRow][column] == counter) {
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
        final char counter = board[counterRow][counterColumn];

        for (int row = counterRow + 1; row < numRows(); ++row) {

            if (board[row][counterColumn] == counter) {
                ++length;
            }
            else {
                break;
            }
        }

        for (int row = counterRow - 1; row >= 0; --row) {

            if (board[row][counterColumn] == counter) {
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
        final char counter = board[counterRow][counterColumn];

        int row = counterRow + 1;
        int column = counterColumn + 1;

        while (row < numRows() && column < numColumns()) {

            if (board[row][column] == counter) {
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

            if (board[row][column] == counter) {
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
        final char counter = board[counterRow][counterColumn];

        int row = counterRow + 1;
        int column = counterColumn - 1;

        while (row < numRows() && column >= 0) {

            if (board[row][column] == counter) {
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

        while (row >= 0 && column < numColumns()) {

            if (board[row][column] == counter) {
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

    public int numRows() {
        return board.length;
    }

    public int numColumns() {
        return board[0].length;
    }

    char[][] board;

    public static final char BLANK_SLOT = '\0';
}

