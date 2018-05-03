package Game.Items;

import Game.Entities.Hero;

import java.util.concurrent.ThreadLocalRandom;

import static Game.Items.Names.armorFirstPart;
import static Game.Items.Names.armorSecondPart;

public class Helmet extends Armor {
    int statBonus;

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
        this.setDefence(defence);
        this.setStatBonus(stat);
        int cost = stat * 30 + defence * 50;
        this.setCost(cost);
    }


    @Override
    public String toString() {
        return String.format("%s. Stat bonus %d. Defence %d. Cost %d.", this.getName(), statBonus, getDefence(), getCost());
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
