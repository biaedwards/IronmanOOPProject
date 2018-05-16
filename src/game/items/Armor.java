package game.items;

abstract class Armor extends Item implements Equipable {

  private int defence;

  public int getDefence() {
    return defence;
  }

  void setDefence(int defence) {
    this.defence = defence;
  }
}
