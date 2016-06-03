package listeners;

import board.Board;
import gui.GUI;

public class Main {

	public Main() {
		Board board = new Board();
		GUI gui = new GUI();
		new Controller(board, gui);
	}

	public static void main(String[] args) {
		new Main();
	}

}
