package engine;

import characters.Player;

/**
 * Created by user on 16.11.16.
 */
public class GameThread extends Thread {
    private GameLand gameLand;
    private Player player;

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

    private GameThread(){}

    public void run()
    {
        gameLand=GameLand.getInstance();
        player=Player.getInstance();
        int creatures;
        while(player.isAlive()){
            gameLand.move();
            creatures = gameLand.countCreature();
            if(creatures==0) {
                return;
            }
        }
    }
}
