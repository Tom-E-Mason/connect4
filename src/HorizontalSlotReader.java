
public class HorizontalSlotReader extends SlotReader {

    HorizontalSlotReader(char colour) {
        super(colour);
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
