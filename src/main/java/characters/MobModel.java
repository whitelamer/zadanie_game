package characters;

import movers.MoveAction;
import movers.Point;

public interface MobModel
{
	MobModel attack(MobModel target);
	//Point move(int x, int y);
	boolean hasTurn();
	void setTurn(boolean turn);
	MoveAction getMoveAction();
	double damage(double uron);
	boolean isAlive();
	void draw();
}
