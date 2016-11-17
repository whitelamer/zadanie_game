
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
		setlleMap=new HashMap<Point,MobModel>();
	}

	private final int size=10;
	//MobModel[][] array;

	HashMap<Point,MobModel> setlleMap;
	public void setlle(List<MobModel> entries) {
		if (entries.size() > size * size) {
			return;
		}
		setlleMap.clear();
		while (entries.size() > 0) {
			Point p = new Point((int) (Math.random() * (double) size), (int) (Math.random() * (double) size));
			while (setlleMap.containsKey(p)) {
				p.setX((int) (Math.random() * (double) size));
				p.setY((int) (Math.random() * (double) size));
			}
			setlleMap.put(p, entries.remove(0));
		}
	}
	public void move(){
		HashMap<Point,MobModel> newSetlleMap = new HashMap<Point,MobModel>();
		Iterator entries = setlleMap.entrySet().iterator();
		while (entries.hasNext()){
			Map.Entry<Point,MobModel> pair= (Map.Entry<Point, MobModel>) entries.next();
			Point newPoint=pair.getValue().move(pair.getKey().getX(),pair.getKey().getY());
			if(!newPoint.equals(pair.getKey())){
				entries.remove();
				if(setlleMap.containsKey(newPoint)){
					//BATLLE
					MobModel winner=pair.getValue().attack(setlleMap.remove(newPoint));
					newSetlleMap.put(newPoint, winner);
				}else{
					newSetlleMap.put(newPoint, pair.getValue());
				}
			}else{
				newSetlleMap.put(pair.getKey(), pair.getValue());
			}
		}
		setlleMap=newSetlleMap;
	}
//		array = new MobModel[size][size];
//		if(entryes.size()>size*size){
//			return;
//		}
//		while(entryes.size()>0){
//			int x=(int) (Math.random()*(double)size);
//			int y=(int) (Math.random()*(double)size);
//			while(array[x][y]!=null){
//				x=(int) (Math.random()*(double)size);
//				y=(int) (Math.random()*(double)size);
//			}
//			array[x][y]=entryes.remove(0);
//		}
//	}
//	public void move(){
//		MobModel[][] newarray=new MobModel[size][size];
//		for(int i=0;i<10;i++){
//			for(int j=0;j<10;j++){
//				if(array[i][j]!=null){
//					//Point point=new Point(i,j);
//					MobModel src=array[i][j];
//					Point point=src.move(i,j);
//
//					if(!point.equals(new Point(i,j))) {
//						array[i][j]=null;
//						MobModel dst=array[point.getX()][point.getY()];
//						array[point.getX()][point.getY()]=null;
//						if (dst == null) {
//							dst=newarray[point.getX()][point.getY()];
//							newarray[point.getX()][point.getY()]=null;
//						}
//						if (dst != null) {
//							System.out.print("Battle:"+src+" vs "+dst);
//							src = src.attack(dst);
//							newarray[point.getX()][point.getY()]=src;
//							System.out.println(" Winner:"+src);
//						}else{
//							newarray[point.getX()][point.getY()] = src;
//						}
//					}else{
//						newarray[i][j]=src;
//					}
//				}
//			}
//		}
//		array=newarray;
//	}
	public int countCreature(){
		int count=0;
		Iterator entries = setlleMap.values().iterator();
		while (entries.hasNext()){
			if (entries.next().getClass() == Creature.class) {
					count++;
			}
		}
//		for(int i=0;i<10;i++) {
//			for (int j = 0; j < 10; j++) {
//				if (array[i][j] != null && array[i][j].getClass()== Creature.class) {
//					count++;
//				}
//			}
//		}
		return count;
	}
	public void draw(){
		System.out.println('\n');
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				if(setlleMap.get(new Point(i,j))!=null){
					setlleMap.get(new Point(i,j)).draw();
//				if(array[i][j]!=null){
//					array[i][j].draw();
				}else{
					//System.out.print('\u0DF4');
					System.out.print('_');
				}
			}
			System.out.println('|');
		}

	}
}
