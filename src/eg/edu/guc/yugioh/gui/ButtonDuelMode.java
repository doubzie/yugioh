package eg.edu.guc.yugioh.gui;

import java.awt.Cursor;

import javax.swing.JButton;

public class ButtonDuelMode extends JButton {
	
	public ButtonDuelMode() {
		super();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public ButtonDuelMode(String string) {
		super(string);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
}
