
import java.util.Random;

public class ComputerPlayer extends Player {

    ComputerPlayer(char colour) {
        super(colour);
    }

    @Override
    protected int getMove() {

        try {
            System.out.print("Computer thinking");
            Thread.sleep(PAUSE);
            System.out.print(" .");
            Thread.sleep(PAUSE);
            System.out.print(" .");
            Thread.sleep(PAUSE);
            System.out.print(" .\n");
            Thread.sleep(PAUSE * 2);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return RNG.nextInt(7);
    }

    private static final Random RNG = new Random();

    private static final int PAUSE = 0;
}
