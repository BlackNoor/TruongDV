package map;

import model.BulletMgr;
import other.CommonVL;
import other.Effection;
import other.EffectionMgr;
import other.ImageMgr;

import java.awt.*;


public class Bird {
	
	public static boolean IS_LIVE;
	private Image img;
	private static final int X_BIRD=12* CommonVL.UNIT_SIZE;
	private static final int Y_BIRD=24*CommonVL.UNIT_SIZE;
	
	public Bird() {
		IS_LIVE=true;
		this.img= ImageMgr.arrBirdImages.get(0);
	}

	public void drawBird(Graphics2D g){
		if(!IS_LIVE)
			img=ImageMgr.arrBirdImages.get(1);
		g.drawImage(img, X_BIRD, Y_BIRD, null);
	}
	
	public boolean checkGameOver(BulletMgr bulletMgr){
		for (int i = bulletMgr.getArrBullet().size()-1; i >= 0 ; i--) {
			if(bulletMgr.getArrBullet().get(i).getRectangle().intersects(getRectangle())){
				IS_LIVE=false;
				EffectionMgr.addEffection(new Effection(ImageMgr.arrBumImages, X_BIRD, Y_BIRD));
			}
		}
		return !IS_LIVE;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(X_BIRD, Y_BIRD, CommonVL.TANK_SIZE, CommonVL.TANK_SIZE);
	}
}
