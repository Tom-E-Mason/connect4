
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


        for (int i = 0; i < board[0].length; ++i) {
            sb.append(String.format("  %d ", i + 1));
        }

        sb.setCharAt(sb.length() - 1, '\n');

        return sb.toString();
    }

    public char get(int x, int y) {
        return board[board.length - 1 - y][x];
    }

    public void set(int x, int y, char value) {
        board[board.length - 1 - y][x] = value;
    }

    char[][] board;
}
