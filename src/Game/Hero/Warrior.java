package Game.Hero;

import Game.Skill;

public class Warrior extends Hero {

    public Warrior(String name) {
        super(name);
    }

    @Override
    public String toString(){
        return String.format("%s, Level %d Warrior", name, level);
    }
}
