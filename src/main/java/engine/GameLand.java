package engine;

import attakers.Battle;
import attakers.AttackbleEntity;
import characters.Creature;
import characters.DrawableEntity;
import characters.Entity;
import movers.MovableEntity;
import movers.MoveAction;
import movers.Mover;
import movers.Point;

import java.util.*;

public class GameLand
{
	//	private static volatile GameLand instance;
//
//	public static GameLand getInstance() {
//		GameLand localInstance = instance;
//		if (localInstance == null) {
//			synchronized (GameLand.class) {
//				localInstance = instance;
//				if (localInstance == null) {
//					instance = localInstance = new GameLand();
//				}
//			}
//		}
//		return localInstance;
//	}
	GameLand(int size) {
		this.size=size;
	}

	private int size;
	MovableEntity[][] settleMap;
	HashMap<Point, Battle> battleMap;

	private boolean setlled=false;
	private int creatureCount;

	public void setlle(List<MovableEntity> entries) {
		if(entries.size()>size*size){
			return;
		}

		settleMap = new MovableEntity[size][size];

		while(entries.size()>0){
			int x=(int) (Math.random()*(double)size);
			int y=(int) (Math.random()*(double)size);
			while(settleMap[x][y]!=null){
				x=(int) (Math.random()*(double)size);
				y=(int) (Math.random()*(double)size);
			}
			settleMap[x][y]=entries.remove(0);
		}
		setlled=true;
	}


	public boolean isSettle(){
		return setlled;
	}

	public void doMovements(){
		for(int i=0;i<size;i++) {
			for (int j = 0; j < size; j++) {
				if (settleMap[i][j] != null) {
					settleMap[i][j].setAction(MoveAction.go);
				}
			}
		}

		battleMap=new HashMap<Point, Battle>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){

				if(settleMap[i][j]!=null){
					Point point=Mover.doMove(i,j,settleMap[i][j]);

					Battle battle=battleMap.get(point);
					if(battle==null){
						battle=new Battle();
						battleMap.put(point,battle);
					}
					battle.addEntity((AttackbleEntity) settleMap[i][j]);
				}
			}
		}
	}

	public void doBattles(){
		if(battleMap==null)return;
		MovableEntity[][] newSettleMap = new MovableEntity[size][size];
		int creatureCount=0;
		boolean setlled=false;
		for(Point battlePoint : battleMap.keySet()){
			AttackbleEntity winner=battleMap.get(battlePoint).getWiner();

			if(winner.getClass() == Creature.class){
				creatureCount++;
				setlled=true;
			}

			newSettleMap[battlePoint.getX()][battlePoint.getY()] = (MovableEntity) winner;

		}
		this.setlled=setlled;
		this.creatureCount=creatureCount;
		settleMap=newSettleMap;
	}

	public int countCreature(){
		return creatureCount;
	}

	public DrawableEntity[][] getSettleMap(){
		DrawableEntity[][] drawMap=new DrawableEntity[size][size];
		for(int i=0;i<settleMap.length;i++) {
			for (int j = 0; j < settleMap[i].length; j++) {
				drawMap[i][j]=(DrawableEntity)settleMap[i][j];
			}
		}
		return drawMap;//(DrawableEntity[][])settleMap;
	}

//	public void draw(){
//		System.out.println('\n');
//		for(int i=0;i<size;i++){
//			for(int j=0;j<size;j++){
//				if(array[i][j]!=null){
//					array[i][j].draw();
//				}else{
//					//System.out.print('\u0DF4');
//					System.out.print('_');
//				}
//			}
//			System.out.println('|');
//		}
//	}
}
