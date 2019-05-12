package map;

import other.CommonVL;

import java.awt.*;


public class Unit {

	private Image img;
	private int x;
	private int y;
	private int type;

	public Unit(int x, int y, int type, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		this.img=img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public Rectangle getRectangle(){
		return new Rectangle(x* CommonVL.UNIT_SIZE, y*CommonVL.UNIT_SIZE, CommonVL.UNIT_SIZE, CommonVL.UNIT_SIZE);
	}
	
	public boolean allowTankPass(){
		if(type==0||type==3||type==5)
			return true;
		return false;
	}
	
	public boolean allowBulletPass(){
		if(type==0||type>=3)
			return true;
		return false;
	}
	
	public void drawUnit(Graphics2D g) {
		g.drawImage(img, x*CommonVL.UNIT_SIZE, y*CommonVL.UNIT_SIZE, CommonVL.UNIT_SIZE, CommonVL.UNIT_SIZE, null);
	}
	
}
