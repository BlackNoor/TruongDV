package model;

import map.MapMgr;

import java.awt.*;
import java.util.ArrayList;


public class BulletMgr {

	private ArrayList<Bullet> arrBullet;

	public BulletMgr() {
		arrBullet = new ArrayList<Bullet>();
	}

	public void addBullet(Bullet fireBullet) {
		arrBullet.add(fireBullet);
	}

	public ArrayList<Bullet> getArrBullet() {
		return arrBullet;
	}

	public void drawAllBullet(Graphics2D g) {
		for (int i = arrBullet.size() - 1; i >= 0; i--) {
			try {
				if (arrBullet.get(i).isRuning())
					arrBullet.get(i).drawCShape(g);
			} catch (Exception e) {
			}
		}
	}

	public void moveAllBullet(TankPlayer tankPlayer, BulletMgr bulletMgr, TankBossMgr tankMgr, MapMgr mapMgr, Graphics2D g) {
		for (int i = arrBullet.size() - 1; i >= 0; i--) {
			try {
				if (!arrBullet.get(i).move(tankPlayer, bulletMgr, tankMgr, mapMgr, g)){
					arrBullet.remove(i);
				}
			} catch (Exception e) {
			}
		}
	}

	public void removeBullet(Bullet bullet) {
		arrBullet.remove(bullet);
	}
}
