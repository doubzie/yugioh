package eg.edu.guc.yugioh.gui;

import java.awt.Cursor;

import javax.swing.JButton;

public class ButtonEndTurn extends JButton{

	public ButtonEndTurn() {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public ButtonEndTurn(String string) {
		super(string);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
