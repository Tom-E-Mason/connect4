
import java.util.Arrays;

// ---------------------------------------------------------------------------------------
// The Board class originally contained a standard Java 2D array directly as a field,
// but when it came to deep copying, I decided I wanted that logic hidden from the Board
// class as it wasn't relevant to its purpose.
// ---------------------------------------------------------------------------------------
public class TwoDimensionalArray {

    TwoDimensionalArray(int rows, int columns) {
        this.data = new SlotValue[rows][columns];
    }

    TwoDimensionalArray(TwoDimensionalArray other) {

        data = new SlotValue[other.data.length][other.data[0].length];

        for (int row = 0; row < other.data.length; ++row) {

            data[row] = Arrays.copyOf(other.data[row], other.data[row].length);
        }
    }

    void set(int row, int column, SlotValue value) {
        data[row][column] = value;
    }

    SlotValue get(int row, int column) {
        return data[row][column];
    }

    int getNumRows() {
        return data.length;
    }

    int getNumColumns() {
        return data[0].length;
    }

    private final SlotValue[][] data;
}
