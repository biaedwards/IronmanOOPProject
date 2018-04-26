package Game.Hero;

import Game.Items.Armor;
import Game.Items.Item;
import Game.Items.Usable;
import Game.Items.Weapon;
import Game.PlayerType;
import Game.Skill;
import Game.Stat;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Hero {
    PlayerType type;
    int hp=100;
    int xp=0;
    int level=1;
    int defence=0;
    int stat=1;
    int gold = 0;
    Stat primaryStat;
    String name;
    Weapon weapon;
    Armor armor;
    HashMap<Item, Integer> inventory;
    HashSet<Skill> skills;

    Hero(String name){
        this.name = name;
        skills = new HashSet<>();
        inventory = new HashMap<>();
    }

    public PlayerType getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public int getDefence() {
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

    public Armor getArmor() {
        return armor;
    }

    public HashMap<Item, Integer> getInventory() {
        return inventory;
    }

    public HashSet<Skill> getSkills() {
        return skills;
    }

    void learnSkill(){
        skills.add(skill);
    }

    void castSkill(Skill skill){

    }

    void equipWeapon(){

    }

    void equipArmor(){

    }

    void getUsables(){
        for (Item item: inventory){
            if(item instanceof Usable) System.out.println(item.getName());
        }
    }

    void use(String itemName){

    }

}
