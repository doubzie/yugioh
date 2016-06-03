package gui;

import java.io.IOException;

public class GUI {

	private FirstInterface startWindow;
	private SecondInterface gameWindow;
	private ThirdInterface endWindow;
	private PlayerHand handPlayer1;
	private PlayerHand handPlayer2;

	public GUI() throws IOException {

		startWindow = new FirstInterface();
		gameWindow = new SecondInterface();
		endWindow = new ThirdInterface();

		handPlayer1 = new PlayerHand();
		handPlayer2 = new PlayerHand();

		startWindow.setVisible(true);

	}

	public FirstInterface getStartWindow() {
		return startWindow;
	}

	public SecondInterface getGameWindow() {
		return gameWindow;
	}

	public ThirdInterface getEndWindow() {
		return endWindow;
	}

	public PlayerHand getHandPlayer1() {
		return handPlayer1;
	}

	public PlayerHand getHandPlayer2() {
		return handPlayer2;
	}

}
