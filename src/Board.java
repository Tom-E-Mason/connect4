
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

    public void set(int column, char value) throws ColumnFullException {

        for (int i = numRows() - 1; i > - 1; --i) {
            if (board[i][column] == '\0') {
                board[i][column] = value;
                return;
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

    private int numRows() {
        return board.length;
    }

    private int numColumns() {
        return board[0].length;
    }

    char[][] board;

    private static final char BLANK_SLOT = '\0';
}

