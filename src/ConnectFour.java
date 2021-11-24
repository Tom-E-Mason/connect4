
public class ConnectFour {

    public static void main(String[] args) {
        printInstructions();


    }

    public static void printInstructions() {

        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players red and yellow");
        System.out.println("Player 1 is Red, Player 2 is Yellow");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
    }

    Board board = new Board();
}
