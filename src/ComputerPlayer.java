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

    private ArrayList<Move> findHorizontalMoves(Board board) {

        var moves = new ArrayList<Move>();
        var reader = new HorizontalSlotReader(getColour());

        for (int row = 0; row < board.numRows(); ++row) {

            for (int column = 0; column < board.numColumns() - 3; ++column) {

                var move = reader.readFourSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findVerticalMoves(Board board) {

        var moves = new ArrayList<Move>();
        var reader = new VerticalSlotReader(getColour());

        for (int column = 0; column < board.numColumns(); ++column) {

            for (int row = 0; row < board.numRows() - 3; ++row) {

                var move = reader.readFourSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findDiagonalBotLeftTopRightMoves(Board board) {

        var moves = new ArrayList<Move>();
        var reader = new BotLeftTopRightSlotReader(getColour());

        for (int row = 0; row < board.numRows() - 3; ++row) {

            for (int column = 0; column < board.numColumns() - 3; ++column) {

                var move = reader.readFourSlots(board, row, column);
                if (move != null) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private ArrayList<Move> findDiagonalBotRightTopLeftMoves(Board board) {

        var moves = new ArrayList<Move>();
        var reader = new BotRightTopLeftSlotReader(getColour());

        for (int row = 0; row < board.numRows() - 3; ++row) {

            for (int column = 3; column < board.numColumns(); ++column) {

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
