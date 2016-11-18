import characters.Creature;
import characters.MobModel;
import characters.NPC;
import characters.Player;
import items.Damager;
import movers.ConsoleInput;

import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class Main
{
    public static void main(String[] args) {
        List<MobModel> allmobs=new ArrayList<MobModel>();
        allmobs.add(new NPC(new Damager(100,100),150));
        allmobs.add(new NPC(new Damager(100,100),150));
        allmobs.add(new NPC(new Damager(100,100),150));

        allmobs.add(new Creature(new Damager(5,10),15));
        allmobs.add(new Creature(new Damager(5,10),15));
        allmobs.add(new Creature(new Damager(5,10),15));
        allmobs.add(new Creature(new Damager(5,10),15));
        allmobs.add(new Creature(new Damager(5,10),15));
        allmobs.add(Player.getInstance(new Damager(1,10),10));

        GameLand.getInstance().setlle(allmobs);
        GameThread.getInstance().start();


        ConsoleInput.getInstance().start();
        Thread ConsoleDraw =new Thread(new Runnable() {
            public void run() {
                Player player= Player.getInstance();
                GameLand gameLand= GameLand.getInstance();
                GameThread gameThread=GameThread.getInstance();
                int turnDrawed=gameLand.getTurn();
                while (gameThread.isAlive()) {
                    if (player.isAlive()){
                        gameLand.draw();
                        System.out.print("Creatures alive:" + gameLand.countCreature() + " ");
                        System.out.println(player);
                        //synchronized (gameLand.moveMutex) {
                        while (turnDrawed==gameLand.getTurn()) {
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        turnDrawed=gameLand.getTurn();
                    }
                }
                ConsoleInput.getInstance().interrupt();
                if(gameLand.countCreature()==0) {
                    System.out.println("PLAYER WIN");
                }else{
                    System.out.println("GAME OVER");
                }
            }
        });
        ConsoleDraw.start();
    }
}
