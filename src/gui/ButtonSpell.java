package gui;

import java.awt.Cursor;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonSpell extends JButton {

	public ButtonSpell() {
		super();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public ButtonSpell(String label) {
		super(label);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
