package game.items;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class HpPotion extends Item implements Usable {
  private int value;
  private ArrayList<String> names = new ArrayList<>();

  public HpPotion() {
    generateRandom();
  }

  /**
   * Public constructor with String name and int value parameters.
   * */
  public HpPotion(String name, int value) {
    setName(name);
    setValue(value);
    setCost(0);
  }

  /**
   * Method for random generation of HpPotion.
   * */
  public void generateRandom() {
    names.add("Small potion");
    names.add("Medium potion");
    names.add("Large potion");
    names.add("Extra large potion");
    int index = ThreadLocalRandom.current().nextInt(0, names.size());
    int recovery = (index + 1) * 50;
    int cost = recovery / 2;
    setName(names.get(index));
    setCost(cost);
    setValue(recovery);

  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.format("%s - %d HP | Cost: %d", getName(), getValue(), getCost());
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + getCost();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj.hashCode() == this.hashCode()) {
      return true;
    } else {
      return false;
    }
  }

  private void setValue(int value) {
    this.value = value;
  }
}
