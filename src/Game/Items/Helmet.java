package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

public class Helmet extends Armor {
    int statBonus;

    public Helmet(String name, int cost, int defence, int statBonus, PlayerType type) {
        super(name, cost, defence, type);
        this.statBonus = statBonus;
    }

    @Override
    public String toString(){
        return String.format("Helmet %s. Stat bonus %d. Defence %d. Type %s. Cost %d.", this.getName(), statBonus, getDefence(), getType(), getCost());
    }

    public int getStatBonus() {
        return statBonus;
    }

    public void setStatBonus(int statBonus) {
        this.statBonus = statBonus;
    }

    @Override
    public void equip(Hero hero){
        hero.setDefence(hero.getDefence()+defence);
        hero.setStat(hero.getStat()+statBonus);
    }
}
