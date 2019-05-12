package map;

import other.ImageMgr;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MapMgr {

	private ArrayList<Unit> arrUnits;

	public MapMgr(int level) {
		arrUnits = new ArrayList<>();
		setArrUnits("src/MAPS/map" + level);
	}

	public ArrayList<Unit> getArrUnits() {
		return arrUnits;
	}

	// Đọc map sau đó lưu vào arrUnits để vẽ và xử lý
	@SuppressWarnings("resource")
	public void setArrUnits(String path) {
		try {
			File file = new File(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF8"));
			String content;
			int j = 0;
			while ((content = in.readLine()) != null) {
				for (int i = 0; i < content.length(); i++) {
					int index = Integer.parseInt(content.charAt(i) + "");
					if (index > 0)
						arrUnits.add(new Unit(i, j, index, ImageMgr.arrUnitImages.get(index)));
				}
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Bao gồm đối tượng băng vẽ trước tank, cho phép tank đi đè lên trên.
	public void drawBottomUnit(Graphics2D g) {
		for (int i = arrUnits.size() - 1; i >= 0; i--) {
			try {
				if (arrUnits.get(i).getType() != 3)
					arrUnits.get(i).drawUnit(g);
			} catch (Exception e) {
			}
		}
	}

	// Cây cho tank đi qua nhưng phải vẽ sau tank, đè lên tank.
	public void drawTopUnit(Graphics2D g) {
		for (int i = arrUnits.size() - 1; i >= 0; i--) {
			if (arrUnits.get(i).getType() == 3)
				arrUnits.get(i).drawUnit(g);
		}
	}

	// xóa 1 đối tượng unit - khi đạn chạm vào gạch.
	public void removeUnit(Unit unit) {
		arrUnits.remove(unit);
	}

}
