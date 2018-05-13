package game.items;

import static game.items.Names.armorFirstPart;
import static game.items.Names.armorSecondPart;

import java.util.concurrent.ThreadLocalRandom;

public class Helmet extends Armor implements Equipable {

  private int statBonus;
  private int levelRequirement;

  public Helmet() {
    generateRandomEquipable();
  }

  public Helmet(String name, int cost, int defence, int statBonus) {
    setName(name);
    setCost(cost);
    setDefence(defence);
    setStatBonus(statBonus);
  }

  public void generateRandomEquipable() {

    int firstIndex = ThreadLocalRandom.current().nextInt(0, armorFirstPart.size());
    int secondIndex = ThreadLocalRandom.current().nextInt(0, armorSecondPart.size());
    String result = armorFirstPart.get(firstIndex) + " Helmet of " + armorSecondPart.get(secondIndex);
    this.setName(result);
    int stat = firstIndex + secondIndex * 2 + 1;
    int defence = (int) (firstIndex * 1.5 + secondIndex * 3 + 2);
    levelRequirement = (int) ((firstIndex + secondIndex) / 1.5);
    this.setDefence(defence);
    this.setStatBonus(stat);
    int cost = stat * 30 + defence * 60;
    this.setCost(cost);
  }

  public int getLevelRequirement() {
    return levelRequirement;
  }

  public int getStatBonus() {
    return statBonus;
  }

  @Override
  public String toString() {
    return String.format("%s. Stat bonus %d. Defence %d. Level requirement %d. Cost %d.", this.getName(), statBonus, getDefence(), levelRequirement, getCost());
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


  private void setStatBonus(int statBonus) {
    this.statBonus = statBonus;
  }
}
