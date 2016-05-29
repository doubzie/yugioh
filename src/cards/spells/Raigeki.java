package cards.spells;

import cards.MonsterCard;

public class Raigeki extends SpellCard {

	public Raigeki(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {

		getBoard().getOpponentPlayer().getField()
				.removeMonsterToGraveyard(getBoard().getOpponentPlayer().getField().getMonstersArea());

	}

}
