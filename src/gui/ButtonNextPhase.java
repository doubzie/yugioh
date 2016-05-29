package gui;

import java.awt.Cursor;

import javax.swing.JButton;

public class ButtonNextPhase extends JButton{

	public ButtonNextPhase() {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public ButtonNextPhase(String string) {
		super(string);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
