package gui;

import other.CommonVL;
import other.ImageMgr;
import other.SoundMgr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private GameFirstPanel firstPanel;
	private GamePlayPanel playPanel;
	public static boolean IS_STOP=false;
	public GUI() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new CardLayout());
		setUndecorated(true);
		init();
		addWindowListener(adapter);
	}
	
	private void init() {
		new CommonVL();
		new ImageMgr();
		new SoundMgr();
		
		firstPanel=new GameFirstPanel();
		firstPanel.setGui(this);
		playPanel = new GamePlayPanel();

		add(firstPanel);
		add(playPanel);

		UIManager.LookAndFeelInfo infor[] = UIManager.getInstalledLookAndFeels();
		//----- lam dep giao dien
		for (int i = 0; i < infor.length; i++) {
			//System.out.println(infor[i].getClassName());
		}

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
	}
	
	WindowAdapter adapter=new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			IS_STOP=true;
			System.exit(0);
		}
	};
	
	public GameFirstPanel getFirstPanel() {
		return firstPanel;
	}
	
	public GamePlayPanel getPlayPanel() {
		return playPanel;
	}
	
}