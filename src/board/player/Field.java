package board.player;

import cards.Card;
import cards.Location;
import cards.Mode;
import cards.MonsterCard;
import cards.spells.SpellCard;
import exceptions.NoMonsterSpaceException;
import exceptions.NoSpellSpaceException;
import exceptions.UnexpectedFormatException;
import java.io.IOException;
import java.util.ArrayList;

public class Field {

  private Phase phase;
  private ArrayList<MonsterCard> monstersArea;
  private ArrayList<SpellCard> spellArea;
  private ArrayList<Card> hand;
  private ArrayList<Card> graveyard;
  private Deck deck;

  public Field() throws IOException, UnexpectedFormatException {
    monstersArea = new ArrayList<MonsterCard>(5);
    spellArea = new ArrayList<SpellCard>(5);
    hand = new ArrayList<Card>();
    graveyard = new ArrayList<Card>();
    deck = new Deck();
    phase = Phase.MAIN1;
  }

  public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden) {
    if (!(monstersArea.size() < 5)) throw new NoMonsterSpaceException(); else {
      if (monster.getLocation() == Location.HAND) {
        monster.setLocation(Location.FIELD);
        monster.setMode(m);
        monster.setHidden(isHidden);
        monstersArea.add(monster);
        hand.remove(monster);
      }
    }
  }

  public void addMonsterToField(
    MonsterCard monster,
    Mode m,
    ArrayList<MonsterCard> sacrifices
  ) {
    if (!(monstersArea.size() < 5)) throw new NoMonsterSpaceException(); else {
      int level = monster.getLevel();

      boolean isHidden = false;
      if (m == Mode.DEFENSE) isHidden = true;

      if (level <= 4 && sacrifices.size() == 0) {
        addMonsterToField(monster, m, isHidden);
      } else {
        if (level <= 6 && level > 4 && sacrifices.size() == 1) {
          removeMonsterToGraveyard(sacrifices);
          addMonsterToField(monster, m, isHidden);
        } else {
          if (level <= 8 && level > 6 && sacrifices.size() == 2) {
            removeMonsterToGraveyard(sacrifices);
            addMonsterToField(monster, m, isHidden);
          }
        }
      }
    }
  }

  public void removeMonsterToGraveyard(MonsterCard monster) {
    if (monster.getLocation() == Location.FIELD) {
      monster.setLocation(Location.GRAVEYARD);
      monster.setHasAttacked(false);
      monster.setModeSwitched(false);
      monstersArea.remove(monster);
      graveyard.add(monster);
    }
  }

  public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {
    while (!monsters.isEmpty()) removeMonsterToGraveyard(monsters.remove(0));
  }

  public void addSpellToField(
    SpellCard action,
    MonsterCard monster,
    boolean hidden
  ) {
    if (!(spellArea.size() < 5)) throw new NoSpellSpaceException(); else {
      if (action.getLocation() == Location.HAND) {
        hand.remove(action);
        spellArea.add(action);
        action.setLocation(Location.FIELD);
        action.setHidden(hidden);

        if (!hidden) activateSetSpell(action, monster);
      }
    }
  }

  public void activateSetSpell(SpellCard action, MonsterCard monster) {
    if (action.getLocation() == Location.FIELD) {
      action.setHidden(false);
      action.action(monster);
      removeSpellToGraveyard(action);
    }
  }

  public void removeSpellToGraveyard(SpellCard spell) {
    if (spell.getLocation() == Location.FIELD) {
      spell.setLocation(Location.GRAVEYARD);
      spellArea.remove(spell);
      graveyard.add(spell);
    }
  }

  public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {
    while (!spells.isEmpty()) removeSpellToGraveyard(spells.remove(0));
  }

  public void addCardToHand() {
    Card tmp = deck.drawOneCard();

    if (tmp == null) Card
      .getBoard()
      .setWinner(Card.getBoard().getOpponentPlayer()); else {
      hand.add(tmp);
      tmp.setLocation(Location.HAND);
    }
  }

  public void addNCardsToHand(int n) {
    for (int i = 0; i < n; i++) addCardToHand();
  }

  public void removeCardToGraveyard(Card card) {
    card.setLocation(Location.GRAVEYARD);
    hand.remove(card);
    graveyard.add(card);
  }

  public void removeCardToGraveyard(ArrayList<Card> cards) {
    while (!cards.isEmpty()) removeCardToGraveyard(cards.remove(0));
  }

  public Phase getPhase() {
    return phase;
  }

  public ArrayList<MonsterCard> getMonstersArea() {
    return monstersArea;
  }

  public ArrayList<SpellCard> getSpellArea() {
    return spellArea;
  }

  public ArrayList<Card> getHand() {
    return hand;
  }

  public ArrayList<Card> getGraveyard() {
    return graveyard;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public Deck getDeck() {
    return deck;
  }
}
