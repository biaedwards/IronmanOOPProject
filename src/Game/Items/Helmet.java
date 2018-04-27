package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

import java.util.concurrent.ThreadLocalRandom;

public class Helmet extends Armor {
  int statBonus;

  public Helmet() {
    generateRandomHelmet();
  }

  public Helmet(String name, int cost, int defence, int statBonus) {
    setName(name);
    setCost(cost);
    setDefence(defence);
    setStatBonus(statBonus);

  }

  public void generateRandomHelmet() {
    firstPart.add("Paper");
    firstPart.add("Cloth");
    firstPart.add("Leather");
    firstPart.add("Wooden");
    firstPart.add("Chain");
    firstPart.add("Copper");
    firstPart.add("Silver");
    firstPart.add("Gold");
    firstPart.add("Titanium");

    secondPart.add("Weaklings");
    secondPart.add("Juniours");
    secondPart.add("Nobles");
    secondPart.add("Barons");
    secondPart.add("Warlords");
    secondPart.add("Demigods");

    int firstIndex = ThreadLocalRandom.current().nextInt(0, firstPart.size());
    int secondIndex = ThreadLocalRandom.current().nextInt(0, secondPart.size());
    String result = firstPart.get(firstIndex) + " Helmet of " + secondPart.get(secondIndex);
    this.setName(result);
    int stat = firstIndex + secondIndex * 2 + 1;
    int defence = (int) (firstIndex * 1.5 + secondIndex * 3+2);
    this.setDefence(defence);
    this.setStatBonus(stat);
    int cost = stat * 30 + defence * 50;
    this.setCost(cost);
  }


  @Override
  public String toString() {
    return String.format("Helmet %s. Stat bonus %d. Defence %d. Cost %d.", this.getName(), statBonus, getDefence(), getCost());
  }

  public int getStatBonus() {
    return statBonus;
  }

  public void setStatBonus(int statBonus) {
    this.statBonus = statBonus;
  }

  @Override
  public void equip(Hero hero) {
    hero.setHelmet(this);
  }
}
