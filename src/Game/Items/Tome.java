package Game.Items;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Tome extends Item implements Usable{
    private int value;
    private ArrayList<String> names = new ArrayList<>();

    public Tome() {
        generateRandom();
    }

    public void generateRandom() {
        names.add("Small tome");
        names.add("Medium tome");
        names.add("Large tome");
        names.add("Huge tome");
        int index = ThreadLocalRandom.current().nextInt(0, names.size());
        int xp = (index + 1) * 250;
        int cost = xp / 2;
        setName(names.get(index));
        setCost(cost);
        setValue(xp);

    }

    @Override
    public String toString(){
        return String.format("%s - %d XP | Cost: %d",getName(),getValue(),getCost());
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

}
