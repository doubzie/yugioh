package gui;

import java.awt.Cursor;

import javax.swing.JButton;

public class ButtonPlayAgain extends JButton {
	
	public ButtonPlayAgain() {
		super();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public ButtonPlayAgain(String string) {
		super(string);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
