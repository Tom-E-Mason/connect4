
public class BotRightTopLeftSlotReader extends SlotReader {

    BotRightTopLeftSlotReader(SlotValue slotValue) {
        super(slotValue);
    }

    @Override
    protected int getNextSlotRow(int row, int offset) {
        return row + offset;
    }

    @Override
    protected int getNextSlotColumn(int column, int offset) {
        return column - offset;
    }
}
