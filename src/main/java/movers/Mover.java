package movers;

import movers.MoveAction;

/**
 * Created by user on 16.11.16.
 */
public class Mover {
    public static Point move(int x, int y, MoveAction action){
        Point point=new Point(x,y);
        if(action==MoveAction.go){
            action=MoveAction.getRandomMoveAction();
        }
        if(action==MoveAction.stay)return point;
        switch (action){
            case left:
                point.left();
                break;
            case right:
                point.right();
                break;
            case up:
                point.up();
                break;
            case down:
                point.down();
                break;
        }
        return point;
    }
}
