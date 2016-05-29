package cards.spells;

import cards.MonsterCard;

public class CardDestruction extends SpellCard {

	public CardDestruction(String name, String description) {
		super(name, description);
	}
	
	public void action (MonsterCard monster){
		
		if(getBoard().getActivePlayer().getField().getDeck().getDeck().size() < getBoard().getActivePlayer().getField().getHand().size() && getBoard().getOpponentPlayer().getField().getDeck().getDeck().size() < getBoard().getOpponentPlayer().getField().getHand().size()){
			
			while(true){
				
				getBoard().getActivePlayer().getField().addCardToHand();
				getBoard().getOpponentPlayer().getField().addCardToHand();
				
				if(getBoard().getWinner() != null)
					break;
				
			}
			
		}
		
		else{
			
			int numberOfCards = getBoard().getActivePlayer().getField().getHand().size();
			
			if(numberOfCards!=0){
				getBoard().getActivePlayer().getField().removeCardToGraveyard(getBoard().getActivePlayer().getField().getHand());
				getBoard().getActivePlayer().addCardToHandHelper(numberOfCards);
			}
			
			if(getBoard().getWinner()==null){
				
				int numberOfCards2 = getBoard().getOpponentPlayer().getField().getHand().size();
				if(numberOfCards2 != 0)
					getBoard().getOpponentPlayer().getField().removeCardToGraveyard(getBoard().getOpponentPlayer().getField().getHand());
				
				getBoard().getOpponentPlayer().addCardToHandHelper(numberOfCards2);
			}
			
			
		}
		
	}
	
	
}


