package movers;

import java.util.Random;

/**
 * Created by user on 16.11.16.
 */
public enum MoveAction {
    left, right, up, down, stay, go;

    private static final MoveAction[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static MoveAction getRandomMoveAction()  {
        return VALUES[RANDOM.nextInt(SIZE-2)];
    }
}
