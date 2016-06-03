package cards.spells;

import java.util.ArrayList;

import cards.Card;
import cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster {

	public HeavyStorm(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {

		super.action(monster);

		ArrayList<SpellCard> spells = Card.getBoard().getActivePlayer().getField().getSpellArea();
		Card.getBoard().getActivePlayer().getField().removeSpellToGraveyard(new ArrayList<SpellCard>(spells));

	}

}
