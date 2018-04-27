package Game.Items;

import Game.Hero.Hero;

import java.util.ArrayList;

public abstract class Armor extends Item implements Equippable {
    int defence;
    ArrayList<String> firstPart = new ArrayList<>();
    ArrayList<String> secondPart = new ArrayList<>();


    Armor(String name, int cost, int defence) {
        super(name, cost);
        setDefence(defence);
    }

    Armor() {

    }


    public void generateRandomName() {

    }


    @Override
    public String toString() {
        return String.format("Armor %s. Defence %d. Cost %d.", getName(), defence, getCost());
    }


    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public abstract void equip(Hero hero);

    public ArrayList<String> getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(ArrayList<String> firstPart) {
        this.firstPart = firstPart;
    }

    public ArrayList<String> getSecondPart() {
        return secondPart;
    }

    public void setSecondPart(ArrayList<String> secondPart) {
        this.secondPart = secondPart;
    }
}
