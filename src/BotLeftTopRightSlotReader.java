
public class BotLeftTopRightSlotReader extends SlotReader {

    BotLeftTopRightSlotReader(Colour colour) {
        super(colour);
    }

    @Override
    protected int getNextSlotRow(int row, int offset) {
        return row + offset;
    }

    @Override
    protected int getNextSlotColumn(int column, int offset) {
        return column + offset;
    }
}
