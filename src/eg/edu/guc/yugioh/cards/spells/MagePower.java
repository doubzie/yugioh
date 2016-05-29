package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard {

	public MagePower(String name, String description) {
		super(name, description);
	}

	public void action(MonsterCard monster){
		
		int numberOfSpellCards = getBoard().getActivePlayer().getField().getSpellArea().size();
		
		for (int i = 0; i < numberOfSpellCards ; i++) {
			
			monster.setAttackPoints(monster.getAttackPoints() + 500);
			monster.setDefensePoints(monster.getDefensePoints() + 500);
			
		}
		
	}
	
}
