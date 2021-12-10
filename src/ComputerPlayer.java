
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

// ---------------------------------------------------------------------------------------
// The ComputerPlayer uses a brute-force approach for finding winning moves and defensive
// moves. If it cannot find a suitable move, one is picked randomly. The random moves are
// tested to see if they will allow the opponent to win, and if possible these moves are
// avoided.
// ---------------------------------------------------------------------------------------
public class ComputerPlayer extends Player {

    ComputerPlayer(SlotValue slotValue) {
        super(slotValue);
    }

    // -----------------------------------------------------------------------------------
    // getMove() first tries to find winning and defensive moves with the output from
    // findMoves().
    // If findMoves() returns a non-empty list of moves, they are sorted using their
    // category to prioritise winning moves.
    // If fineMoves() returns an empty list, the columns of the board are randomly tested
    // to find one that won't result in an opportunity for the human player.
    // If all the columns result in a risky move, the first is chosen.
    // -----------------------------------------------------------------------------------
    @Override
    protected int getMove(Board board) {

        System.out.println("Computer thinking . . .");

        final var moves = findMoves(board, getColour());

        if (moves.size() > 0) {
            moves.sort(Comparator.comparing(Move::category));
            return moves.get(0).column();
        }

        return findRandomMove(board);
    }

    private int findRandomMove(Board board) {

        var randomMoves = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
        Collections.shuffle(randomMoves, RNG);

        SlotValue opponentSlotValue = Colours.getOpponent(getColour());

        for (int randomMove : randomMoves) {

            var newBoard = new Board(board);

            try {
                newBoard.set(randomMove, getColour());
            }
            catch (ColumnFullException e) {
                continue;
            }

            var winningOpponentMoves = findMoves(board, opponentSlotValue);

            if (winningOpponentMoves.size() == 0) {
                return randomMove;
            }
        }

        return randomMoves.get(0);
    }

    private ArrayList<Move> findMoves(Board board, SlotValue slotValue) {

        var moves = new ArrayList<Move>();

        moves.addAll(findHorizontalMoves(board, slotValue));
        moves.addAll(findVerticalMoves(board, slotValue));
        moves.addAll(findDiagonalBotLeftTopRightMoves(board, slotValue));
        moves.addAll(findDiagonalBotRightTopLeftMoves(board, slotValue));

        return moves;
    }

    private ArrayList<Move> findHorizontalMoves(Board board, SlotValue slotValue) {

        var moves = new ArrayList<Move>();
        var reader = new HorizontalSlotReader(slotValue);

        for (int row = 0; row < board.getNumRows(); ++row) {

            for (int column = 0; column < board.getNumColumns() - 3; ++column) {

                var move = reader.readFourSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findVerticalMoves(Board board, SlotValue slotValue) {

        var moves = new ArrayList<Move>();
        var reader = new VerticalSlotReader(slotValue);

        for (int column = 0; column < board.getNumColumns(); ++column) {

            for (int row = 0; row < board.getNumRows() - 3; ++row) {

                var move = reader.readFourSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findDiagonalBotLeftTopRightMoves(Board board, SlotValue slotValue) {

        var moves = new ArrayList<Move>();
        var reader = new BotLeftTopRightSlotReader(slotValue);

        for (int row = 0; row < board.getNumRows() - 3; ++row) {

            for (int column = 0; column < board.getNumColumns() - 3; ++column) {

                var move = reader.readFourSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findDiagonalBotRightTopLeftMoves(Board board, SlotValue slotValue) {

        var moves = new ArrayList<Move>();
        var reader = new BotRightTopLeftSlotReader(slotValue);

        for (int row = 0; row < board.getNumRows() - 3; ++row) {

            for (int column = 3; column < board.getNumColumns(); ++column) {

                var move = reader.readFourSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private static final Random RNG = new Random(System.currentTimeMillis());
}
