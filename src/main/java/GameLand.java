
import characters.Creature;
import characters.MobModel;
import movers.Mover;
import movers.Point;

import java.util.*;
public class GameLand
{
	private static volatile GameLand instance;
	private Integer countTurns;
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
	}
/*	public void move(){
		//HashMap<Point,MobModel> newSetlleMap = new HashMap<Point,MobModel>();
//		turnEnd=false;
		Iterator entries = setlleMap.values().iterator();
		while (entries.hasNext()){
			((MobModel)entries.next()).setTurn();
		}
		entries = setlleMap.entrySet().iterator();
		while (entries.hasNext()){
			Map.Entry<Point,MobModel> pair= (Map.Entry<Point, MobModel>) entries.next();
			//Point newPoint=pair.getValue().move(pair.getKey().getX(),pair.getKey().getY());
			if(!pair.getValue().hasTurn())continue;

			Point newPoint= Mover.move(pair.getKey(),pair.getValue().getMoveAction());
			if(!newPoint.equals(pair.getKey())){
				entries.remove();
				if(setlleMap.containsKey(newPoint)){
					//BATLLE
					MobModel winner=pair.getValue().attack(setlleMap.remove(newPoint));
					setlleMap.put(newPoint, winner);
				}else{
					setlleMap.put(newPoint, pair.getValue());
				}
			}else{
				setlleMap.put(pair.getKey(), pair.getValue());
			}
		}
		synchronized (countTurns) {
			countTurns++;
			countTurns.notifyAll();
		}
	}*/

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
