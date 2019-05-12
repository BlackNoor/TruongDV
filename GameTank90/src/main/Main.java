package main;


import gui.GUI;

public class Main {
	public static void main(String[] args) {
		try {
			GUI gui = new GUI();
			gui.setVisible(true);	
		} catch (Exception e) {
		}
	}
}
