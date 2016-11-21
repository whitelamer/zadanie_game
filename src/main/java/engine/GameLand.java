package engine;

import characters.Creature;
import characters.MobModel;
import movers.Mover;
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
	private Integer countTurns;
	private boolean setlled=false;
	public void setlle(List<MobModel> entries) {
		if(entries.size()>size*size){
			return;
		}
		countTurns=0;
		array = new MobModel[size][size];
		while(entries.size()>0){
			int x=(int) (Math.random()*(double)size);
			int y=(int) (Math.random()*(double)size);
			while(array[x][y]!=null){
				x=(int) (Math.random()*(double)size);
				y=(int) (Math.random()*(double)size);
			}
			array[x][y]=entries.remove(0);
		}
		setlled=true;
	}

	public boolean isSettle(){
		return setlled;
	}

	public void move(){
			for(int i=0;i<size;i++) {
				for (int j = 0; j < size; j++) {
					if (array[i][j] != null) {
						array[i][j].setTurn();
					}
				}
			}
			for(int i=0;i<size;i++){
				for(int j=0;j<size;j++){
					if(array[i][j]!=null && array[i][j].hasTurn()){
						MobModel src=array[i][j];
						Point point=Mover.move(i,j,src.getMoveAction());
						if(!point.equals(new Point(i,j))) {
							array[i][j]=null;
							array[point.getX()][point.getY()]=src.attack(array[point.getX()][point.getY()]);
						}
					}
				}
			}
		countTurns++;
	}

	public int countCreature(){
		int count=0;
		for(int i=0;i<size;i++) {
			for (int j = 0; j < size; j++) {
				if (array[i][j] != null && array[i][j].getClass()== Creature.class) {
					count++;
				}
			}
		}
		if(count==0)setlled=false;
		return count;
	}
	public void draw(){
		System.out.println('\n');
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
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

	public int getTurn() {
		return countTurns;
	}
}
