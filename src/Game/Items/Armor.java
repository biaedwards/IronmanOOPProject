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

    @Override
    public String toString(){
        return String.format("Armor %s. Defence %d. Type %s. Cost %d.", getName(), defence, getType(), getCost());
    }

    public PlayerType getType() {
        return type;
    }

    public int getDefence() {
        return defence;
    }

    public abstract void equip(Hero hero);
}
