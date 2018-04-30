package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

import java.util.concurrent.ThreadLocalRandom;

public class Vest extends Armor {
    int HPBonus;

    public Vest() {
        generateRandomVest();
    }

    public Vest(String name, int cost, int defence, int HPBonus) {
        setName(name);
        setCost(cost);
        setDefence(defence);
        setHPBonus(HPBonus);

    }

    public void generateRandomVest() {
        firstPart.add("Paper");
        firstPart.add("Cloth");
        firstPart.add("Leather");
        firstPart.add("Wooden");
        firstPart.add("Chain");
        firstPart.add("Copper");
        firstPart.add("Steel");
        firstPart.add("Silver");
        firstPart.add("Gold");
        firstPart.add("Titanium");
        firstPart.add("Diamond");
        firstPart.add("Epic");
        firstPart.add("Godly");
        firstPart.add("Legendary");


        secondPart.add("Weaklings");
        secondPart.add("Juniours");
        secondPart.add("Nobles");
        secondPart.add("Barons");
        secondPart.add("Warlords");
        secondPart.add("Demigods");

        int firstIndex = ThreadLocalRandom.current().nextInt(0, firstPart.size());
        int secondIndex = ThreadLocalRandom.current().nextInt(0, secondPart.size());
        String result = firstPart.get(firstIndex) + " Vest of " + secondPart.get(secondIndex);
        this.setName(result);
        int stat = firstIndex * 2 + secondIndex * 3 + 2;
        int defence = (int) (firstIndex * 1.5 + secondIndex * 3 + 2);
        this.setDefence(defence);
        this.setHPBonus(stat);
        int cost = stat * 30 + defence * 50;
        this.setCost(cost);
    }

    @Override
    public String toString() {
        return String.format("%s. HP Bonus %d. Defence %d. Cost %d. \n", getName(), HPBonus, defence, getCost());
    }

    public int getHPBonus() {
        return HPBonus;
    }

    public void setHPBonus(int HPBonus) {
        this.HPBonus = HPBonus;
    }

    @Override
    public void equip(Hero hero) {
        hero.setVest(this);
    }
}
