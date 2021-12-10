
public class VerticalSlotReader extends SlotReader {

    VerticalSlotReader(SlotValue slotValue) {
        super(slotValue);
    }

    @Override
    protected int getNextSlotRow(int row, int offset) {
        return row + offset;
    }

    @Override
    protected int getNextSlotColumn(int column, int offset) {
        return column;
    }
}
