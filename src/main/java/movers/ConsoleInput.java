package movers;

import characters.Player;
import engine.GameThread;

import java.util.Scanner;

/**
 * Created by user on 16.11.16.
 */
public class ConsoleInput extends Thread {
    private static volatile ConsoleInput instance;

    public static ConsoleInput getInstance() {
        ConsoleInput localInstance = instance;
        if (localInstance == null) {
            synchronized (ConsoleInput.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConsoleInput();
                }
            }
        }
        return localInstance;
    }
    private ConsoleInput(){
        setDaemon(true);
        setName("ConsoleInput");
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Enter next step [left,right,up,down,stay,go(random)]:");
            Scanner scan=new Scanner(System.in);
            String direction=scan.next();
            try {
                MoveAction action=MoveAction.valueOf(direction);
                GameThread.getInstance().getPlayer().setAction(action);
            }catch (IllegalArgumentException e){
                System.out.println("input error");
            }
        }
    }
}
