
public abstract class SlotReader {

    SlotReader(char colour) {
        this.colour = colour;
    }

    public Move readFourSlots(Board board, int row, int column) {

        State state = State.NONE;
        char firstCounter = Board.BLANK_SLOT;

        int emptySlotRow = row;
        int emptySlotColumn = column;

        for (int i = 0; i < 4; ++i) {

            final int nextSlotRow = getNextSlotRow(row, i);
            final int nextSlotColumn = getNextSlotColumn(column, i);

            char c = board.get(nextSlotColumn, nextSlotRow);

            switch (state) {

                case NONE -> {

                    if (c == Board.BLANK_SLOT) {
                        state = State.EMPTY;
                    }
                    else {
                        firstCounter = c;
                        state = State.ONE;
                    }
                }

                case EMPTY -> {

                    if (c != Board.BLANK_SLOT) {
                        firstCounter = c;
                        state = State.EMPTY_ONE;
                    }
                    else {
                        return null;
                    }
                }

                case EMPTY_ONE -> {

                    if (c == firstCounter) {
                        state = State.EMPTY_TWO;
                    }
                    else {
                        return null;
                    }
                }

                case EMPTY_TWO, ONE_EMPTY_ONE, TWO_EMPTY -> {

                    if (c == firstCounter && board.isValidMove(emptySlotRow, emptySlotColumn)) {
                        return categoriseMove(firstCounter, emptySlotColumn);
                    }

                    return null;
                }

                case ONE -> {

                    if (c == Board.BLANK_SLOT) {
                        emptySlotRow = nextSlotRow;
                        emptySlotColumn = nextSlotColumn;

                        state = State.ONE_EMPTY;
                    }
                    else if (c == firstCounter) {
                        state = State.TWO;
                    }
                    else {
                        return null;
                    }
                }

                case ONE_EMPTY -> {

                    if (c == firstCounter) {
                        state = State.ONE_EMPTY_ONE;
                    }
                    else {
                        return null;
                    }
                }

                case TWO -> {

                    if (c == firstCounter) {
                        state = State.THREE;
                    }
                    else if (c == Board.BLANK_SLOT) {
                        emptySlotRow = nextSlotRow;
                        emptySlotColumn = nextSlotColumn;

                        state = State.TWO_EMPTY;
                    }
                    else {
                        return null;
                    }
                }

                case THREE -> {

                    if (c == Board.BLANK_SLOT && board.isValidMove(nextSlotRow, nextSlotColumn)) {
                        return categoriseMove(firstCounter, nextSlotColumn);
                    }

                    return null;
                }
            }
        }

        return null;
    }

    private Move categoriseMove(char counter, int column) {

        if (colour == counter) {
            return Move.makeWinner(column);
        }
        else {
            return Move.makePreventLoss(column);
        }
    }

    protected abstract int getNextSlotRow(int row, int offset);
    protected abstract int getNextSlotColumn(int column, int offset);

    private final char colour;

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
