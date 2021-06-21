package cards.spells;

import cards.MonsterCard;

public class ChangeOfHeart extends SpellCard {

  public ChangeOfHeart(String name, String description) {
    super(name, description);
  }

  @Override
  public void action(MonsterCard monster) {
    if (getBoard().getActivePlayer().getField().getMonstersArea().size() < 5) {
      getBoard()
        .getOpponentPlayer()
        .getField()
        .getMonstersArea()
        .remove(monster);

      monster.setHidden(false);
      monster.setHasAttacked(false);
      monster.setModeSwitched(false);

      getBoard().getActivePlayer().getField().getMonstersArea().add(monster);
    }
  }
}
