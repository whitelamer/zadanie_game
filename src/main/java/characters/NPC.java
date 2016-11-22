package characters;

import attakers.AttackbleEntity;
import attakers.Damager;
import movers.MoveAction;

public class NPC extends Entity
{
    public NPC(Damager dps, double helth) {
        this.helth=helth;
        this.dps=dps;
    }

	public void setAction(MoveAction action) {
		moveAction=MoveAction.stay;
	}
	
    public AttackbleEntity doAttack(AttackbleEntity target) {
        if(target==null)return null;
        if(target.getClass()==Player.class){
            target.doDamage(-10);
            ((Player)target).addItem(new Damager(5,10));
            return target;
        }else {
            return super.doAttack(target);
        }
    }

//    @Override
//    public void draw() {
//        System.out.print('\u263A');
//    }

    @Override
    public String toString() {
        return "NPC{" +
                "dps=" + getDps() +
                ", helth=" + (int)getHelth() +
                '}';
    }
}
