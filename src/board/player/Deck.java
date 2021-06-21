package board.player;

import cards.Card;
import cards.MonsterCard;
import cards.spells.CardDestruction;
import cards.spells.ChangeOfHeart;
import cards.spells.DarkHole;
import cards.spells.GracefulDice;
import cards.spells.HarpieFeatherDuster;
import cards.spells.HeavyStorm;
import cards.spells.MagePower;
import cards.spells.MonsterReborn;
import cards.spells.PotOfGreed;
import cards.spells.Raigeki;
import cards.spells.SpellCard;
import exceptions.EmptyFieldException;
import exceptions.MissingFieldException;
import exceptions.UnexpectedFormatException;
import exceptions.UnknownCardTypeException;
import exceptions.UnknownSpellCardException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck {

  private static ArrayList<Card> monsters;
  private static ArrayList<Card> spells;
  private ArrayList<Card> deck;
  private static boolean isLoaded;
  private static String monstersPath = "/resources/Database-Monsters.csv";
  private static String spellsPath = "/resources/Database-Spells.csv";
  private boolean hasMonstersLoaded = false;
  private Scanner sc;
  private static BufferedReader br;

  public Deck() throws IOException, UnexpectedFormatException {
    isLoaded = !(monsters == null || spells == null);

    if (!isLoaded) {
      monsters = tryLoadCardsFromFile(monstersPath);
      hasMonstersLoaded = true;
      spells = tryLoadCardsFromFile(spellsPath);
      isLoaded = true;
    }

    deck = new ArrayList<Card>(20);
    buildDeck(monsters, spells);
    shuffleDeck();
  }

  public ArrayList<Card> tryLoadCardsFromFile(String path)
    throws IOException, UnexpectedFormatException {
    sc = new Scanner(System.in);
    int errorCount = 0;
    boolean isCorrectPath = false;
    ArrayList<Card> cardsArray = null;

    do {
      try {
        cardsArray = loadCardsFromFile(path);
        isCorrectPath = true;
      } catch (FileNotFoundException e) {
        if (errorCount == 3) {
          e.printStackTrace();
          throw e;
        }

        errorCount++;
        System.out.println("File not found");
        System.out.print("Please enter the correct path: ");

        if (sc.hasNextLine()) path = sc.nextLine();
      } catch (MissingFieldException e) {
        if (errorCount == 3) {
          e.printStackTrace();
          throw e;
        }

        errorCount++;
        System.out.println("Missing field");
        System.out.print("Please enter the correct path: ");

        if (sc.hasNextLine()) path = sc.nextLine();
      } catch (EmptyFieldException e) {
        if (errorCount == 3) {
          e.printStackTrace();
          throw e;
        }

        errorCount++;
        System.out.println("Empty field");
        System.out.print("Please enter the correct path: ");

        if (sc.hasNextLine()) path = sc.nextLine();
      } catch (UnknownCardTypeException e) {
        if (errorCount == 3) {
          e.printStackTrace();
          throw e;
        }

        errorCount++;
        System.out.println("Unknown card type");
        System.out.print("Please enter the correct path: ");

        if (sc.hasNextLine()) path = sc.nextLine();
      } catch (UnknownSpellCardException e) {
        if (errorCount == 3) {
          e.printStackTrace();
          throw e;
        }

        errorCount++;
        System.out.println("Unknown spell card");
        System.out.print("Please enter the correct path: ");

        if (sc.hasNextLine()) path = sc.nextLine();
      }
    } while (errorCount <= 3 && !isCorrectPath);

    return cardsArray;
  }

  public static ArrayList<String> readFile(String path) throws IOException {
    ArrayList<String> cardsStringArray = new ArrayList<String>();
    String currentLine = "";

    InputStream i = Deck.class.getResourceAsStream(path);
    br = new BufferedReader(new InputStreamReader(i));

    while ((currentLine = br.readLine()) != null) cardsStringArray.add(
      currentLine
    );

    return cardsStringArray;
  }

  public ArrayList<Card> loadCardsFromFile(String path)
    throws IOException, UnexpectedFormatException {
    ArrayList<String> cardsStringArray = readFile(path);
    ArrayList<Card> cardsArray = new ArrayList<Card>();

    for (int i = 0; i < cardsStringArray.size(); i++) {
      String[] cardStringElement = cardsStringArray.get(i).split(",");

      if (!hasMonstersLoaded) {
        if (
          !(cardStringElement[0].equals("Monster"))
        ) throw new UnknownCardTypeException(path, i + 1, cardStringElement[0]);
      } else {
        if (
          !(cardStringElement[0].equals("Spell"))
        ) throw new UnknownCardTypeException(path, i + 1, cardStringElement[0]);
      }

      if (!hasMonstersLoaded) {
        if (cardStringElement.length != 6) throw new MissingFieldException(
          path,
          i + 1
        );
      } else {
        if (cardStringElement.length != 3) throw new MissingFieldException(
          path,
          i + 1
        );
      }

      for (int j = 0; j < cardStringElement.length; j++) if (
        cardStringElement[j].equals("") || cardStringElement[j].equals(" ")
      ) throw new EmptyFieldException(path, i + 1, j + 1);

      if (cardStringElement[0].equals("Monster")) {
        cardsArray.add(
          new MonsterCard(
            cardStringElement[1],
            cardStringElement[2],
            Integer.parseInt(cardStringElement[5]),
            Integer.parseInt(cardStringElement[3]),
            Integer.parseInt(cardStringElement[4])
          )
        );

        if (i == cardsStringArray.size() - 1) monstersPath = path;
      } else {
        if (hasMonstersLoaded) if (
          !cardStringElement[1].equals("Card Destruction") &&
          !cardStringElement[1].equals("Change Of Heart") &&
          !cardStringElement[1].equals("Dark Hole") &&
          !cardStringElement[1].equals("Graceful Dice") &&
          !cardStringElement[1].equals("Harpie's Feather Duster") &&
          !cardStringElement[1].equals("Heavy Storm") &&
          !cardStringElement[1].equals("Mage Power") &&
          !cardStringElement[1].equals("Monster Reborn") &&
          !cardStringElement[1].equals("Pot of Greed") &&
          !cardStringElement[1].equals("Raigeki")
        ) throw new UnknownSpellCardException(
          path,
          i + 1,
          cardStringElement[1]
        );

        if (i == cardsStringArray.size() - 1) spellsPath = path;

        if (cardStringElement[1].equals("Card Destruction")) cardsArray.add(
          new CardDestruction(cardStringElement[1], cardStringElement[2])
        );
        if (cardStringElement[1].equals("Change Of Heart")) cardsArray.add(
          new ChangeOfHeart(cardStringElement[1], cardStringElement[2])
        );
        if (cardStringElement[1].equals("Dark Hole")) cardsArray.add(
          new DarkHole(cardStringElement[1], cardStringElement[2])
        );
        if (cardStringElement[1].equals("Graceful Dice")) cardsArray.add(
          new GracefulDice(cardStringElement[1], cardStringElement[2])
        );
        if (
          cardStringElement[1].equals("Harpie's Feather Duster")
        ) cardsArray.add(
          new HarpieFeatherDuster(cardStringElement[1], cardStringElement[2])
        );
        if (cardStringElement[1].equals("Heavy Storm")) cardsArray.add(
          new HeavyStorm(cardStringElement[1], cardStringElement[2])
        );
        if (cardStringElement[1].equals("Mage Power")) cardsArray.add(
          new MagePower(cardStringElement[1], cardStringElement[2])
        );
        if (cardStringElement[1].equals("Monster Reborn")) cardsArray.add(
          new MonsterReborn(cardStringElement[1], cardStringElement[2])
        );
        if (cardStringElement[1].equals("Pot of Greed")) cardsArray.add(
          new PotOfGreed(cardStringElement[1], cardStringElement[2])
        );
        if (cardStringElement[1].equals("Raigeki")) cardsArray.add(
          new Raigeki(cardStringElement[1], cardStringElement[2])
        );
      }
    }

    return cardsArray;
  }

  private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells)
    throws IOException {
    for (int i = 0; i < 5; i++) {
      if (spells != null) {
        int randomIndex = (int) (Math.random() * spells.size());
        deck.add(cloneSpellCard(spells.get(randomIndex)));
      }
    }

    for (int i = 0; i < 15; i++) {
      if (monsters != null) {
        int randomIndex = (int) (Math.random() * monsters.size());
        deck.add(cloneMonsterCard(monsters.get(randomIndex)));
      }
    }
  }

  public static MonsterCard cloneMonsterCard(Card c) {
    MonsterCard m = (MonsterCard) c;
    return new MonsterCard(
      m.getName(),
      m.getDescription(),
      m.getLevel(),
      m.getAttackPoints(),
      m.getDefensePoints()
    );
  }

  public static SpellCard cloneSpellCard(Card c) {
    SpellCard s = (SpellCard) c;

    if (s instanceof CardDestruction) return new CardDestruction(
      s.getName(),
      s.getDescription()
    );
    if (s instanceof ChangeOfHeart) return new ChangeOfHeart(
      s.getName(),
      s.getDescription()
    );
    if (s instanceof DarkHole) return new DarkHole(
      s.getName(),
      s.getDescription()
    );
    if (s instanceof GracefulDice) return new GracefulDice(
      s.getName(),
      s.getDescription()
    );
    if (s instanceof HeavyStorm) return new HeavyStorm(
      s.getName(),
      s.getDescription()
    );
    if (s instanceof HarpieFeatherDuster) return new HarpieFeatherDuster(
      s.getName(),
      s.getDescription()
    );
    if (s instanceof MagePower) return new MagePower(
      s.getName(),
      s.getDescription()
    );
    if (s instanceof MonsterReborn) return new MonsterReborn(
      s.getName(),
      s.getDescription()
    );
    if (s instanceof PotOfGreed) return new PotOfGreed(
      s.getName(),
      s.getDescription()
    );
    return new Raigeki(s.getName(), s.getDescription());
  }

  private void shuffleDeck() {
    Collections.shuffle(deck);
  }

  public ArrayList<Card> drawNCards(int n) {
    ArrayList<Card> drawnCards = new ArrayList<Card>();

    for (int i = 0; i < n; i++) {
      Card c = drawOneCard();
      if (c != null) drawnCards.add(c); else return drawnCards;
    }

    return drawnCards;
  }

  public Card drawOneCard() {
    if (deck.isEmpty()) return null;

    return deck.remove(0);
  }

  public ArrayList<Card> getDeck() {
    return deck;
  }

  public static ArrayList<Card> getMonsters() {
    return monsters;
  }

  public static ArrayList<Card> getSpells() {
    return spells;
  }

  public static boolean isLoaded() {
    return isLoaded;
  }

  public static void setMonsters(ArrayList<Card> monsters) {
    Deck.monsters = monsters;
  }

  public static void setSpells(ArrayList<Card> spells) {
    Deck.spells = spells;
  }

  public static void setLoaded(boolean isLoaded) {
    Deck.isLoaded = isLoaded;
  }

  public static String getMonstersPath() {
    return monstersPath;
  }

  public static String getSpellsPath() {
    return spellsPath;
  }

  public static void setMonstersPath(String monstersPath) {
    Deck.monstersPath = monstersPath;
  }

  public static void setSpellsPath(String spellsPath) {
    Deck.spellsPath = spellsPath;
  }
}
