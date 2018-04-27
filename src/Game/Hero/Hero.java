package Game.Hero;

import Game.Items.*;
import Game.PlayerType;
import Game.Skill;
import Game.Stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Hero {
    private PlayerType type;
    private int maxHP = 100;
    private int currentHP;
    private double xp = 0;
    private int level = 1;
    private int defence = 0;
    private int stat = 1;
    private int gold = 0;
    private int damage;
    private double xpUntilNextlevel = 1000;
    private Stat primaryStat;
    private String name;
    private Weapon weapon;
    private Helmet helmet;
    private Vest vest;
    private HashMap<Item, Integer> inventory;
    private HashSet<Skill> skills;
    private ArrayList<Skill> allSkills = new ArrayList<>();


    Hero(String name) {
        setName(name);
        skills = new HashSet<>();
        inventory = new HashMap<>();
        currentHP = maxHP;
    }

    public PlayerType getType() {
        return type;
    }


    public double getXp() {
        return xp;
    }

    public void setType(PlayerType type) {
        this.type = type;

    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int hp) {
        this.maxHP = hp;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public double getXpUntilNextlevel() {
        return xpUntilNextlevel;
    }

    public void setXpUntilNextlevel(double xpUntilNextlevel) {
        this.xpUntilNextlevel = xpUntilNextlevel;
    }

    public Stat getPrimaryStat() {
        return primaryStat;
    }


    public void setPrimaryStat(Stat primaryStat) {
        this.primaryStat = primaryStat;

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        damage = stat + weapon.getDamage();
        return damage;
    }

    public Helmet getHelmet() {

        return helmet;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public Vest getVest() {
        return vest;
    }

    public void setVest(Vest vest) {
        this.vest = vest;
    }

    public void setInventory(HashMap<Item, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setSkills(HashSet<Skill> skills) {
        this.skills = skills;
    }

    public void setXp(double xp) {
        this.xp += xp;
    }

    public int getLevel() {
        return level;
    }

    public int getDefence() {
        defence = helmet.getDefence() + vest.getDefence();
        return defence;
    }

    public int getStat() {
        return stat;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold += gold;
    }


    public HashMap<Item, Integer> getInventory() {
        return inventory;
    }

    public HashSet<Skill> getSkills() {
        return skills;
    }

    void learnSkill(Skill skill) {
        skills.add(skill);
    }

    void castSkill(Skill skill) {

    }

    void getUsables() {
        for (Item item : inventory.keySet()) {
            if (item instanceof Usable) System.out.println(item.getName());
        }
    }

    void use(String itemName) {

    }

    void level() {

    }

    public int attack() {
        return weapon.getDamage();
    }

    private void generateAllSkills() {
        Skill fireball = new Skill(PlayerType.MAGE, "Fireball", 5, true);
        Skill stab = new Skill(PlayerType.WARRIOR, "Stab", 5, true);
        Skill icearrow = new Skill(PlayerType.ARCHER, "Ice Arrow", 5, true);
        Skill blizzard = new Skill(PlayerType.MAGE, "Blizzard", 15, true);
        Skill pulverize = new Skill(PlayerType.WARRIOR, "Pulverize", 10, true);
        Skill volley = new Skill(PlayerType.ARCHER, "Volley", 10, true);
        Skill meteorstrike = new Skill(PlayerType.MAGE,"Meteor Strike", 30,true);
        Skill rainofarrows = new Skill(PlayerType.ARCHER,"Rain of arrows", 20,true);
        Skill shockwave = new Skill(PlayerType.WARRIOR,"Shockwave", 20, true);
        Skill armageddon = new Skill(PlayerType.MAGE,"Armageddon", 200, false);
        Skill headshot = new Skill(PlayerType.ARCHER, "Headshot", 100, true);
        Skill earthquake = new Skill(PlayerType.WARRIOR, "Earthquake", 150,false);
        allSkills.add(fireball);
        allSkills.add(stab);
        allSkills.add(icearrow);
        allSkills.add(blizzard);
        allSkills.add(pulverize);
        allSkills.add(volley);
        allSkills.add(meteorstrike);
        allSkills.add(rainofarrows);
        allSkills.add(shockwave);
        allSkills.add(armageddon);
        allSkills.add(headshot);
        allSkills.add(earthquake);



    }
}
