package movers;

public class Point
{
	public int x;
	public int y;
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Point point = (Point) o;

		if (x != point.x) return false;
		return y == point.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	public void left() {
		if(y-1>=0){
			y-=1;
		}
	}

	public void right() {
		if(y+1<10){
			y+=1;
		}
	}

	public void up() {
		if(x-1>=0){
			x-=1;
		}
	}

	public void down() {
		if(x+1<10){
			x+=1;
		}
	}
}
