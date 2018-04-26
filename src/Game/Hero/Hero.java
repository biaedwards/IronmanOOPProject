package Game.Hero;

import Game.Items.Item;
import Game.Items.Weapon;
import Game.Skill;
import Game.Stat;

import java.util.ArrayList;

public abstract class Hero {
    int hp=100;
    int xp=0;
    int level=1;
    int defence=0;
    int stat=1;
    Stat primaryStat;
    String name;
    Weapon weapon;
    ArrayList<Item> inventory;
    ArrayList<Skill> skills;

    void learnSkill(){

    }

    void castSkill(){

    }



}
