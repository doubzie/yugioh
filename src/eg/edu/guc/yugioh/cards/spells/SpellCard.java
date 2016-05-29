package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public abstract class SpellCard extends Card {

	public SpellCard(String name, String description) {
		super(name, description);
	}
	
	public String toString(){
		return super.toString() + ", " + getDescription() + "\n";
	}
	
	@Override
	abstract public void action(MonsterCard monster);
	

}
