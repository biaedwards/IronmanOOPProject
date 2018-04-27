package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

public class Helmet extends Armor {
    int statBonus;

    Helmet(String name, int cost, int defence, int statBonus, PlayerType type) {
        super(name, cost, defence, type);
        this.statBonus = statBonus;
    }

    @Override
    public String toString(){
        return String.format("Helmet %s with stat bonus %d.", this.getName(), statBonus);
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
