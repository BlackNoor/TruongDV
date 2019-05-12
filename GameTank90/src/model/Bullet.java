package model;

import gui.MapPanel;
import map.MapMgr;
import map.Unit;
import other.*;

import java.awt.*;
import java.util.ArrayList;


public class Bullet extends CShape {
	
	public Bullet(int id, int x, int y, int size, int orient, int speed, ArrayList<Image> arrImages) {
		super(id, x, y, size, orient, speed, arrImages);
		getStandartBullet();
		setRuning(true);
	}

	//điều chỉnh vị trí bắt đầu của đạn vào đúng nòng súng của tank tùy theo từng hướng.
	private void getStandartBullet() {
		switch (orient) {
		case CommonVL.UP_ORIENT:
			x += CommonVL.TANK_SIZE / 2 - CommonVL.BULLET_SIZE / 2;
			y -= CommonVL.BULLET_SIZE;
			break;
		case CommonVL.DOWN_ORIENT:
			x += CommonVL.TANK_SIZE / 2 - CommonVL.BULLET_SIZE / 2;
			y += CommonVL.TANK_SIZE;
			break;
		case CommonVL.LEFT_ORIENT:
			y += CommonVL.TANK_SIZE / 2 - CommonVL.BULLET_SIZE / 2;
			x -= CommonVL.BULLET_SIZE;
			break;
		case CommonVL.RIGH_ORIENT:
			y += CommonVL.TANK_SIZE / 2 - CommonVL.BULLET_SIZE / 2;
			x += CommonVL.TANK_SIZE;
			break;
		}
	}

	//xét khi đạn di chuyển, nếu không có va trạm thì đạn tiếp tục di chuyển.
	public boolean move(TankPlayer tankPlayer, BulletMgr bulletMgr, TankBossMgr tankBossMgr, MapMgr mapMgr, Graphics2D g) {
		switch (orient) {
		case CommonVL.UP_ORIENT:
			if (y > 0) {
				if (!checkHit(tankPlayer, bulletMgr, tankBossMgr, mapMgr,g))
					return false;
				y -= speed;
				return true;
			}
			break;
		case CommonVL.DOWN_ORIENT:
			if (y < CommonVL.MAP_SIZE - CommonVL.BULLET_SIZE) {
				if (!checkHit(tankPlayer, bulletMgr, tankBossMgr, mapMgr,g))
					return false;
				y += speed;
				return true;
			}
			break;
		case CommonVL.LEFT_ORIENT:
			if (x > 0) {
				if (!checkHit(tankPlayer, bulletMgr, tankBossMgr, mapMgr,g))
					return false;
				x -= speed;
				return true;
			}
			break;
		case CommonVL.RIGH_ORIENT:
			if (x < CommonVL.MAP_SIZE - CommonVL.BULLET_SIZE) {
				if (!checkHit(tankPlayer, bulletMgr, tankBossMgr, mapMgr,g))
					return false;
				x += speed;
				return true;
			}
			break;
		}
		return false;
	}

//	kiểm tra đạn có va trạm vào tank, vào đạn địch, vào các phần tử trên map, đối tượng Graphics2D để xóa các đối tượng khi có va trạm
	private boolean checkHit(TankPlayer tankPlayer, BulletMgr bulletMgr, TankBossMgr tankMgr, MapMgr mapMgr, Graphics2D g) {
		if (this.getId()==CommonVL.BOSS_ID && getRectangle().intersects(tankPlayer.getRectangle())) {
			EffectionMgr.addEffection(new Effection(ImageMgr.arrBumImages, tankPlayer.getX(), tankPlayer.getY()));
			SoundMgr.play(SoundMgr.bum_tank);
//			tankPlayer.setRuning(false);
			if(MapPanel.numPlayer-->0){
				tankPlayer.setNewTank();
			}
			return false;
		}
		
		//kiểm tra đạn va trạm với tankBoss, tankBoss có 3 mức. 
		if(this.getId()==CommonVL.TANK_ID)
			for (int i = tankMgr.getArrTankBoss().size() - 1; i >= 0; i--) {
				TankBoss tank = tankMgr.getArrTankBoss().get(i);
				if (getRectangle().intersects(tank.getRectangle())) {
					if(tank.getTankLevel()<3){
						EffectionMgr.addEffection(new Effection(ImageMgr.arrBumImages, tank.getX(), tank.getY()));
						SoundMgr.play(SoundMgr.bum_tank);
						tankMgr.removeTank(tank);
					}else{
						if(tank.getTankHP()==3){
							SoundMgr.play(SoundMgr.bum);
							tank.setTankHP(tank.getTankHP()-1);
							tank.setArrImages(ImageMgr.arrBoss32Images);
						}else if(tank.getTankHP()==2){
							SoundMgr.play(SoundMgr.bum);
							tank.setTankHP(tank.getTankHP()-1);
							tank.setArrImages(ImageMgr.arrBoss33Images);
						}else{
							EffectionMgr.addEffection(new Effection(ImageMgr.arrBumImages, tank.getX(), tank.getY()));
							SoundMgr.play(SoundMgr.bum_tank);
							
							tankMgr.removeTank(tank);
						}
					}
					return false;
				}
			}
		
		//Kiểm tra 2 loại đạn có ID khác nhau va trạm nhau
		for (int i = bulletMgr.getArrBullet().size() - 1; i >= 0; i--) {
			Bullet bullet = bulletMgr.getArrBullet().get(i);
			if (this.getId()==CommonVL.TANK_ID && bullet.getId()==CommonVL.BOSS_ID && getRectangle().intersects(bullet.getRectangle())) {
				SoundMgr.play(SoundMgr.bum);
				bulletMgr.removeBullet(bullet);
				bulletMgr.removeBullet(this);
				return false;
			}
		}
		
		//biến kt để khi đạn có thể bắn nổ cả 2 viên ghạch nếu cả 2 viên cùng va chạm với  đạn
		boolean kt = true;
		for (int i = mapMgr.getArrUnits().size() - 1; i >= 0; i--) {
			Unit unit = mapMgr.getArrUnits().get(i);
			if (getRectangle().intersects(unit.getRectangle()) && (!unit.allowBulletPass())) {
				SoundMgr.play(SoundMgr.bum);
				if (unit.getType() == 1)
					mapMgr.removeUnit(unit);
				kt = false;
			}
		}
		return kt;
	}

}
