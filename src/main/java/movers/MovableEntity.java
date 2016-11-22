package movers;

import movers.MoveAction;

/**
 * Created by user on 21.11.16.
 */
public interface MovableEntity {
    boolean isAction();
    void setAction(MoveAction action);
    MoveAction getAction();
    MoveAction doAction();
}
