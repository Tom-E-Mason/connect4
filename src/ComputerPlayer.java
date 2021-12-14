
import java.util.ArrayList;
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

    ComputerPlayer(Colour colour) {
        super(colour);
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

    // -----------------------------------------------------------------------------------
    // findMoves finds all winning and defensive moves for a given board setup.
    // -----------------------------------------------------------------------------------
    private ArrayList<Move> findMoves(Board board, Colour colour) {

        var moves = new ArrayList<Move>();

        moves.addAll(findHorizontalMoves(board, colour));
        moves.addAll(findVerticalMoves(board, colour));
        moves.addAll(findDiagonalBotLeftTopRightMoves(board, colour));
        moves.addAll(findDiagonalBotRightTopLeftMoves(board, colour));

        return moves;
    }

    // -----------------------------------------------------------------------------------
    // findRandomMove shuffles the possible moves and tests each one to see if it gives
    // the opponent an opportunity to win. If all options fail
    // -----------------------------------------------------------------------------------
    private int findRandomMove(Board board) {

        var randomMoves = new ArrayList<Integer>();

        for (int i = 0; i < board.getNumColumns(); ++i) {
            if (!board.isColumnFull(i)) {
                randomMoves.add(i);
            }
        }

        Collections.shuffle(randomMoves, RNG);

        Colour opponentColour = Colours.getOpponent(getColour());

        for (int randomMove : randomMoves) {

            var newBoard = new Board(board);

            newBoard.set(randomMove, getColour());

            var opponentMoves = findMoves(newBoard, opponentColour);

            if (!containsWinningMove(opponentMoves)) {
                return randomMove;
            }
        }

        return randomMoves.get(0);
    }

    private ArrayList<Move> findHorizontalMoves(Board board,
                                                Colour colour) {

        var moves = new ArrayList<Move>();
        var reader = new HorizontalSlotReader(colour);

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

    private ArrayList<Move> findVerticalMoves(Board board,
                                              Colour colour) {

        var moves = new ArrayList<Move>();
        var reader = new VerticalSlotReader(colour);

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

    private ArrayList<Move> findDiagonalBotLeftTopRightMoves(Board board,
                                                             Colour colour) {

        var moves = new ArrayList<Move>();
        var reader = new BotLeftTopRightSlotReader(colour);

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

    private ArrayList<Move> findDiagonalBotRightTopLeftMoves(Board board,
                                                             Colour colour) {

        var moves = new ArrayList<Move>();
        var reader = new BotRightTopLeftSlotReader(colour);

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

    private static boolean containsWinningMove(ArrayList<Move> moves) {

        for (Move move : moves) {
            if (move.category() == Move.Category.WINNER) {
                return true;
            }
        }

        return false;
    }

    private static final Random RNG = new Random(System.currentTimeMillis());
}
