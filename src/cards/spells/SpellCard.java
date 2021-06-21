package cards.spells;

import cards.Card;
import cards.MonsterCard;

public abstract class SpellCard extends Card {

  public SpellCard(String name, String description) {
    super(name, description);
  }

  @Override
  public String toString() {
    return super.toString() + ", " + getDescription() + "\n";
  }

  @Override
  public abstract void action(MonsterCard monster);
}
