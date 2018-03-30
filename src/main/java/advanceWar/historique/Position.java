package advanceWar.historique;

import java.io.Serializable;

public class Position implements Serializable{
	private Integer x;
	private Integer y;
	
	public Position() {}
	
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x:"+x+" y:"+y;
	}
}
