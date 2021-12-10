
public class Colours {

    public static char RED = 'r';
    public static char YELLOW = 'y';

    public static char getOpponent(char colour) {
        assert(colour == RED || colour == YELLOW);

        if (colour == RED) {
            return YELLOW;
        }
        else {
            return RED;
        }
    }
}
