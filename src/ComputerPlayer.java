import java.util.*;

public class ComputerPlayer extends Player {

    ComputerPlayer(char colour) {
        super(colour);
    }

    @Override
    protected int getMove(Board board) {

        think();

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

        char opponentColour = Colours.getOpponent(getColour());

        for (int randomMove : randomMoves) {

            var newBoard = new Board(board);

            try {
                newBoard.set(randomMove, getColour());
            }
            catch (ColumnFullException e) {
                continue;
            }

            var winningOpponentMoves = findMoves(board, opponentColour);

            if (winningOpponentMoves.size() == 0) {
                return randomMove;
            }
        }

        return randomMoves.get(0);
    }

    private ArrayList<Move> findMoves(Board board, char colour) {

        var moves = new ArrayList<Move>();

        moves.addAll(findHorizontalMoves(board, colour));
        moves.addAll(findVerticalMoves(board, colour));
        moves.addAll(findDiagonalBotLeftTopRightMoves(board, colour));
        moves.addAll(findDiagonalBotRightTopLeftMoves(board, colour));

        return moves;
    }

    private ArrayList<Move> findHorizontalMoves(Board board, char colour) {

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

    private ArrayList<Move> findVerticalMoves(Board board, char colour) {

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

    private ArrayList<Move> findDiagonalBotLeftTopRightMoves(Board board, char colour) {

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

    private ArrayList<Move> findDiagonalBotRightTopLeftMoves(Board board, char colour) {

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
