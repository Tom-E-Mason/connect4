
// ---------------------------------------------------------------------------------------
// Player is an abstract class that allows the main game loop to be kept simple. The
// alternative would be using a boolean flag or something similar to switch between the
// logic for the human and computer players.
// ---------------------------------------------------------------------------------------
public abstract class Player {

    public Player(Colour colour) {
        this.colour = colour;
    }

    protected abstract int getMove(Board board);

    protected Colour getColour() {
        return colour;
    }

    private final Colour colour;
}
