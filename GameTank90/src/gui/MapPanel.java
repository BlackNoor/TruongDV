package gui;

import map.Bird;
import map.MapMgr;
import model.BulletMgr;
import model.TankBossMgr;
import model.TankPlayer;
import other.CommonVL;
import other.EffectionMgr;
import other.ImageMgr;
import other.SoundMgr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

public class MapPanel extends JPanel implements KeyListener, Runnable{

	private static final long serialVersionUID = 1L;
	private GamePlayPanel gamePlayPanel;
	private MapMgr mapMgr;
	private Bird bird;
	private TankPlayer tankPlayer;
	private TankBossMgr tankBossMgr;
	private BulletMgr bulletMgr;
	private EffectionMgr effectionMgr;
	private boolean isPause = false;
	public static int statusGame=0;
	public static int level=1;
	public static int numPlayer=3;
	public static int numBoss=5;
	private int count;
	private Graphics2D g;
	private BitSet bitset;
	
	public MapPanel() {
		setLayout(null);
		setBounds((CommonVL.WIDTH_FRAME - CommonVL.MAP_SIZE) / 2,CommonVL.PADDING_TOP, CommonVL.MAP_SIZE, CommonVL.MAP_SIZE);
		setBackground(Color.BLACK);
		setVisible(true);
		setFocusable(true);
		setupGame();
		new Thread(this).start();
	}

	private void setupGame() {
		isPause=false;
		statusGame=0;
		numPlayer=3;
		numBoss=12;
		mapMgr = new MapMgr(level);		
		bird = new Bird();
		tankPlayer = new TankPlayer(CommonVL.TANK_ID, 8 * CommonVL.UNIT_SIZE, 24 * CommonVL.UNIT_SIZE, CommonVL.TANK_SIZE, CommonVL.UP_ORIENT, CommonVL.STANDARD_SPEED / 2, ImageMgr.arrPlayerImages);
		tankBossMgr = new TankBossMgr();
		bulletMgr = new BulletMgr();
		effectionMgr=new EffectionMgr();
		bitset = new BitSet(256);
		bitset.clear();
		addKeyListener(this);
		SoundMgr.play(SoundMgr.enter_game);
		SoundMgr.play(SoundMgr.move);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		g = (Graphics2D) graphics;
		mapMgr.drawBottomUnit(g);
		bird.drawBird(g);
		tankPlayer.drawCShape(g);
		tankBossMgr.drawAllTankBoss(g);
		mapMgr.drawTopUnit(g);
		bulletMgr.drawAllBullet(g);
		effectionMgr.displayEffection(g);
	}

	@Override
	public void run() {
		while (!GUI.IS_STOP && Bird.IS_LIVE) {
			if (!isPause) {
				count++;
				if (count == 100000000)
					count = 0;
				if(tankBossMgr.addTeamTankBoss())
					gamePlayPanel.repaint();
				
				if (count % 60 == 0) {
					tankPlayer.setCanFire(true);
				}
				tankBossMgr.autoMoveAll(mapMgr, tankPlayer);
				if (count % 180 == 0) {
					tankBossMgr.autoFireAll(bulletMgr);
				}
				bulletMgr.moveAllBullet(tankPlayer, bulletMgr, tankBossMgr, mapMgr, g);
				checkGame();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			catchEvent();
			gamePlayPanel.repaint();
			repaint();
		}
	}

//	bắt sự kiện khi status game thay đổi.
	private void checkGame() {
		if (bird.checkGameOver(bulletMgr) || numPlayer==0) {
			SoundMgr.play(SoundMgr.level_completed);
			statusGame=-1;
		}else if(numBoss==0 && tankBossMgr.getArrTankBoss().size()==0){
			SoundMgr.play(SoundMgr.level_completed);
			statusGame=1;
		}
		if(statusGame==0)
			return;
		if(statusGame==1){
			if(level<9){
				JOptionPane.showMessageDialog(null, "YOU ARE WINER\n - Cllick OK To Next Lelel.....");
				level++;
				setupGame();
			}
		}else if(statusGame==-1){
			JOptionPane.showMessageDialog(null, "GAME OVER\n Click OK To Try It Again.....");
			setupGame();
		}
	}

	//Bắt sự kiện nhấn bàn phím
	private void catchEvent() {
		if (bitset.get(KeyEvent.VK_P)) {
			isPause = !isPause;
			SoundMgr.stopAll();
			// do sự kiện Press được bắt liên tục nên sleep 1 khoảng thời gian nhất định mới cho phép nhận phím SPACE và phím M
			try {
				Thread.sleep(140);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!isPause) {
			if(bitset.get(KeyEvent.VK_ESCAPE)){
				int option=JOptionPane.showConfirmDialog(null, "You Are Exiting Game...\n+ Press YES To Confirm.\n+ Press NO To Cancle","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(option==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
			if (bitset.get(KeyEvent.VK_M)){
				SoundMgr.canPlay = !SoundMgr.canPlay;
				SoundMgr.stopAll();
				try {
					Thread.sleep(240);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (bitset.get(KeyEvent.VK_UP)) {
				tankPlayer.changeOrient(CommonVL.UP_ORIENT);
				tankPlayer.move(mapMgr, tankBossMgr);
			}
			if (bitset.get(KeyEvent.VK_DOWN)) {
				tankPlayer.changeOrient(CommonVL.DOWN_ORIENT);
				tankPlayer.move(mapMgr, tankBossMgr);
			}
			if (bitset.get(KeyEvent.VK_LEFT)) {
				tankPlayer.changeOrient(CommonVL.LEFT_ORIENT);
				tankPlayer.move(mapMgr, tankBossMgr);
			}
			if (bitset.get(KeyEvent.VK_RIGHT)) {
				tankPlayer.changeOrient(CommonVL.RIGH_ORIENT);
				tankPlayer.move(mapMgr, tankBossMgr);
			}
			if (bitset.get(KeyEvent.VK_SPACE)){
				if (tankPlayer.isCanFire()) {
					bulletMgr.addBullet(tankPlayer.fireBullet(CommonVL.TANK_ID));
					tankPlayer.setCanFire(false);
				}
			}
			
		}
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		bitset.set(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		bitset.clear(e.getKeyCode());
	}
	

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public void setgamePlayPanel(GamePlayPanel gamePlayPanel) {
		this.gamePlayPanel = gamePlayPanel;
	}
}
