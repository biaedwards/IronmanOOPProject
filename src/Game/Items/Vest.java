package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

public class Vest extends Armor {
    int HPBonus;

    Vest(String name, int cost, int defence, int HPBonus, PlayerType type) {
        super(name, cost, defence, type);
        this.HPBonus = HPBonus;
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
        hero.setCurrentHP(hero.getCurrentHP()+HPBonus);
    }
}
