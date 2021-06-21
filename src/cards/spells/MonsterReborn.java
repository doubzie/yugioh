package cards.spells;

import cards.Location;
import cards.MonsterCard;
import exceptions.NoMonsterSpaceException;

public class MonsterReborn extends SpellCard {

  public MonsterReborn(String name, String description) {
    super(name, description);
  }

  @Override
  public void action(MonsterCard monster) {
    if (
      getBoard().getActivePlayer().getField().getMonstersArea().size() > 4
    ) throw new NoMonsterSpaceException();

    int maxIndexActive = -1;
    int maxIndexOpponent = -1;

    int maxPointsActive = 0;
    int maxPointsOpponent = 0;

    if (
      !getBoard().getActivePlayer().getField().getGraveyard().isEmpty() ||
      !getBoard().getOpponentPlayer().getField().getGraveyard().isEmpty()
    ) {
      for (
        int i = 0;
        i < getBoard().getActivePlayer().getField().getGraveyard().size();
        i++
      ) {
        MonsterCard monsterTemp = null;

        if (
          getBoard()
            .getActivePlayer()
            .getField()
            .getGraveyard()
            .get(i) instanceof MonsterCard
        ) monsterTemp =
          (MonsterCard) getBoard()
            .getActivePlayer()
            .getField()
            .getGraveyard()
            .get(i); else continue;

        if (monsterTemp.getAttackPoints() > maxPointsActive) {
          maxPointsActive = monsterTemp.getAttackPoints();
          maxIndexActive = i;
        }
      }

      for (
        int i = 0;
        i < getBoard().getOpponentPlayer().getField().getGraveyard().size();
        i++
      ) {
        MonsterCard monsterTemp = null;

        if (
          getBoard()
            .getOpponentPlayer()
            .getField()
            .getGraveyard()
            .get(i) instanceof MonsterCard
        ) monsterTemp =
          (MonsterCard) getBoard()
            .getOpponentPlayer()
            .getField()
            .getGraveyard()
            .get(i); else continue;

        if (monsterTemp.getAttackPoints() > maxPointsOpponent) {
          maxPointsOpponent = monsterTemp.getAttackPoints();
          maxIndexOpponent = i;
        }
      }

      MonsterCard maxMonsterActive = null;
      MonsterCard maxMonsterOpponent = null;

      try {
        maxMonsterActive =
          (MonsterCard) getBoard()
            .getActivePlayer()
            .getField()
            .getGraveyard()
            .get(maxIndexActive);
      } catch (ArrayIndexOutOfBoundsException e) {
        maxMonsterActive =
          new MonsterCard("Dummy Monster", "Does nothing", 0, 0, 0);
      }

      try {
        maxMonsterOpponent =
          (MonsterCard) getBoard()
            .getOpponentPlayer()
            .getField()
            .getGraveyard()
            .get(maxIndexOpponent);
      } catch (ArrayIndexOutOfBoundsException e) {
        maxMonsterOpponent =
          new MonsterCard("Dummy Monster", "Does nothing", 0, 0, 0);
      }

      if (
        maxMonsterActive.getAttackPoints() >
        maxMonsterOpponent.getAttackPoints()
      ) {
        getBoard()
          .getActivePlayer()
          .getField()
          .getGraveyard()
          .remove(maxMonsterActive);

        maxMonsterActive.setHasAttacked(false);
        maxMonsterActive.setModeSwitched(false);
        maxMonsterActive.setLocation(Location.FIELD);

        getBoard()
          .getActivePlayer()
          .getField()
          .getMonstersArea()
          .add(maxMonsterActive);
      } else {
        getBoard()
          .getOpponentPlayer()
          .getField()
          .getGraveyard()
          .remove(maxMonsterOpponent);

        maxMonsterOpponent.setHasAttacked(false);
        maxMonsterOpponent.setModeSwitched(false);
        maxMonsterOpponent.setLocation(Location.FIELD);

        getBoard()
          .getActivePlayer()
          .getField()
          .getMonstersArea()
          .add(maxMonsterOpponent);
      }
    }
  }
}
