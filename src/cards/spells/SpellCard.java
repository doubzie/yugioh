package cards.spells;

import cards.Card;
import cards.Location;
import cards.MonsterCard;

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
