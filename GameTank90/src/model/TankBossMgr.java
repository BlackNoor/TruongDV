package model;

import gui.MapPanel;
import map.MapMgr;
import other.CommonVL;
import other.ImageMgr;
import other.SoundMgr;

import java.awt.*;
import java.util.ArrayList;


public class TankBossMgr {
	
	private ArrayList<TankBoss> arrTankBoss;
	
	public TankBossMgr() {
		arrTankBoss=new ArrayList<TankBoss>();
	}
	
	//khi số tank địch trên mab còn lại nhỏ hơn 2 thì add team tank mới.
	public boolean addTeamTankBoss(){
		if(arrTankBoss.size()<2 && MapPanel.numBoss>0){
			SoundMgr.play(SoundMgr.life);
			MapPanel.numBoss-=3;
			if(MapPanel.numBoss>12)
				for (int i = 0; i < 3; i++) {
					arrTankBoss.add(new TankBoss(CommonVL.BOSS_ID, i*CommonVL.MAP_SIZE/2/CommonVL.TANK_SIZE*CommonVL.TANK_SIZE, 0, CommonVL.TANK_SIZE, CommonVL.DOWN_ORIENT, CommonVL.STANDARD_SPEED/4, ImageMgr.arrBoss1Images, 1));
				}
			else if(MapPanel.numBoss>6)
				for (int i = 0; i < 3; i++) {
					arrTankBoss.add(new TankBoss(CommonVL.BOSS_ID, i*CommonVL.MAP_SIZE/2/CommonVL.TANK_SIZE*CommonVL.TANK_SIZE, 0, CommonVL.TANK_SIZE, CommonVL.DOWN_ORIENT, CommonVL.STANDARD_SPEED/2, ImageMgr.arrBoss2Images, 2));
				}
			else if(MapPanel.numBoss>=3)
				for (int i = 0; i < 3; i++) {
					arrTankBoss.add(new TankBoss(CommonVL.BOSS_ID, i*CommonVL.MAP_SIZE/2/CommonVL.TANK_SIZE*CommonVL.TANK_SIZE, 0, CommonVL.TANK_SIZE, CommonVL.DOWN_ORIENT, CommonVL.STANDARD_SPEED/4, ImageMgr.arrBoss31Images, 3));
				}
			return true;
		}
		return false;
	}

	
	public void drawAllTankBoss(Graphics2D g) {
		for (int i = arrTankBoss.size() - 1; i >= 0; i--) {
			arrTankBoss.get(i).drawCShape(g);
		}
	}
	
	public void autoMoveAll(MapMgr mapMgr, TankPlayer tankPlayer) {
		for (int i = arrTankBoss.size() - 1; i >= 0; i--) {
			TankBoss tankBoss=arrTankBoss.get(i);
			//Nếu vị trí nằm nửa bên trái map thì ưu tiên đi về bên phải hơn. tương tự khi ở bên phải thì đi về bên trái nhiều hơn.
			if(tankBoss.getX()+CommonVL.TANK_SIZE/2<CommonVL.MAP_SIZE/2)
				tankBoss.autoMove(mapMgr,tankPlayer,CommonVL.LEFT_ORIENT);
			else
				tankBoss.autoMove(mapMgr,tankPlayer,CommonVL.RIGH_ORIENT);
		}
	}
	
	public ArrayList<TankBoss> getArrTankBoss() {
		return arrTankBoss;
	}

	
	public void removeTank(TankBoss tank) {
		arrTankBoss.remove(tank);
	}

	public void autoFireAll(BulletMgr bulletMgr) {
		for (int i = arrTankBoss.size() - 1; i >= 0; i--) {
			TankBoss tankBoss=arrTankBoss.get(i);
			bulletMgr.addBullet(tankBoss.fireBullet(CommonVL.BOSS_ID));
		}
	}
	
}
