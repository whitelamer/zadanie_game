package movers;

/**
 * Created by user on 16.11.16.
 */
public class Mover {
    public static Point doMove(int x, int y, MovableEntity entity) {
        Point point=new Point(x,y);
        return doMove(point, entity);
    }

    public static Point doMove(Point point, MovableEntity entity){
        MoveAction action = entity.doAction();

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
