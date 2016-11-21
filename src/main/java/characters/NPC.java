package characters;

import items.Damager;
import movers.MoveAction;
import movers.Mover;
import movers.Point;

public class NPC extends Creature
{
    public NPC(Damager dps, double helth) {
        super(dps, helth);
    }

	public void setAction(MoveAction action) {
		moveAction=MoveAction.stay;
	}
	
    @Override
    public MobModel attack(MobModel target) {
        if(target==null)return null;
        if(target.getClass()==Player.class){
            target.damage(-10);
            ((Player)target).addItem(new Damager(5,10));
            return target;
        }else {
            return super.attack(target);
        }
    }

    @Override
    public void draw() {
        System.out.print('\u263A');
    }

    @Override
    public String toString() {
        return "NPC{" +
                "dps=" + getDps() +
                ", helth=" + (int)getHelth() +
                '}';
    }
}
