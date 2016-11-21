package characters;

import items.Damager;
import movers.MoveAction;
import movers.Mover;
import movers.Point;

public class Creature extends MobModel
{
	protected MoveAction moveAction;
	public Creature(Damager dps, double helth){
		this.helth=helth;
		this.dps=dps;
	}
	public Damager getDps() {
		return dps;
	}

	public void setDps(Damager dps) {
		this.dps = dps;
	}

	public double getHelth() {
		return helth;
	}

	public void setHelth(double helth) {
		this.helth = helth;
	}

	public double damage(double uron) {
		setHelth(getHelth()-uron);
		return getHelth();
	}

	public boolean isAlive() {
		return damage(0)>0?true:false;
	}


	public MobModel attack(MobModel target)
	{
		if(target!=null && target.damage(getDps().damage())>=0){
			return target.attack(this);
		}
		return this;
	}


	public void setAction(MoveAction action) {
		moveAction=MoveAction.go;
	}

	public void draw()
	{
		System.out.print('\u2620');
	}

	@Override
	public String toString() {
		return "Creature{" +
				"dps=" + dps +
				", helth=" + (int)helth +
				'}';
	}
}
