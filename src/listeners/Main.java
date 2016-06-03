package listeners;

import java.io.IOException;

import board.Board;
import gui.GUI;

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
