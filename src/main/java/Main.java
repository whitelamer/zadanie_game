import characters.DrawableEntity;
import characters.Player;
import engine.GameThread;
import movers.ConsoleInput;

import static java.lang.Thread.sleep;

public class Main
{
    public static void main(String[] args) {

        GameThread.getInstance().startGame();

        ConsoleInput.getInstance().start();


        Thread ConsoleDraw =new Thread(new Runnable() {
            private String drawModel(String model) {
                if(model==null)return "_";
                if(model.contains("Player"))return "\uD83D\uDEB9";
                if(model.contains("NPC"))return "\u263A";
                if(model.contains("Creature"))return "\u2620";
                return "_";
            }
            public void run() {
                GameThread gameThread=GameThread.getInstance();
                Player player= gameThread.getPlayer();
                int turnDrawed=gameThread.getTurn();
                while (gameThread.isAlive()) {
                    if (player.isAlive()){
                        DrawableEntity[][] settleMap = gameThread.getSettleMap();
                        for(int i=0;i<settleMap.length;i++) {
                            for (int j = 0; j < settleMap[i].length; j++) {
                                if (settleMap[i][j] != null) {
                                    System.out.print(drawModel(settleMap[i][j].getModel()));
                                } else {
                                    System.out.print('_');
                                }
                            }
                            System.out.println('|');
                        }
                        System.out.print("Creatures alive:" + gameThread.countCreature() + " ");
                        System.out.println(player);
                        //synchronized (gameLand.moveMutex) {
                        while (turnDrawed==gameThread.getTurn()) {
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        turnDrawed=gameThread.getTurn();
                    }
                }
                ConsoleInput.getInstance().interrupt();
                if(gameThread.countCreature()==0) {
                    System.out.println("PLAYER WIN");
                }else{
                    System.out.println("GAME OVER");
                }
            }

        });

        ConsoleDraw.start();
    }
}
