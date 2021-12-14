
// ---------------------------------------------------------------------------------------
// The SlotReader abstract class is used in the brute-force search of winning and
// defensive plays. The methods getNextSlotRow and getNextSlotColumn can be overridden to
// define the direction in which the slots on the board are read.
// ---------------------------------------------------------------------------------------
public abstract class SlotReader {

    SlotReader(Colour colour) {
        this.colour = colour;
    }

    // -----------------------------------------------------------------------------------
    // readFourSlots uses a state machine to read four slots in a direction determined
    // by the inheriting subclass. It finds moves that finish or prevent a connect4.
    // -----------------------------------------------------------------------------------
    public Move readFourSlots(Board board, int row, int column) {

        State state = State.NONE;
        Colour firstCounter = null;

        int emptySlotRow = row;
        int emptySlotColumn = column;

        for (int i = 0; i < 4; ++i) {

            final int nextSlotRow = getNextSlotRow(row, i);
            final int nextSlotColumn = getNextSlotColumn(column, i);

            Colour colour = board.get(nextSlotColumn, nextSlotRow);

            switch (state) {
                case NONE:
                    if (colour == null) {
                        state = State.EMPTY;
                    }
                    else {
                        firstCounter = colour;
                        state = State.ONE;
                    }
                    break;

                case EMPTY:
                    if (colour != null) {
                        firstCounter = colour;
                        state = State.EMPTY_ONE;
                    }
                    else {
                        return null;
                    }
                    break;

                case EMPTY_ONE:
                    if (colour == firstCounter) {
                        state = State.EMPTY_TWO;
                    }
                    else {
                        return null;
                    }
                    break;

                case EMPTY_TWO:
                case ONE_EMPTY_ONE:
                case TWO_EMPTY:
                    if (colour == firstCounter
                        && emptySlotRow == board.getNumCountersInColumn(emptySlotColumn)) {
                        return categoriseMove(firstCounter, emptySlotColumn);
                    }
                    return null;

                case ONE:
                    if (colour == null) {
                        emptySlotRow = nextSlotRow;
                        emptySlotColumn = nextSlotColumn;

                        state = State.ONE_EMPTY;
                    }
                    else if (colour == firstCounter) {
                        state = State.TWO;
                    }
                    else {
                        return null;
                    }
                    break;

                case ONE_EMPTY:
                    if (colour == firstCounter) {
                        state = State.ONE_EMPTY_ONE;
                    }
                    else {
                        return null;
                    }
                    break;

                case TWO:
                    if (colour == firstCounter) {
                        state = State.THREE;
                    }
                    else if (colour == null) {
                        emptySlotRow = nextSlotRow;
                        emptySlotColumn = nextSlotColumn;

                        state = State.TWO_EMPTY;
                    }
                    else {
                        return null;
                    }

                    break;

                case THREE:
                    if (colour == null
                        && nextSlotRow == board.getNumCountersInColumn(nextSlotColumn)) {
                        return categoriseMove(firstCounter, nextSlotColumn);
                    }

                    return null;
            }
        }

        return null;
    }

    private Move categoriseMove(Colour counter, int column) {

        if (colour == counter) {
            return Move.makeWinner(column);
        }
        else {
            return Move.makePreventLoss(column);
        }
    }

    // -----------------------------------------------------------------------------------
    // This method is overridden to define how the state machine moves in the y-axis for
    // each step.
    // -----------------------------------------------------------------------------------
    protected abstract int getNextSlotRow(int row, int offset);

    // -----------------------------------------------------------------------------------
    // This method is overridden to define how the state machine moves in the x-axis for
    // each step.
    // -----------------------------------------------------------------------------------
    protected abstract int getNextSlotColumn(int column, int offset);

    private final Colour colour;

    // -----------------------------------------------------------------------------------
    // These states represent what the machine has read so far. For example, if the
    // machine reads an empty slot and then a filled slot, it will have the EMPTY_ONE
    // slot.
    // -----------------------------------------------------------------------------------
    private enum State {
        NONE,
        EMPTY,
        EMPTY_ONE,
        EMPTY_TWO,
        ONE,
        ONE_EMPTY,
        ONE_EMPTY_ONE,
        TWO,
        TWO_EMPTY,
        THREE,
    }
}
