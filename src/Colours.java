
public class Colours {

    public static SlotValue getOpponent(SlotValue slotValue) {

        if (slotValue == SlotValue.RED) {
            return SlotValue.YELLOW;
        }
        else {
            return SlotValue.RED;
        }
    }

    public static char toCharacter(SlotValue slotValue) {

        if (slotValue == SlotValue.RED) {
            return 'r';
        }
        else if (slotValue == SlotValue.YELLOW) {
            return 'y';
        }
        else if (slotValue == null) {
            return ' ';
        }

        throw new IllegalArgumentException();
    }
}
