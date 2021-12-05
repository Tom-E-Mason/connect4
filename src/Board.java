
public class Board {

    public Board() {
        board = new char[6][7];
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (char[] row : board) {
            for (char position : row) {
                char centre = ' ';
                if (position == 'r') {
                    centre = 'r';
                }
                else if (position == 'y') {
                    centre = 'y';
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

    public char get(int x, int y) {
        return board[numRows() - 1 - y][x];
    }

    public int set(int column, char value) throws ColumnFullException {

        for (int i = numRows() - 1; i > - 1; --i) {
            if (board[i][column] == '\0') {
                board[i][column] = value;
                return numRows() - 1 - i;
            }
        }

        throw new ColumnFullException();
    }

    public boolean connectFour() {

        char c;
        int length;

        // horizontal
        for (int row = numRows() - 1; row > -1; --row) {

            c = BLANK_SLOT;
            length = 0;

            for (int column = 0; column < numColumns(); ++column) {

                if (c != board[row][column]) {
                    c = board[row][column];

                    length = 1;
                }
                else if (c != BLANK_SLOT && ++length == 4) {
                    return true;
                }
            }
        }

        // vertical
        for (int column = 0; column < numColumns(); ++column) {

            c = BLANK_SLOT;
            length = 0;

            for (int row = numRows() - 1; row > -1; --row) {

                if (c != board[row][column]) {
                    c = board[row][column];

                    length = 1;
                }
                else if (c != BLANK_SLOT && ++length == 4) {
                    return true;
                }
            }
        }

        // diagonal bottom left to top right
        for (int row = numRows() - 1; row > 2; --row) {

            for (int column = 0; column < numColumns() - 3; ++column) {

                c = board[row][column];

                if (c != BLANK_SLOT
                    && c == board[row - 1][column + 1]
                    && c == board[row - 2][column + 2]
                    && c == board[row - 3][column + 3]) {
                    return true;
                }
            }
        }

        // diagonal bottom right to top left
        for (int row = numRows() - 1; row > 2; --row) {

            for (int column = numColumns() - 1; column > 2; --column) {

                c = board[row][column];

                if (c != BLANK_SLOT
                    && c == board[row - 1][column - 1]
                    && c == board[row - 2][column - 2]
                    && c == board[row - 3][column - 3]) {
                    return true;
                }
            }
        }

        return false;
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

        for (int row = 0; row < numRows(); ++row) {
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

        int row = counterRow - 1;
        int column = counterColumn + 1;

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

        row = counterRow + 1;
        column = counterColumn - 1;

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

        return length >= 4;
    }

    private boolean connectFourDiagonalBotRightTopLeft(int counterRow, int counterColumn) {

        int length = 1;
        final char counter = board[counterRow][counterColumn];

        int row = counterRow - 1;
        int column = counterColumn - 1;

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

        row = counterRow + 1;
        column = counterColumn + 1;

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

        return length >= 4;
    }

    private int numRows() {
        return board.length;
    }

    private int numColumns() {
        return board[0].length;
    }

    char[][] board;

    private static final char BLANK_SLOT = '\0';
}

