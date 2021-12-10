
// ---------------------------------------------------------------------------------------
// Player is an abstract class that allows the main game loop to be kept simple. The
// alternative would be using a boolean flag or something similar to switch between the
// logic for the human and computer players.
// ---------------------------------------------------------------------------------------
public abstract class Player {

    public Player(SlotValue slotValue) {
        this.slotValue = slotValue;
    }

    protected abstract int getMove(Board board);

    protected SlotValue getColour() {
        return slotValue;
    }

    private final SlotValue slotValue;
}
