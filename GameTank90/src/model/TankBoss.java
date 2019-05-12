package model;

import map.MapMgr;
import map.Unit;
import other.CommonVL;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TankBoss extends CShape {

	//tankLevel1 - đi bt, bắn 1 phát nổ; 
	//tankLevel2 - đi nhanh, bắn 1 phát nổ
	//tankLevel3 - đi bt, HP bằng 3, mỗi khi trúng đạn thì HP giảm 1. HP==0 thì tank nổ.
	private int tankLevel;
	private int tankHP=3;
	
	public TankBoss(int id, int x, int y, int size, int orient, int speed, ArrayList<Image> arrImages, int tankLevel) {
		super(id, x, y, size, orient, speed, arrImages);
		this.tankLevel=tankLevel;
		this.tankHP=3;
	}

	//biến enjoy để xác định nếu tank ở nửa phía bên phải map thì sẽ có xu hướng đi về trái nhiều hơn, 
	//tương tự với khi tankBoss ở bên phải thì có xu hướng đi về trái nhiều hơn.
	public void autoMove(MapMgr mapMgr, TankPlayer tankPlayer, int enjoyOrient) {
		Random rd=new Random();
		ArrayList<Integer> arrOrient=new ArrayList<Integer>();
		arrOrient.add(CommonVL.UP_ORIENT);
		for (int i = 0; i < 2; i++) {
			arrOrient.add(CommonVL.UP_ORIENT);
			arrOrient.add(CommonVL.DOWN_ORIENT);
			arrOrient.add(CommonVL.LEFT_ORIENT);
			arrOrient.add(CommonVL.RIGH_ORIENT);
			if(enjoyOrient==CommonVL.LEFT_ORIENT)
				arrOrient.add(CommonVL.LEFT_ORIENT);
			else
				arrOrient.add(CommonVL.RIGH_ORIENT);
		}
		//dùng random để không phải cứ khi nào đi hết đường thì tank địch mới được chuyển hướng.
		if (!move(mapMgr,tankPlayer)||rd.nextInt(100)>98) {
			int index=rd.nextInt(arrOrient.size());
			changeOrient(arrOrient.get(index));
		}
	}
	
	
	public Bullet autoFire(){
		return fireBullet(CommonVL.BOSS_ID);
	}

	public boolean move(MapMgr mapMgr, TankPlayer tankPlayer) {
		int xt=x,yt=y;
		CShape newShape=new CShape(xt,yt,size);
		//tùy theo hướng, giả sử đối tượng có thể di chuyển đến vị trí mới được thay đổi tọa độ như bên dưới.
		switch (orient) {
		case CommonVL.UP_ORIENT:
			if(y>0)
				yt-=speed;
			break;
		case CommonVL.DOWN_ORIENT:
			if (y < CommonVL.MAP_SIZE - CommonVL.TANK_SIZE)
				yt+=speed;
			break;
		case CommonVL.LEFT_ORIENT:
			if (x > 0)
				xt-=speed;
			break;
		case CommonVL.RIGH_ORIENT:
			if (x < CommonVL.MAP_SIZE - CommonVL.TANK_SIZE)
				xt+=speed;
			break;
		}
		//nếu vị trí không hề thay đổi tức là vị trí mới sẽ đi ra ngoài biên của map thì return false.
		if(xt==x && yt==y)
			return false;
		
		//xet va chạm với tankPlayer
		if(newShape.getRectangle().intersects(tankPlayer.getRectangle()))
			return false;
		
		//nếu vị trí mới mà có va trạm vào các đối tượng trên map thì return false.
		for (Unit unit : mapMgr.getArrUnits()) {
			if (newShape.getRectangle().intersects(unit.getRectangle()) && (!unit.allowTankPass()))
				return false;
		}
		
		//nếu tank không vị cản thì đặt vị trí của nó bằng vị trí mới đã giả định.
		x=xt;
		y=yt;
		return true;
	}

	public int getTankLevel() {
		return tankLevel;
	}

	public int getTankHP() {
		return tankHP;
	}

	public void setTankHP(int tankHP) {
		this.tankHP = tankHP;
	}

}
