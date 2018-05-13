package game.items;

import static game.items.Names.armorFirstPart;
import static game.items.Names.armorSecondPart;

import java.util.concurrent.ThreadLocalRandom;

public class Vest extends Armor implements Equipable {
  private int HPBonus;
  private int levelRequirement;

  public Vest() {
    generateRandomEquipable();
  }

  public Vest(String name, int cost, int defence, int HPBonus) {
    setName(name);
    setCost(cost);
    setDefence(defence);
    setHPBonus(HPBonus);
  }

  public void generateRandomEquipable() {
    int firstIndex = ThreadLocalRandom.current().nextInt(0, armorFirstPart.size());
    int secondIndex = ThreadLocalRandom.current().nextInt(0, armorSecondPart.size());
    String result = armorFirstPart.get(firstIndex) + " Vest of " + armorSecondPart.get(secondIndex);
    this.setName(result);
    int stat = firstIndex * 2 + secondIndex * 3 + 2;
    int defence = firstIndex * 4 + secondIndex * 3 + 3;
    levelRequirement = (int) ((firstIndex + secondIndex) / 1.5);
    this.setDefence(defence);
    this.setHPBonus(stat);
    int cost = stat * 30 + defence * 60;
    this.setCost(cost);
  }

  public int getHPBonus() {
    return HPBonus;
  }

  public int getLevelRequirement() {
    return levelRequirement;
  }

  @Override
  public String toString() {
    return String.format("%s. HP Bonus %d. Defence %d. Level Requirement %d Cost %d.", getName(), HPBonus, getDefence(), levelRequirement, getCost());
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

  private void setHPBonus(int HPBonus) {
    this.HPBonus = HPBonus;
  }
}
