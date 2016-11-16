import characters.Creature;
import characters.MobModel;
import characters.NPC;
import characters.Player;
import items.Damager;
import movers.ConsoleInput;

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
		final Player player = Player.getInstance(new Damager(1,10),10);
		allmobs.add(player);

		final GameLand gameLand=GameLand.getInstance();
		gameLand.setlle(allmobs);
//        Jostic jostic = Jostic.getInstance();
//        jostic.start();
        ConsoleInput console = ConsoleInput.getInstance();
        console.start();

        Thread GameThready = new Thread(new Runnable()
        {
            public void run()
            {
                int creatures=gameLand.countCreature();
                gameLand.draw();
                System.out.print("Creatures alive:"+creatures+" ");
                System.out.println(player);

                while(player.isAlive()){
                    gameLand.move();
                    creatures=gameLand.countCreature();
                    if(player.isAlive()&&creatures>0)gameLand.draw();
                    if(creatures==0) {
                        System.out.println("PLAYER WIN");
                        return;
                    }
                    System.out.print("Creatures alive:"+creatures+" ");
                    System.out.println(player);
                }
                System.out.println("GAME OVER");
            }
        });
        GameThready.start();
	}
}
