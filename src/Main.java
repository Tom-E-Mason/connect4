
// -----------------------------------------------------------------------------
// This is the entry point of the program. All the game logic is handled by the
// ConnectFour class.
// -----------------------------------------------------------------------------
public class Main {

    public static void main(String[] args) {
        var connectFour = new ConnectFour();

        try {
            connectFour.play();
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
