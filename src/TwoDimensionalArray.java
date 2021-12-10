import java.util.Arrays;

public class TwoDimensionalArray {

    TwoDimensionalArray(int rows, int columns) {
        this.data = new char[rows][columns];
    }

    TwoDimensionalArray(TwoDimensionalArray other) {

        data = new char[other.data.length][other.data[0].length];

        for (int row = 0; row < other.data.length; ++row) {

            data[row] = Arrays.copyOf(other.data[row], other.data[row].length);
        }
    }

    void set(int row, int column, char value) {
        data[row][column] = value;
    }

    char get(int row, int column) {
        return data[row][column];
    }

    int getNumRows() {
        return data.length;
    }

    int getNumColumns() {
        return data[0].length;
    }

    private char[][] data;
}
