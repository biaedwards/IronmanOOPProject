package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

public abstract class Armor extends Item implements Equippable {
    PlayerType type;
    int defence;

    Armor(String name, int cost, int defence, PlayerType type){
        super(name, cost);
        this.defence = defence;
        this.type = type;
    }

    public PlayerType getType() {
        return type;
    }

    public int getDefence() {
        return defence;
    }

    public void equip(Hero hero){
        hero.setDefence(hero.getDefence()+defence);
    }
}
