package gui;

import java.awt.Cursor;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonMonster extends JButton {

	public ButtonMonster() {
		super();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public ButtonMonster(String label) {
		super(label);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
