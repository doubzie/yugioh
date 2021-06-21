package gui;

import java.awt.Cursor;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonEndTurn extends JButton {

  public ButtonEndTurn() {
    setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  public ButtonEndTurn(String string) {
    super(string);
    setCursor(new Cursor(Cursor.HAND_CURSOR));
  }
}
