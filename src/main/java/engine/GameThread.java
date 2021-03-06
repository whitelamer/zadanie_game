package engine;

import characters.Creature;
import characters.DrawableEntity;
import movers.MovableEntity;
import characters.NPC;
import characters.Player;
import attakers.Damager;
import movers.MoveAction;

import java.util.ArrayList;
import java.util.List;

public class GameThread extends Thread {
    private GameLand gameLand;
    private Player player;
    private Integer countTurns;

    private static volatile GameThread instance;

    public static GameThread getInstance() {
        GameThread localInstance = instance;
        if (localInstance == null) {
            synchronized (GameThread.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GameThread();
                }
            }
        }
        return localInstance;
    }

    private GameThread(){

    }
    public void fillSettle(List<MovableEntity> setlleList){
        setlleList.add(new NPC(new Damager(100,100),150));
        setlleList.add(new NPC(new Damager(100,100),150));
        setlleList.add(new NPC(new Damager(100,100),150));

        setlleList.add(new Creature(new Damager(5,10),15));
        setlleList.add(new Creature(new Damager(5,10),15));
        setlleList.add(new Creature(new Damager(5,10),15));
        setlleList.add(new Creature(new Damager(5,10),15));
        setlleList.add(new Creature(new Damager(5,10),15));
    }
    public void startGame(){
        gameLand=new GameLand(10);

        List<MovableEntity> setlleList=new ArrayList<MovableEntity>();
        fillSettle(setlleList);
        if(player!=null){
            player.doDamage(999);
            player.setAction(MoveAction.stay);
        }
        player=new Player(new Damager(1,10),10);
        setlleList.add(player);

        gameLand.setlle(setlleList);
        countTurns=0;
        if(!isAlive())start();
    }

    public int countCreature(){
        return gameLand.countCreature();
    }

    public Player getPlayer(){
        return player;
    }
    public void run()
    {
        while(!interrupted()) {
            while (player.isAlive() && gameLand.isSettle()) {
                gameLand.doMovements();
                gameLand.doBattles();
                countTurns++;
//                if (gameLand.countCreature() == 0) {
//                    return;
//                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getTurn() {
        return countTurns;
    }

    public DrawableEntity[][] getSettleMap() {
        return gameLand.getSettleMap();
    }
}
