package Game.Location;

import Game.Attack;
import Game.Hero.Hero;
import Game.Items.*;
import Game.Stat;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy implements Attack {

    private int maxHP;
    static final int HP_DEFAULT = 100;
    private String name;
    private int currentHP;
    private int damage;
    private int gold;
    private Item item;
    private int XP;
    private double coefficient;
    private Stat stat;
    private Item usable;

    private ArrayList<String> allNames = new ArrayList<>();

    public Enemy(double coefficient) {
        generateNames();
        this.name = getRandomName();
        maxHP = HP_DEFAULT;
        currentHP = maxHP;
        this.damage = (int) (getRandomDamage() * coefficient);
        this.gold = (int) (damage * 10 * coefficient);
        this.item = getRandomItem();
        usable = generateRandomUsable();
        this.XP = (int) (damage * 2 * coefficient);
        Stat stat;

    }

    public void showStats() {
        System.out.printf("HP: %d \nDamage: %d \nGold reward: %d \nXP reward: %d \nItem reward: %s\n\n", currentHP, damage, gold, XP, item.toString());
    }

    public void dropReward(Hero hero) {
        System.out.printf("%s dropped: Item: %s\n", getName(), getItem().toString());
        System.out.printf("%s, %d gold and you gained %d XP\n\n", usable.getName(), gold, XP);
        hero.addToInventory(item);
        hero.addToInventory(usable);
        hero.setXp(XP);
        hero.setGold(gold);
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getGold() {
        return gold;
    }

    public int getXP() {
        return XP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    private int getRandomDamage() {
        return ThreadLocalRandom.current().nextInt(10, 50);
    }

    private void generateNames() {
        allNames.add("Adrian");
        allNames.add("Borislav");
        allNames.add("Dido");
        allNames.add("Drago");
        allNames.add("Edward");
        allNames.add("Elizar");
        allNames.add("Kiril");
        allNames.add("Krasimir");
        allNames.add("MariaONE");
        allNames.add("MariaTWO");
        allNames.add("MartinONE");
        allNames.add("MartinTwo");
        allNames.add("RadoONE");
        allNames.add("RadoTWO");
        allNames.add("Oxana");
        allNames.add("Nasko");
        allNames.add("Sasho");
        allNames.add("Danail");
        allNames.add("Irina");
        allNames.add("Doncho");
        allNames.add("Georgi");
        allNames.add("Ivan");
        allNames.add("Ivelin");
        allNames.add("Ivo");
        allNames.add("Krisi");
        allNames.add("Lu");
        allNames.add("Penyo");
        allNames.add("Pepi");
        allNames.add("Pesho");
        allNames.add("Rosi");
        allNames.add("Steven");
        allNames.add("Maya");
        allNames.add("Viktor");
        allNames.add("Yana");
        allNames.add("Yani");
        allNames.add("Yasen");
    }

    public int attack() {
        return damage;
    }

    private String getRandomName() {
        int index = ThreadLocalRandom.current().nextInt(0, allNames.size());
        name = allNames.get(index);
        return name;
    }

    private Item getRandomItem() {
        int index = ThreadLocalRandom.current().nextInt(0, 3);
        Item item;
        switch (index) {
            case 0:
                item = new Weapon();
                break;
            case 1:
                item = new Vest();
                break;
            case 2:
                item = new Helmet();
                break;
            default:
                item = null;
                break;
        }
        return item;
    }

    private Item generateRandomUsable() {
        int index = ThreadLocalRandom.current().nextInt(0, 2);
        Item usable;
        switch (index) {
            case 0:
                usable = new HPPotion();
                break;
            case 1:
                usable = new Tome();
                break;
            default:
                usable = null;
                break;

        }
        return usable;

    }

    public void takeDamage(int damage) {
        currentHP -= damage;
    }
}

