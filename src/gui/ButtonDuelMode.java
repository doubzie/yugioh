package gui;

import java.awt.Cursor;
import javax.swing.JButton;

@SuppressWarnings("serial")
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
