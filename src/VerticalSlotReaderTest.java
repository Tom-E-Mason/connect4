import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VerticalSlotReaderTest {

    @Test
    void testReadFourSlots_ThreeInARow() {

        var reader = new VerticalSlotReader(Colour.YELLOW);
        var board = new Board();

        // |   |   |   |   |   |   |   |
        // | y |   |   |   |   |   | y |
        // | y |   |   |   |   |   | y |
        // | y |   |   |   |   |   | y |
        //   0   1   2   3   4   5   6

        board.set(0, Colour.YELLOW);
        board.set(0, Colour.YELLOW);
        board.set(0, Colour.YELLOW);

        board.set(6, Colour.YELLOW);
        board.set(6, Colour.YELLOW);
        board.set(6, Colour.YELLOW);

        var move = reader.readFourSlots(board, 0, 0);
        Assertions.assertEquals(0, move.column());

        move = reader.readFourSlots(board, 0, 6);
        Assertions.assertEquals(6, move.column());
    }
}