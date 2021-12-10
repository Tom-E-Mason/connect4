
// ---------------------------------------------------------------------------------------
// The SlotReader abstract class is used in the brute-force search of winning and
// defensive plays. The methods getNextSlotRow and getNextSlotColumn can be overridden to
// define the direction in which the slots on the board are read.
// ---------------------------------------------------------------------------------------
public abstract class SlotReader {

    SlotReader(SlotValue slotValue) {
        this.slotValue = slotValue;
    }

    public Move readFourSlots(Board board, int row, int column) {

        State state = State.NONE;
        SlotValue firstCounter = null;

        int emptySlotRow = row;
        int emptySlotColumn = column;

        for (int i = 0; i < 4; ++i) {

            final int nextSlotRow = getNextSlotRow(row, i);
            final int nextSlotColumn = getNextSlotColumn(column, i);

            SlotValue slotValue = board.get(nextSlotColumn, nextSlotRow);

            switch (state) {

                case NONE -> {

                    if (slotValue == null) {
                        state = State.EMPTY;
                    }
                    else {
                        firstCounter = slotValue;
                        state = State.ONE;
                    }
                }

                case EMPTY -> {

                    if (slotValue != null) {
                        firstCounter = slotValue;
                        state = State.EMPTY_ONE;
                    }
                    else {
                        return null;
                    }
                }

                case EMPTY_ONE -> {

                    if (slotValue == firstCounter) {
                        state = State.EMPTY_TWO;
                    }
                    else {
                        return null;
                    }
                }

                case EMPTY_TWO, ONE_EMPTY_ONE, TWO_EMPTY -> {

                    if (slotValue == firstCounter && emptySlotRow == board.getNumCountersInColumn(emptySlotColumn)) {
                        return categoriseMove(firstCounter, emptySlotColumn);
                    }

                    return null;
                }

                case ONE -> {

                    if (slotValue == null) {
                        emptySlotRow = nextSlotRow;
                        emptySlotColumn = nextSlotColumn;

                        state = State.ONE_EMPTY;
                    }
                    else if (slotValue == firstCounter) {
                        state = State.TWO;
                    }
                    else {
                        return null;
                    }
                }

                case ONE_EMPTY -> {

                    if (slotValue == firstCounter) {
                        state = State.ONE_EMPTY_ONE;
                    }
                    else {
                        return null;
                    }
                }

                case TWO -> {

                    if (slotValue == firstCounter) {
                        state = State.THREE;
                    }
                    else if (slotValue == null) {
                        emptySlotRow = nextSlotRow;
                        emptySlotColumn = nextSlotColumn;

                        state = State.TWO_EMPTY;
                    }
                    else {
                        return null;
                    }
                }

                case THREE -> {

                    if (slotValue == null
                        && nextSlotRow == board.getNumCountersInColumn(nextSlotColumn)) {
                        return categoriseMove(firstCounter, nextSlotColumn);
                    }

                    return null;
                }
            }
        }

        return null;
    }

    private Move categoriseMove(SlotValue counter, int column) {

        if (slotValue == counter) {
            return Move.makeWinner(column);
        }
        else {
            return Move.makePreventLoss(column);
        }
    }

    protected abstract int getNextSlotRow(int row, int offset);
    protected abstract int getNextSlotColumn(int column, int offset);

    private final SlotValue slotValue;

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
