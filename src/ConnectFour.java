
public class ConnectFour {

    public void play() {
        printInstructions();
        System.out.println();

        while (true) {
            System.out.println(board);

            int move = makeMove();

            if (board.connectFour(move)) {
                break;
            }

            swapPlayers();
        }

        System.out.print(board);

        if (currentPlayer == human) {
            System.out.println("You won!");
        }
        else {
            System.out.println("You lost!");
        }
    }

    public static void printInstructions() {

        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players red and yellow");
        System.out.println("Player 1 is Red, Player 2 is Yellow");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
    }

    private int makeMove() {
        int move = currentPlayer.getMove();

        while (true) {
            try {
                board.set(move, currentPlayer.getColour());
                break;
            }
            catch (ColumnFullException e) {
                System.out.println("That column is full, try again");
            }
            //catch (BoardFullException e) {
            //    break;
            //}
        }

        return move;
    }

    private void swapPlayers() {
        if (currentPlayer == human) {
            currentPlayer = computer;
        }
        else {
            currentPlayer = human;
        }
    }

    private final Board board = new Board();

    private final HumanPlayer human = new HumanPlayer('r');
    private final ComputerPlayer computer = new ComputerPlayer('y');
    private Player currentPlayer = human;
}
