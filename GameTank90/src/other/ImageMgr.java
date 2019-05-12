package other;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageMgr {
	
	public static ArrayList<Image> arrUnitImages;
	public static ArrayList<Image> arrPlayerImages;
	public static ArrayList<Image> arrBoss1Images;
	public static ArrayList<Image> arrBoss2Images;
	public static ArrayList<Image> arrBoss31Images;
	public static ArrayList<Image> arrBoss32Images;
	public static ArrayList<Image> arrBoss33Images;
	public static ArrayList<Image> arrBulletImages;
	public static ArrayList<Image> arrBumImages;
	public static ArrayList<Image> arrNumImages;
	public static ArrayList<Image> arrBirdImages;
	public static Image imageStart;
	public static Image imageIconBoss;
	public static Image imageLeft;
	public static Image imageRight;
	
	
	//khởi tạo ảnh xử dụng trong chương trình
	public ImageMgr() {
		arrUnitImages=getImage("unit", CommonVL.UNIT_SIZE, CommonVL.UNIT_SIZE, 6);
		arrPlayerImages=getImage("player", CommonVL.TANK_SIZE, CommonVL.TANK_SIZE, 4);
		arrBoss1Images=getImage("boss1", CommonVL.TANK_SIZE, CommonVL.TANK_SIZE, 4);
		arrBoss2Images=getImage("boss2", CommonVL.TANK_SIZE, CommonVL.TANK_SIZE, 4);
		arrBoss31Images=getImage("boss31", CommonVL.TANK_SIZE, CommonVL.TANK_SIZE, 4);
		arrBoss32Images=getImage("boss32", CommonVL.TANK_SIZE, CommonVL.TANK_SIZE, 4);
		arrBoss33Images=getImage("boss33", CommonVL.TANK_SIZE, CommonVL.TANK_SIZE, 4);
		arrBulletImages=getImage("bullet", CommonVL.BULLET_SIZE, CommonVL.BULLET_SIZE, 4);
		arrBumImages=getImage("bum", CommonVL.TANK_SIZE*2, CommonVL.TANK_SIZE*2, 9);
		arrNumImages=getImage("num_level", 16, 16, 10);
		arrBirdImages=getImage("bird", CommonVL.TANK_SIZE, CommonVL.TANK_SIZE, 2);
		imageStart=new ImageIcon(getClass().getResource("/IMAGES/bgb.jpg")).getImage();
		imageIconBoss=new ImageIcon(getClass().getResource("/IMAGES/icon_boss.png")).getImage();
		imageLeft=new ImageIcon(getClass().getResource("/IMAGES/left.png")).getImage();
		imageRight=new ImageIcon(getClass().getResource("/IMAGES/right.png")).getImage();
	}
	
	//đọc 1 ảnh lớn, sau đó cắt đưa vào 1 arrayList
	public ArrayList<Image> getImage(String imgName, int w, int h, int num) {
		ArrayList<Image> arrImg = new ArrayList<Image>();
		try {
			BufferedImage bfi = ImageIO.read(getClass().getResourceAsStream("/IMAGES/" + imgName + ".png"));
			for (int i = 0; i < num; i++) {
				BufferedImage b = bfi.getSubimage(0, i*h, w, h);
				arrImg.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrImg;
	}
	
}