package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

public class Vest extends Armor {
    int HPBonus;

    public Vest(String name, int cost, int defence, int HPBonus, PlayerType type) {
        super(name, cost, defence, type);
        this.HPBonus = HPBonus;
    }

    @Override
    public String toString(){
        return String.format("Vest %s. HP Bonus %d. Defence %d. Type %s. Cost %d.", getName(), HPBonus, defence, getType(), getCost());
    }

    public int getHPBonus() {
        return HPBonus;
    }

    public void setHPBonus(int HPBonus) {
        this.HPBonus = HPBonus;
    }

    @Override
    public void equip(Hero hero){
        hero.setDefence(hero.getDefence()+defence);
        hero.changeCurrentHP(hero.getCurrentHP()+HPBonus);
    }
}
