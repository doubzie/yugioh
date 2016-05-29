package gui;

import java.awt.Cursor;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonQuitGame extends JButton {

	public ButtonQuitGame() {
		super();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public ButtonQuitGame(String string) {
		super(string);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
