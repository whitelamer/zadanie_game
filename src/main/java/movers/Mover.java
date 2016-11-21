package movers;

import movers.MoveAction;

/**
 * Created by user on 16.11.16.
 */
public class Mover {
    public static Point move(int x, int y, MoveAction action) {
        Point point=new Point(x,y);
        return move(point, action);
    }

    public static Point move(Point point, MoveAction action){
        if(action==MoveAction.go){
            action=MoveAction.getRandomMoveAction();
        }
        
        Point newPoint=new Point(point.getX(),point.getY());
        
        if(action==MoveAction.stay)return newPoint;
        switch (action){
            case left:
                newPoint.left();
                break;
            case right:
                newPoint.right();
                break;
            case up:
                newPoint.up();
                break;
            case down:
                newPoint.down();
                break;
        }
        return newPoint;
    }
}
