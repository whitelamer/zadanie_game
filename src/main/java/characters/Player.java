package characters;

import items.Damager;

import movers.MoveAction;
import movers.Mover;
import movers.Point;

import java.util.*;
public class Player extends Creature
{
    private static volatile Player instance;
    public static Player getInstance() {
        return getInstance(new Damager(1,10),10);
    }
    public static Player getInstance(Damager dps, double helth) {
        Player localInstance = instance;
        if (localInstance == null) {
            synchronized (Player.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Player(dps,helth);
                }
            }
        }
        return localInstance;
    }


    private Player(Damager dps, double helth) {
        super(dps, helth);
    }

    public MobModel attack(MobModel target)
    {
        if(target.getClass()==NPC.class){
            return target.attack(this);
        }else {
            if(target.damage(getDps().damage())>=0){
                return target.attack(this);
            }
            return this;
        }
    }
    Point nowPoint;
    public Point move(int x, int y) {
        nowPoint = new Point(x, y);
        synchronized (nowPoint) {
            try {
                nowPoint.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return nowPoint;
    }

    public void draw()
    {
        //U+1F6B9
        System.out.print("\uD83D\uDEB9");
    }

    public boolean isAlive(){
        return ((int)damage(0))>0?true:false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "dps=" + getDps() +
                ", helth=" + (int)getHelth() +
                '}';
    }

    public void addItem(Damager damager) {
        getDps().addItem(damager);
    }

    public void setAction(MoveAction action) {
        if(nowPoint!=null){
            synchronized(nowPoint){
                nowPoint = Mover.move(nowPoint, action);
                nowPoint.notify();
            }
        }
    }
}
