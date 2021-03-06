
// ---------------------------------------------------------------------------------------
// ConnectFour contains the main game loop. Each iteration of the loop the currentPlayer
// is swapped, between the human and the computer. The different logic required for each
// player is handled by runtime polymorphism.
// ---------------------------------------------------------------------------------------
public class ConnectFour {

    public void play() {

        printInstructions();
        System.out.println();

        while (true) {

            System.out.println(board);

            if (board.isFull()) {
                System.out.print("Stalemate!");
                break;
            }

            final int move = currentPlayer.getMove(board);
            board.set(move, currentPlayer.getColour());

            if (board.connectFour(move)) {

                System.out.print(board);

                if (currentPlayer == human) {
                    System.out.println("You won!");
                }
                else {
                    System.out.println("You lost!");
                }

                break;
            }

            swapPlayers();
        }
    }

    public static void printInstructions() {

        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players, Red and Yellow");
        System.out.println("Your colour is Red, the computer's colour is Yellow");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
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

    private final HumanPlayer human = new HumanPlayer(Colour.RED);
    private final ComputerPlayer computer = new ComputerPlayer(Colour.YELLOW);
    private Player currentPlayer = human;
}
