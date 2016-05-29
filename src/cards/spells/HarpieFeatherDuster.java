package cards.spells;

import cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard {

	public HarpieFeatherDuster(String name, String description) {
		super(name, description);
	}
	
	public void action(MonsterCard monster){
	
		getBoard().getOpponentPlayer().getField().removeSpellToGraveyard(getBoard().getOpponentPlayer().getField().getSpellArea());

	}
	

}
