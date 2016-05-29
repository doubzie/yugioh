package eg.edu.guc.yugioh.gui;

import java.awt.Cursor;

import javax.swing.JButton;

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
