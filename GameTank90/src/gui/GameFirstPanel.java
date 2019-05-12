package gui;

import other.CommonVL;
import other.ImageMgr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;


public class GameFirstPanel extends JPanel{
	private JButton jButtonExit,jButtonHelp,jButtonLogin;

	private static final long serialVersionUID = 1L;
	private GUI gui;


	public GameFirstPanel() {
		setLayout(null);
		setFocusable(true);
		addKeyListener(adapter);
		initComps();
		addComps();

	}

	private void addComps() {
		add(jButtonExit);
		add(jButtonHelp);
		add(jButtonLogin);

	}

	private void initComps() {
		Font font = new Font("Time News Roman", Font.BOLD,24);
		jButtonExit = new JButton();
		jButtonExit.setFont(font);
		jButtonExit.setText("THOÁT GAME");
		jButtonExit.setForeground(Color.DARK_GRAY);
		jButtonExit.setBackground(Color.YELLOW);
		jButtonExit.setBounds(650,400,200,50);

		jButtonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		jButtonHelp = new JButton();
		jButtonHelp.setFont(font);
		jButtonHelp.setText("HƯỚNG DẪN");
		jButtonHelp.setForeground(Color.BLUE);
		jButtonHelp.setBounds(650,480,200,50);

		jButtonLogin = new JButton();
		jButtonLogin.setFont(font);
		jButtonLogin.setText("ĐĂNG NHẬP");
		jButtonLogin.setForeground(Color.BLUE);
		jButtonLogin.setBounds(650, 560, 200, 50);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ImageMgr.imageStart,0,0, CommonVL.WIDTH_FRAME,CommonVL.HEIGHT_FRAME,null);
	}

	KeyAdapter adapter=new KeyAdapter() {
		public void keyPressed(java.awt.event.KeyEvent e) {
			gui.getPlayPanel().startGame();
			setFocusable(false);
			setVisible(false);
		};
	};

	public void setGui(GUI gui) {
		this.gui = gui;
	}
}
