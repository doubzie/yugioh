package listeners;

import board.Board;
import gui.GUI;
import java.io.IOException;

public class Main {

  public Main() throws IOException {
    Board board = new Board();
    GUI gui = new GUI();
    new Controller(board, gui);
  }

  public static void main(String[] args) throws IOException {
    new Main();
  }
}
