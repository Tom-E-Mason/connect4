
import java.util.ArrayList;
import java.util.Collections;

// ---------------------------------------------------------------------------------------
// The Board class originally contained a standard Java 2D array directly as a field,
// but when it came to deep copying, I decided I wanted that logic hidden from the Board
// class as it wasn't relevant to its purpose.
// It also allows the 2D array to be more efficiently implemented as a 1D array, while,
// not exposing this complexity to the user.
// ---------------------------------------------------------------------------------------
public class TwoDimensionalArray {

    TwoDimensionalArray(int rows, int columns) {
        this.data = new ArrayList<>(Collections.nCopies(rows * columns, null));
        this.rows = rows;
        this.columns = columns;
    }

    TwoDimensionalArray(TwoDimensionalArray other) {
        rows = other.rows;
        columns = other.columns;
        data = new ArrayList<>(other.data);
    }

    void set(int row, int column, Colour value) {
        data.set(row * columns + column, value);
    }

    Colour get(int row, int column) {
        return data.get(row * columns + column);
    }

    int getNumRows() {
        return rows;
    }

    int getNumColumns() {
        return columns;
    }

    private final ArrayList<Colour> data;
    private final int rows;
    private final int columns;
}
