
public abstract class Player {

    public Player (char colour) {
        this.colour = colour;
    }

    protected abstract int getMove(Board board);

    protected char getColour() {
        return colour;
    }

    private final char colour;
}
