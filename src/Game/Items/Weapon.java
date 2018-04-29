package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Weapon extends Item implements Equippable {
    private PlayerType type;
    private int damage;
    private ArrayList<String> firstPart = new ArrayList<>();
    private ArrayList<String> secondPart = new ArrayList<>();
    private ArrayList<String> thirdPart = new ArrayList<>();

    public Weapon(String name, int cost, int damage, PlayerType type) {
        super(name, cost);
        this.damage = damage;
        this.type = type;
    }

    public Weapon() {
        generateRandomWeapon();
    }

    public void generateRandomWeapon() {
        firstPart.add("Wooden");
        firstPart.add("Copper");
        firstPart.add("Silver");
        firstPart.add("Golden");
        firstPart.add("Platinum");
        firstPart.add("Frozen");
        firstPart.add("Burning");
        firstPart.add("Frostfire");
        firstPart.add("Epic");
        firstPart.add("Legendary");
        firstPart.add("Unholy");
        firstPart.add("Divine");
        firstPart.add("Otherworldly");
        firstPart.add("Absurd");

        secondPart.add(" Sword of ");
        secondPart.add(" Bow of ");
        secondPart.add(" Staff of ");

        thirdPart.add("Weaklings");
        thirdPart.add("Newbies");
        thirdPart.add("Recruits");
        thirdPart.add("Proteges");
        thirdPart.add("Nobles");
        thirdPart.add("Masters");
        thirdPart.add("Archangels");
        thirdPart.add("Demigods");
        thirdPart.add("Beasts");
        thirdPart.add("The Dark Ones");
        thirdPart.add("The Gods");

        int firstIndex = ThreadLocalRandom.current().nextInt(0, firstPart.size());
        int secondIndex = ThreadLocalRandom.current().nextInt(0, secondPart.size());
        int thirdIndex = ThreadLocalRandom.current().nextInt(0, thirdPart.size());
        String result = firstPart.get(firstIndex) + secondPart.get(secondIndex) + thirdPart.get(thirdIndex);
        setName(result);
        if (secondIndex == 0) {
            setType(PlayerType.WARRIOR);
        } else if (secondIndex == 1) {
            setType(PlayerType.ARCHER);
        } else if (secondIndex == 2) {
            setType(PlayerType.MAGE);
        }
        int damage = firstIndex * 5 + secondIndex * 4 + 5;
        setDamage(damage);
        int cost = damage * 25;
        setCost(cost);
    }

    @Override
    public String toString() {
        return String.format("%s. Damage %d. Cost %d.", getName(), getDamage(), getCost());
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void equip(Hero hero) {
        hero.setWeapon(this);
    }

}
