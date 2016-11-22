package characters;

import attakers.AttackbleEntity;
import attakers.Damager;
import movers.MovableEntity;
import movers.MoveAction;

public abstract class Entity implements MovableEntity, AttackbleEntity, DrawableEntity
{
	protected Damager dps;
	protected double helth;
	protected MoveAction moveAction;


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


	public Damager getDps() {
		return dps;
	}
	public void setDps(Damager dps) {
		this.dps = dps;
	}

	public AttackbleEntity doAttack(AttackbleEntity target){
		if(target!=null && target.doDamage(dps.damage())>=0){
			return target.doAttack(this);
		}
		return this;
	}

	public double doDamage(double uron) {
		setHelth(getHelth()-uron);
		return getHelth();
	}

	public double getHelth() {
		return helth;
	}
	public void setHelth(double helth) {
		this.helth = helth;
	}

	public boolean isAlive() {
		return getHelth()>0;
	}


	protected String model;
	public String getModel(){
        if(model==null)return getClass().getName();
        return model;
    }
    public void setModel(String model){
        this.model=model;
    }
}
