package other;

import java.awt.*;
import java.util.ArrayList;

public class EffectionMgr {
	
	public static ArrayList<Effection> arrEffections;
	
	public EffectionMgr() {
		arrEffections=new ArrayList<Effection>();
	}
	
	public static void addEffection(Effection eff){
		arrEffections.add(eff);
	}
	
	//tạo hiệu ứng khi tank nổ
	public void displayEffection(Graphics2D g){
		for (int i = arrEffections.size()-1; i >= 0; i--) {
			if(!arrEffections.get(i).draw(g))
				arrEffections.remove(i);
		}
	}
}
