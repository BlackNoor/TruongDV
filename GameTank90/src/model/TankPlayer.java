package model;

import map.MapMgr;
import map.Unit;
import other.CommonVL;

import java.awt.*;
import java.util.ArrayList;


public class TankPlayer extends CShape {

	public TankPlayer(int id, int x, int y, int size, int orient, int speed,
			ArrayList<Image> arrImages) {
		super(id, x, y, size, orient, speed, arrImages);
	}

	public void setNewTank() {
		x = 8 * CommonVL.UNIT_SIZE;
		y = 24 * CommonVL.UNIT_SIZE;
		orient = CommonVL.UP_ORIENT;
	}

	public boolean move(MapMgr mapMgr, TankBossMgr tankBossMgr) {
		int xt = x, yt = y;
		CShape newShape = new CShape(xt, yt, size);
		// tùy theo hướng, giả sử đối tượng có thể di chuyển đến vị trí mới được
		// thay đổi tọa độ như bên dưới.
		switch (orient) {
		case CommonVL.UP_ORIENT:
			if (y > 0)
				yt -= speed;
			break;
		case CommonVL.DOWN_ORIENT:
			if (y < CommonVL.MAP_SIZE - CommonVL.TANK_SIZE)
				yt += speed;
			break;
		case CommonVL.LEFT_ORIENT:
			if (x > 0)
				xt -= speed;
			break;
		case CommonVL.RIGH_ORIENT:
			if (x < CommonVL.MAP_SIZE - CommonVL.TANK_SIZE)
				xt += speed;
			break;
		}
		// nếu vị trí không hề thay đổi tức là vị trí mới sẽ đi ra bên ngoài map. return false.
		if (xt == x && yt == y)
			return false;

		// nếu vị trí mới mà có va trạm vào các đối tượng trên map thì return false.
		for (Unit unit : mapMgr.getArrUnits()) {
			if (newShape.getRectangle().intersects(unit.getRectangle())
					&& (!unit.allowTankPass()))
				return false;
		}

		// xét va chạm với arrTankBoss.
		for (TankBoss tankBoss : tankBossMgr.getArrTankBoss()) {
			if (newShape.getRectangle().intersects(tankBoss.getRectangle()))
				return false;
		}

		// nếu CShape không vị cản thì đặt vị trí của nó bằng vị trí mới đã giả định.
		x = xt;
		y = yt;
		return true;
	}
}
