
import characters.Creature;
import characters.MobModel;
import movers.Point;

import java.util.*;
public class GameLand
{
	private static volatile GameLand instance;

	public static GameLand getInstance() {
		GameLand localInstance = instance;
		if (localInstance == null) {
			synchronized (GameLand.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new GameLand();
				}
			}
		}
		return localInstance;
	}


	private GameLand() {
	}

	private final int size=10;
	MobModel[][] array;

	public void setlle(List<MobModel> entryes){
		array = new MobModel[size][size];
		if(entryes.size()>size*size){
			return;
		}
		while(entryes.size()>0){
			int x=(int) (Math.random()*(double)size);
			int y=(int) (Math.random()*(double)size);
			while(array[x][y]!=null){
				x=(int) (Math.random()*(double)size);
				y=(int) (Math.random()*(double)size);
			}
			array[x][y]=entryes.remove(0);
		}
	}
	public void move(){
		MobModel[][] newarray=new MobModel[size][size];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				if(array[i][j]!=null){
					//Point point=new Point(i,j);
					MobModel src=array[i][j];
					Point point=src.move(i,j);
					if(!point.equals(new Point(i,j))) {
						array[i][j]=null;
						MobModel dst=array[point.getX()][point.getY()];
						array[point.getX()][point.getY()]=null;
						if (dst == null) {
							dst=newarray[point.getX()][point.getY()];
							newarray[point.getX()][point.getY()]=null;
						}
						if (dst != null) {
							System.out.print("Battle:"+src+" vs "+dst);
							src = src.attack(dst);
							newarray[point.getX()][point.getY()]=src;
							System.out.println(" Winner:"+src);
						}else{
							newarray[point.getX()][point.getY()] = src;
						}
					}else{
						newarray[i][j]=array[i][j];
					}
				}
			}
		}
		array=newarray;
	}
	public int countCreature(){
		int count=0;
		for(int i=0;i<10;i++) {
			for (int j = 0; j < 10; j++) {
				if (array[i][j] != null && array[i][j].getClass()== Creature.class) {
					count++;
				}
			}
		}
		return count;
	}
	public void draw(){
		System.out.println('\n');
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				if(array[i][j]!=null){
					array[i][j].draw();
				}else{
					//System.out.print('\u0DF4');
					System.out.print('_');
				}
			}
			System.out.println('|');
		}

	}
}
