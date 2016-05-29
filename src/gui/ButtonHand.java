package gui;

import java.awt.Cursor;

import javax.swing.JButton;

public class ButtonHand extends JButton{
	
	public ButtonHand() {
		super();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public ButtonHand(String label) {
		super(label);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
}
