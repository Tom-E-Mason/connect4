
import java.util.*;

public class ComputerPlayer extends Player {

    ComputerPlayer(char colour) {
        super(colour);
    }

    @Override
    protected int getMove(Board board) {

        think();

        var moves = new ArrayList<Move>();

        moves.addAll(findHorizontalMoves(board));

        moves.addAll(findVerticalMoves(board));

        moves.addAll(findDiagonalBotLeftTopRightMoves(board));

        moves.addAll(findDiagonalBotRightTopLeftMoves(board));

        if (moves.size() > 0) {
            moves.sort(Comparator.comparing(Move::category));
            return moves.get(0).column();
        }

        return RNG.nextInt(7);
    }

    enum State {
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

    record Move(Category category, int column) {

        enum Category {
            WINNER,
            PREVENT_LOSS,
        }

        public static Move makeWinner(int column) {
            return new Move(Category.WINNER, column);
        }

        public static Move makePreventLoss(int column) {
            return new Move(Category.PREVENT_LOSS, column);
        }
    }

    private ArrayList<Move> findHorizontalMoves(Board board) {

        var moves = new ArrayList<Move>();

        for (int row = 0; row < board.numRows(); ++row) {

            for (int column = 0; column < board.numColumns() - 3; ++column) {

                var move = readFourHorizontalSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findVerticalMoves(Board board) {

        var moves = new ArrayList<Move>();

        for (int column = 0; column < board.numColumns(); ++column) {

            for (int row = 0; row < board.numRows() - 3; ++row) {

                var move = readFourVerticalSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findDiagonalBotLeftTopRightMoves(Board board) {

        var moves = new ArrayList<Move>();

        for (int row = 0; row < board.numRows() - 3; ++row) {

            for (int column = 0; column < board.numColumns() - 3; ++column) {

                var move = readFourDiagonalBotLeftTopRightSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findDiagonalBotRightTopLeftMoves(Board board) {

        var moves = new ArrayList<Move>();

        for (int row = 0; row < board.numRows() - 3; ++row) {

            for (int column = 3; column < board.numColumns(); ++column) {

                var move = readFourDiagonalBotRightTopLeftSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private Move readFourHorizontalSlots(Board board, int row, int column) {

        State state = State.NONE;
        char firstCounter = Board.BLANK_SLOT;

        for (int i = 0; i < 4; ++i) {

            char c = board.get(column + i, row);

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

                case EMPTY_TWO -> {
                    if (c == firstCounter && board.isValidMove(row, column)) {
                        return categoriseMove(firstCounter, column);
                    }

                    return null;
                }

                case ONE -> {
                    if (c == Board.BLANK_SLOT) {
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

                case ONE_EMPTY_ONE -> {

                    final int emptySlot = column + 1;

                    if (c == firstCounter && board.isValidMove(row, emptySlot)) {
                        return categoriseMove(firstCounter, emptySlot);
                    }

                    return null;
                }

                case TWO -> {
                    if (c == firstCounter) {
                        state = State.THREE;
                    }
                    else if (c == Board.BLANK_SLOT) {
                        state = State.TWO_EMPTY;
                    }
                    else {
                        return null;
                    }
                }

                case TWO_EMPTY -> {

                    final int emptySlot = column + 2;

                    if (c == firstCounter && board.isValidMove(row, emptySlot)) {
                        return categoriseMove(firstCounter, emptySlot);
                    }

                    return null;
                }

                case THREE -> {

                    final int emptySlot = column + 3;

                    if (c == Board.BLANK_SLOT && board.isValidMove(row, emptySlot)) {
                        return categoriseMove(firstCounter, emptySlot);
                    }

                    return null;
                }
            }
        }

        return null;
    }

    private Move readFourVerticalSlots(Board board, int row, int column) {

        State state = State.NONE;
        char firstCounter = Board.BLANK_SLOT;

        for (int i = 0; i < 4; ++i) {

            char c = board.get(column, row + i);

            switch (state) {
                case NONE -> {
                    if (c == Board.BLANK_SLOT) {
                        return null;
                    }
                    else {
                        firstCounter = c;
                        state = State.ONE;
                    }
                }
                case ONE -> {
                    if (c == firstCounter) {
                        state = State.TWO;
                    }
                    else {
                        return null;
                    }
                }
                case TWO -> {
                    if (c == firstCounter) {
                        state = State.THREE;
                    }
                    else {
                        return null;
                    }
                }
                case THREE -> {
                    if (c == Board.BLANK_SLOT) {
                        if (firstCounter == getColour()) {
                            return Move.makeWinner(column);
                        }

                        return Move.makePreventLoss(column);
                    }

                    return null;
                }
            }
        }

        return null;
    }

    private Move readFourDiagonalBotLeftTopRightSlots(Board board, int row, int column) {

        State state = State.NONE;
        char firstCounter = Board.BLANK_SLOT;

        for (int i = 0; i < 4; ++i) {

            char c = board.get(column + i, row + i);

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
                case EMPTY_TWO -> {
                    if (c == firstCounter && board.isValidMove(row, column)) {
                        return categoriseMove(firstCounter, column);
                    }

                    return null;
                }
                case ONE -> {
                    if (c == Board.BLANK_SLOT) {
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
                case ONE_EMPTY_ONE -> {

                    final int emptySlot = column + 1;

                    if (c == firstCounter && board.isValidMove(row + 1, emptySlot)) {
                        return categoriseMove(firstCounter, emptySlot);
                    }

                    return null;
                }
                case TWO -> {
                    if (c == firstCounter) {
                        state = State.THREE;
                    }
                    else if (c == Board.BLANK_SLOT) {
                        state = State.TWO_EMPTY;
                    }
                    else {
                        return null;
                    }
                }
                case TWO_EMPTY -> {
                    if (c == firstCounter) {
                        return categoriseMove(firstCounter, column + 2);
                    }

                    return null;
                }
                case THREE -> {

                    final int emptySlot = column + 3;

                    if (c == Board.BLANK_SLOT && board.isValidMove(row + 3, emptySlot)) {
                        return categoriseMove(firstCounter, emptySlot);
                    }

                    return null;
                }
            }
        }

        return null;
    }

    private Move readFourDiagonalBotRightTopLeftSlots(Board board, int row, int column) {

        State state = State.NONE;
        char firstCounter = Board.BLANK_SLOT;

        for (int i = 0; i < 4; ++i) {

            char c = board.get(column - i, row + i);

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
                case EMPTY_TWO -> {

                    if (c == firstCounter && board.isValidMove(row, column)) {
                        return categoriseMove(firstCounter, column);
                    }

                    return null;
                }
                case ONE -> {

                    if (c == Board.BLANK_SLOT) {
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
                case ONE_EMPTY_ONE -> {

                    final int emptySlot = column - 1;

                    if (c == firstCounter && board.isValidMove(row + 1, emptySlot)) {
                        return categoriseMove(firstCounter, emptySlot);
                    }

                    return null;
                }
                case TWO -> {

                    if (c == firstCounter) {
                        state = State.THREE;
                    }
                    else if (c == Board.BLANK_SLOT) {
                        state = State.TWO_EMPTY;
                    }
                    else {
                        return null;
                    }
                }
                case TWO_EMPTY -> {

                    final int emptySlot = column - 2;

                    if (c == firstCounter && board.isValidMove(row + 2, emptySlot)) {
                        return categoriseMove(firstCounter, emptySlot);
                    }

                    return null;
                }
                case THREE -> {

                    final int emptySlot = column - 3;

                    if (c == Board.BLANK_SLOT && board.isValidMove(row + 3, emptySlot)) {
                        return categoriseMove(firstCounter, emptySlot);
                    }

                    return null;
                }
            }
        }

        return null;
    }

    private Move categoriseMove(char counter, int column) {

        if (getColour() == counter) {
            return Move.makeWinner(column);
        }
        else {
            return Move.makePreventLoss(column);
        }
    }

    private static void think() {

        try {
            System.out.print("Computer thinking");
            Thread.sleep(PAUSE);
            System.out.print(" .");
            Thread.sleep(PAUSE);
            System.out.print(" .");
            Thread.sleep(PAUSE);
            System.out.print(" .\n");
            Thread.sleep(PAUSE * 2);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static final Random RNG = new Random(System.currentTimeMillis());

    private static final int PAUSE = 0;
}
