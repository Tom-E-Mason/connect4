
public class HorizontalSlotReader extends SlotReader {

    HorizontalSlotReader(SlotValue slotValue) {
        super(slotValue);
    }

    @Override
    protected int getNextSlotRow(int row, int offset) {
        return row;
    }

    @Override
    protected int getNextSlotColumn(int column, int offset) {
        return column + offset;
    }
}
