import characters.Creature;
import characters.MobModel;
import characters.NPC;
import characters.Player;
import items.Damager;

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		List<MobModel> allmobs=new ArrayList<MobModel>();
		allmobs.add(new NPC(new Damager(100,100),150));
		allmobs.add(new NPC(new Damager(100,100),150));
		allmobs.add(new NPC(new Damager(100,100),150));

		allmobs.add(new Creature(new Damager(5,10),15));
		allmobs.add(new Creature(new Damager(5,10),15));
		allmobs.add(new Creature(new Damager(5,10),15));
		allmobs.add(new Creature(new Damager(5,10),15));
		allmobs.add(new Creature(new Damager(5,10),15));
		Player player = new Player(new Damager(1,10),10);
		allmobs.add(player);

		GameLand gameLand=new GameLand();
		gameLand.setlle(allmobs);
        int creatures=gameLand.countCreature();
		gameLand.draw();
        System.out.println(player);
		while(player.isAlive()){
			gameLand.move();
			gameLand.draw();
            creatures=gameLand.countCreature();
            if(creatures==0) {
                System.out.println("PLAYER WIN");
                break;
            }
            System.out.print("Creatures alive:"+creatures+" ");
            System.out.println(player);
		}
        if(!player.isAlive()){
            System.out.println("GAME OVER");
        }
	}
}
