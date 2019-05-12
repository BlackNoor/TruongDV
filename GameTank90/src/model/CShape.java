package model;

import other.CommonVL;
import other.ImageMgr;
import other.SoundMgr;

import java.awt.*;
import java.util.ArrayList;


public class CShape {

	private ArrayList<Image> arrImages;
	protected int id;
	protected int x;
	protected int y;
	protected int size;
	protected int orient;
	protected int speed;
	protected boolean runing;
	protected boolean canFire;
	
	public CShape(int x, int y, int size) {
		this.x=x;
		this.y=y;
		this.size=size;
	}
	
	public CShape(int id, int x, int y, int size, int orient, int speed, ArrayList<Image> arrImages) {
		this.id=id;
		this.x=x;
		this.y=y;
		this.size=size;
		this.orient=orient;
		this.speed=speed;
		this.setArrImages(arrImages);
	}
	
	public int getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getOrient() {
		return orient;
	}

	public boolean isRuning() {
		return runing;
	}

	public void setRuning(boolean runing) {
		this.runing = runing;
	}

	public boolean isCanFire() {
		return canFire;
	}

	public void setCanFire(boolean canFire) {
		this.canFire = canFire;
	}
	
	public void drawCShape(Graphics2D g){
		g.drawImage(arrImages.get(orient), x, y, null);
	}

	public void changeOrient(int orient) {
		if(this.orient!=orient){
			this.orient = orient;
			// khi chuyển hướng thì tự động điều chỉnh lại vị trí vào giữa làn đường.
			x=(x+ CommonVL.UNIT_SIZE/2)/CommonVL.UNIT_SIZE*CommonVL.UNIT_SIZE;
			y=(y+CommonVL.UNIT_SIZE/2)/CommonVL.UNIT_SIZE*CommonVL.UNIT_SIZE;
		}
	}
	
	public Bullet fireBullet(int id){
		SoundMgr.play(SoundMgr.shoot);
		return new Bullet(id, x, y,CommonVL.BULLET_SIZE, orient,CommonVL.STANDARD_SPEED, ImageMgr.arrBulletImages);
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, size, size);
	}

	public void move(int x, int y){
		this.x=x;
		this.y=y;
	}

	public void setArrImages(ArrayList<Image> arrImages) {
		this.arrImages = arrImages;
	}
	
}
