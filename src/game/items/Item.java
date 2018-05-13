package game.items;

public abstract class Item {
  private int cost;
  private String name;

  Item(String name, int cost) {
    this.name = name;
    this.cost = cost;
  }

  Item() {
  }

  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  void setName(String name) {
    this.name = name;
  }

  void setCost(int cost) {
    this.cost = cost;
  }

}
