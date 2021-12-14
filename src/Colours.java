
// ---------------------------------------------------------------------------------------
// These two static functions act as the bridge between the Enum values for colours and
// their representation in the terminal window.
// ---------------------------------------------------------------------------------------
public class Colours {

    public static Colour getOpponent(Colour colour) {

        if (colour == Colour.RED) {
            return Colour.YELLOW;
        }
        else {
            return Colour.RED;
        }
    }

    public static char toCharacter(Colour colour) {

        if (colour == Colour.RED) {
            return 'r';
        }
        else if (colour == Colour.YELLOW) {
            return 'y';
        }
        else if (colour == null) {
            return ' ';
        }

        throw new IllegalArgumentException();
    }
}
