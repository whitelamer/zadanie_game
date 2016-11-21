package characters;

import items.Damager;
import movers.MoveAction;
import movers.Point;

public abstract class MobModel
{
	protected Damager dps;
	protected double helth;
	protected MoveAction moveAction;

	abstract MobModel attack(MobModel target);
	//Point move(int x, int y);
	public boolean isAction() {
		return moveAction!=MoveAction.stay;
	}
	public void setAction(MoveAction action) {
		moveAction=action;
	}
	public MoveAction getAction() {
		return moveAction;
	}
	public MoveAction doAction() {
		MoveAction ret=moveAction;
		moveAction=MoveAction.stay;
		return ret;
	}
	
	abstract double damage(double uron);
	abstract boolean isAlive();
	abstract void draw();
}
