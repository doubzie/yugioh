package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard {

	public Raigeki(String name, String description) {
		super(name, description);
	}
	
	public void action(MonsterCard monster){
		
		getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(	getBoard().getOpponentPlayer().getField().getMonstersArea());
	
	}

}
