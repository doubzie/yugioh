package gui;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonDeck extends JButton {
	
	public ButtonDeck() {
		super();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public ButtonDeck(String string) {
		super(string);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
