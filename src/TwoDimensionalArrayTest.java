
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoDimensionalArrayTest {

    @Test
    void testTwoDimensionalArray() {

        var tda = new TwoDimensionalArray(2, 3);

        assertEquals(2, tda.getNumRows());
        assertEquals(3, tda.getNumColumns());

        assertNull(tda.get(0, 0));

        tda.set(0, 0, SlotValue.YELLOW);

        assertEquals(SlotValue.YELLOW, tda.get(0, 0));

        var secondTda = new TwoDimensionalArray(tda);

        secondTda.set(0, 1, SlotValue.RED);

        assertNull(tda.get(0, 1));
    }
}