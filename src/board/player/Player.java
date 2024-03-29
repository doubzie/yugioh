package board.player;

import cards.Card;
import cards.Location;
import cards.Mode;
import cards.MonsterCard;
import cards.spells.MagePower;
import cards.spells.MonsterReborn;
import cards.spells.SpellCard;
import exceptions.DefenseMonsterAttackException;
import exceptions.MonsterMultipleAttackException;
import exceptions.MultipleMonsterAdditionException;
import exceptions.UnexpectedFormatException;
import exceptions.WrongPhaseException;
import java.io.IOException;
import java.util.ArrayList;

public class Player implements Duelist {

  private String name;
  private int lifePoints;
  private Field field;

  public Player(String name) throws IOException, UnexpectedFormatException {
    this.name = name;
    lifePoints = 8000;
    field = new Field();
  }

  @Override
  public boolean summonMonster(MonsterCard monster) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) if (field.getPhase() == Phase.MAIN1 || field.getPhase() == Phase.MAIN2) {
      if (!Card.getBoard().isMonsterAdded()) {
        field.addMonsterToField(monster, Mode.ATTACK, false);
        if (
          Card
            .getBoard()
            .getActivePlayer()
            .getField()
            .getMonstersArea()
            .contains(monster)
        ) {
          Card.getBoard().setMonsterAdded(true);
          return true;
        }
      } else throw new MultipleMonsterAdditionException();
    } else throw new WrongPhaseException();

    return false;
  }

  @Override
  public boolean summonMonster(
    MonsterCard monster,
    ArrayList<MonsterCard> sacrifices
  ) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) if (field.getPhase() == Phase.MAIN1 || field.getPhase() == Phase.MAIN2) {
      if (!Card.getBoard().isMonsterAdded()) {
        field.addMonsterToField(monster, Mode.ATTACK, sacrifices);
        if (
          Card
            .getBoard()
            .getActivePlayer()
            .getField()
            .getMonstersArea()
            .contains(monster)
        ) {
          Card.getBoard().setMonsterAdded(true);
          return true;
        }
      } else throw new MultipleMonsterAdditionException();
    } else throw new WrongPhaseException();

    return false;
  }

  @Override
  public boolean setMonster(MonsterCard monster) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) if (field.getPhase() == Phase.MAIN1 || field.getPhase() == Phase.MAIN2) {
      if (!Card.getBoard().isMonsterAdded()) {
        field.addMonsterToField(monster, Mode.DEFENSE, true);
        if (
          Card
            .getBoard()
            .getActivePlayer()
            .getField()
            .getMonstersArea()
            .contains(monster)
        ) {
          Card.getBoard().setMonsterAdded(true);
          return true;
        }
      } else throw new MultipleMonsterAdditionException();
    } else throw new WrongPhaseException();

    return false;
  }

  @Override
  public boolean setMonster(
    MonsterCard monster,
    ArrayList<MonsterCard> sacrifices
  ) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) if (field.getPhase() == Phase.MAIN1 || field.getPhase() == Phase.MAIN2) {
      if (!Card.getBoard().isMonsterAdded()) {
        field.addMonsterToField(monster, Mode.DEFENSE, sacrifices);
        if (
          Card
            .getBoard()
            .getActivePlayer()
            .getField()
            .getMonstersArea()
            .contains(monster)
        ) {
          Card.getBoard().setMonsterAdded(true);
          return true;
        }
      } else throw new MultipleMonsterAdditionException();
    } else throw new WrongPhaseException();

    return false;
  }

  @Override
  public boolean setSpell(SpellCard spell) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) if (field.getPhase() == Phase.MAIN1 || field.getPhase() == Phase.MAIN2) {
      field.addSpellToField(spell, null, true);

      if (
        Card
          .getBoard()
          .getActivePlayer()
          .getField()
          .getSpellArea()
          .contains(spell)
      ) return true;
    } else throw new WrongPhaseException();

    return false;
  }

  @Override
  public boolean activateSpell(SpellCard spell, MonsterCard monster) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) if (
      field.getPhase() == Phase.MAIN1 ||
      field.getPhase() == Phase.MAIN2 ||
      spell instanceof MagePower ||
      spell instanceof MonsterReborn
    ) {
      if (spell.getLocation() == Location.HAND) {
        field.addSpellToField(spell, monster, false);
        return true;
      }

      field.activateSetSpell(spell, monster);
      return true;
    } else throw new WrongPhaseException();

    return false;
  }

  @Override
  public boolean declareAttack(
    MonsterCard activeMonster,
    MonsterCard opponentMonster
  ) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) {
      if (field.getPhase() == Phase.BATTLE) {
        if (activeMonster.getMode() == Mode.ATTACK) {
          if (!(activeMonster.hasAttacked())) {
            activeMonster.action(opponentMonster);
            activeMonster.setHasAttacked(true);
            return true;
          } else throw new MonsterMultipleAttackException();
        } else throw new DefenseMonsterAttackException();
      } else throw new WrongPhaseException();
    }

    return false;
  }

  @Override
  public boolean declareAttack(MonsterCard activeMonster) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) {
      if (field.getPhase() == Phase.BATTLE) {
        if (activeMonster.getMode() == Mode.ATTACK) {
          if (!(activeMonster.hasAttacked())) {
            activeMonster.action();
            activeMonster.setHasAttacked(true);
            return true;
          } else throw new MonsterMultipleAttackException();
        } else throw new DefenseMonsterAttackException();
      } else throw new WrongPhaseException();
    }

    return false;
  }

  @Override
  public void addCardToHand() {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) Card.getBoard().getActivePlayer().getField().addCardToHand();
  }

  @Override
  public void addNCardsToHand(int n) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) Card.getBoard().getActivePlayer().getField().addNCardsToHand(n);
  }

  public void addCardToHandHelper(int n) {
    if (Card.getBoard().getWinner() == null) {
      ArrayList<Card> list = this.field.getDeck().drawNCards(n);
      if (list.size() < n) {
        if (this == Card.getBoard().getActivePlayer()) Card
          .getBoard()
          .setWinner(Card.getBoard().getOpponentPlayer()); else Card
          .getBoard()
          .setWinner(Card.getBoard().getActivePlayer());
      } else {
        while (!list.isEmpty()) {
          Card tmp = list.remove(0);
          tmp.setLocation(Location.HAND);
          this.field.getHand().add(tmp);
        }
      }
    }
  }

  @Override
  public void endPhase() {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) {
      if (field.getPhase() == Phase.MAIN1) field.setPhase(Phase.BATTLE); else {
        if (field.getPhase() == Phase.BATTLE) field.setPhase(
          Phase.MAIN2
        ); else endTurn();
      }
    }
  }

  @Override
  public boolean endTurn() {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null
    ) {
      Card.getBoard().nextPlayer();
      return true;
    }

    return false;
  }

  @Override
  public boolean switchMonsterMode(MonsterCard monster) {
    if (
      this == Card.getBoard().getActivePlayer() &&
      Card.getBoard().getWinner() == null &&
      monster.getLocation() == Location.FIELD
    ) {
      if (field.getPhase() == Phase.MAIN1 || field.getPhase() == Phase.MAIN2) {
        if (!monster.isModeSwitched()) {
          if (monster.getMode() == Mode.ATTACK) monster.setMode(
            Mode.DEFENSE
          ); else monster.setMode(Mode.ATTACK);
          monster.setModeSwitched(true);
          return true;
        }
      } else throw new WrongPhaseException();
    }

    return false;
  }

  public String getName() {
    return name;
  }

  public int getLifePoints() {
    return lifePoints;
  }

  public Field getField() {
    return field;
  }

  public void setLifePoints(int lifePoints) {
    this.lifePoints = lifePoints;
  }

  @Override
  public String toString() {
    return name;
  }
}
