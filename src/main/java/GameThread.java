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
        int creatures=gameLand.countCreature();
        //gameLand.draw();
        //System.out.print("Creatures alive:"+creatures+" ");
        //System.out.println(player);

        while(player.isAlive()){
                gameLand.move();
                //gameLand.notifyAll();
                creatures = gameLand.countCreature();
            //if(player.isAlive()&&creatures>0)gameLand.draw();
            if(creatures==0) {
                //System.out.println("PLAYER WIN");
                return;
            }
            //System.out.print("Creatures alive:"+creatures+" ");
            //System.out.println(player);
        }
        //System.out.println("GAME OVER");
    }
}
