package eg.edu.guc.yugioh.listeners;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.gui.*;


public class Main {
	
	public Main(){
		
		Board board = new Board();
		GUI gui = new GUI();
		new Controller(board, gui);
		
	}
	
	public static void main(String[] args) {
		
		Main main = new Main();
		
	}
	
}
