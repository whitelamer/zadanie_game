package characters;

import movers.Point;

public interface MobModel
{
	MobModel attack(MobModel target);
	Point move(int x, int y);
	double damage(double uron);
	boolean isAlive();
	void draw();
}
