package cards;

import board.Board;

public abstract class Card {

  private final String name;
  private final String description;
  private boolean isHidden;
  private Location location;
  private static Board board;

  public Card(String name, String description) {
    this.name = name;
    this.description = description;
    isHidden = true;
    location = Location.DECK;
  }

  public abstract void action(MonsterCard monster);

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public boolean isHidden() {
    return isHidden;
  }

  public Location getLocation() {
    return location;
  }

  public void setHidden(boolean isHidden) {
    this.isHidden = isHidden;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public static Board getBoard() {
    return board;
  }

  public static void setBoard(Board board) {
    Card.board = board;
  }

  @Override
  public String toString() {
    return name;
  }
}
