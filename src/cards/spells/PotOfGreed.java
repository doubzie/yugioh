package cards.spells;

import cards.MonsterCard;

public class PotOfGreed extends SpellCard {

	public PotOfGreed(String name, String description) {
		super(name, description);
	}
	
	public void action(MonsterCard monster){
		getBoard().getActivePlayer().getField().addNCardsToHand(2);
	}

}
