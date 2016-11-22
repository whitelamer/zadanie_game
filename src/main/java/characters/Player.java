package characters;

import attakers.AttackbleEntity;
import attakers.Damager;
import movers.MoveAction;
public class Player extends Entity
{
//    private static volatile Player instance;
//    public static Player getInstance() {
//        return getInstance(new Damager(1,10),10);
//    }
//    public static Player getInstance(Damager dps, double helth) {
//        Player localInstance = instance;
//        if (localInstance == null) {
//            synchronized (Player.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new Player(dps,helth);
//                }
//            }
//        }
//        return localInstance;
//    }


    public Player(Damager dps, double helth) {
        this.helth=helth;
        this.dps=dps;
    }

    public AttackbleEntity doAttack(AttackbleEntity target)
    {
        if(target==null)return this;

        if(target.getClass()==NPC.class){
            return target.doAttack(this);
        }else {
            if(target.doDamage(getDps().damage())>=0){
                return target.doAttack(this);
            }
            return this;
        }
    }

//    public void draw()
//    {
//        //U+1F6B9\uD83D
//        System.out.print("\uDEB9");
//    }

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

    //private MoveAction action;

    private final Object mutex=new Object();

    @Override
    public MoveAction doAction() {
        synchronized (mutex) {
            try {
                mutex.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return super.doAction();
    }

    public void setAction(MoveAction action) {
        synchronized(this.mutex){
            super.setAction(action);
            mutex.notifyAll();
        }
    }
}
