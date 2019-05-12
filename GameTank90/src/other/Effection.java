package other;

import java.awt.*;
import java.util.ArrayList;

public class Effection{
	private ArrayList<Image> arrImages;
	private int x;
	private int y;
	public int index=0;
	
	public Effection(ArrayList<Image> arrImages, int x, int y) {
		this.arrImages=arrImages;
		this.x=x;
		this.y=y;
	}
	public boolean draw(Graphics2D g){
		if(index<arrImages.size()){
			g.drawImage(arrImages.get(index++), x-CommonVL.UNIT_SIZE, y-CommonVL.UNIT_SIZE, null);
			return true;
		}
		return false;
	}
}