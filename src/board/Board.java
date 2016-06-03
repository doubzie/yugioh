package board;

import board.player.Phase;
import board.player.Player;
import cards.Card;

public class Board {

	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;

	private boolean isMonsterAdded = false;

	public Board() {
		Card.setBoard(this);
	}

	public void whoStarts(Player p1, Player p2) {

		int whoStarts = (int) (Math.ceil(Math.random() * 2));

		if (whoStarts == 1) {
			activePlayer = p1;
			opponentPlayer = p2;
		} else {
			activePlayer = p2;
			opponentPlayer = p1;
		}

	}

	public void startGame(Player p1, Player p2) {

		p1.getField().addNCardsToHand(5);
		p2.getField().addNCardsToHand(5);
		whoStarts(p1, p2);
		activePlayer.getField().addCardToHand();

	}

	public void nextPlayer() {

		if (winner == null) {

			for (int i = 0; i < activePlayer.getField().getMonstersArea().size(); i++)
				if (activePlayer.getField().getMonstersArea().get(i) != null)
					activePlayer.getField().getMonstersArea().get(i).setHasAttacked(false);

			for (int i = 0; i < activePlayer.getField().getMonstersArea().size(); i++)
				if (activePlayer.getField().getMonstersArea().get(i) != null)
					activePlayer.getField().getMonstersArea().get(i).setModeSwitched(false);

			Player tmp = activePlayer;
			activePlayer = opponentPlayer;
			opponentPlayer = tmp;

			isMonsterAdded = false;

			activePlayer.getField().setPhase(Phase.MAIN1);
			activePlayer.getField().addCardToHand();

		}

	}

	public void assignWinner() {

		if (activePlayer.getLifePoints() <= 0)
			winner = opponentPlayer;

		if (opponentPlayer.getLifePoints() <= 0)
			winner = activePlayer;

	}

	public void endGame() {

		@SuppressWarnings("unused")
		Player loser;
		if (winner == activePlayer)
			loser = opponentPlayer;
		else
			loser = activePlayer;

	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public Player getOpponentPlayer() {
		return opponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
		endGame();
	}

	public boolean isMonsterAdded() {
		return isMonsterAdded;
	}

	public void setMonsterAdded(boolean isMonsterAdded) {
		this.isMonsterAdded = isMonsterAdded;
	}

}
