package characters;

import attakers.Damager;
import movers.MoveAction;

public class Creature extends Entity
{
	public Creature(Damager dps, double helth){
		this.helth=helth;
		this.dps=dps;
	}


	public void setAction(MoveAction action) {
		moveAction=MoveAction.go;
	}

//	public void draw()
//	{
//		System.out.print('\u2620');
//	}

	@Override
	public String toString() {
		return "Creature{" +
				"dps=" + dps +
				", helth=" + (int)helth +
				'}';
	}
}
