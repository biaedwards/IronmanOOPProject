package Game.Items;

import Game.Hero.Hero;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class HPPotion extends Item implements Usable {
    private int value;
    private ArrayList<String> names = new ArrayList<>();

    HPPotion() {
        generateRandomPotion();

    }

    private void generateRandomPotion() {
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

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void use(Hero hero) {
        hero.changeCurrentHP(value);
    }
}
