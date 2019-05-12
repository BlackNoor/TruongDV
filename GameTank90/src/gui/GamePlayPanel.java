package gui;

import other.CommonVL;
import other.ImageMgr;

import javax.swing.*;
import java.awt.*;


public class GamePlayPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MapPanel mapPanel;

	public GamePlayPanel() {
		setLayout(null);
		setBackground(Color.GRAY);
	}

	public void startGame() {
		mapPanel = new MapPanel();
		mapPanel.setgamePlayPanel(this);
		add(mapPanel);
		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		drawInfor(g);
	}

	// vẽ ảnh bên trái, bên phải và thông tin level, số mạng tank, số boss còn lại.
	public void drawInfor(Graphics2D g) {		
		g.drawImage(ImageMgr.imageLeft, CommonVL.PADDING_LEFT, CommonVL.PADDING_TOP, CommonVL.LEFT_RIGHT_SIZE, CommonVL.MAP_SIZE, null);
		g.drawImage(ImageMgr.imageRight, CommonVL.RIGHT_START_X, CommonVL.PADDING_TOP, CommonVL.LEFT_RIGHT_SIZE, CommonVL.MAP_SIZE, null);
		g.drawImage(ImageMgr.arrNumImages.get(MapPanel.numPlayer), CommonVL.RIGHT_START_X + 125, CommonVL.PADDING_TOP + 500, 30, 30, null);
		g.drawImage(ImageMgr.arrNumImages.get(MapPanel.level), CommonVL.RIGHT_START_X + 125, CommonVL.PADDING_TOP + 650, 35, 35, null);

		for (int i = 0; i < MapPanel.numBoss/3; i++) {
			g.drawImage(ImageMgr.imageIconBoss, CommonVL.RIGHT_START_X + 50,
					CommonVL.PADDING_TOP + 50 * i + 30, 150, 50, null);
		}
	}

	public MapPanel getMapPanel() {
		return mapPanel;
	}
	
	public void setNumPlayer() {
		repaint();
	}
	
}
