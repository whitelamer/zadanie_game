package characters;

import items.Damager;
import movers.MoveAction;
import movers.Mover;
import movers.Point;

import java.util.*;
public class Player extends Creature
{
    public Player(Damager dps, double helth) {
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

	public Point move(int x, int y)
	{
        System.out.println("Enter next step [left,right,up,down,stay,go(random)]:");
		Scanner scan=new Scanner(System.in);
		String direction=scan.next();
        try {
            return Mover.move(x,y, MoveAction.valueOf(direction));
        }catch (IllegalArgumentException e){
            System.out.println("input error");
            return move(x,y);
        }
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
}
