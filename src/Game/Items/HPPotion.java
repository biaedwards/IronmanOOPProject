package Game.Items;

import Game.Hero.Hero;

public class HPPotion extends Item implements Usable {
    private int value;

    HPPotion(String name, int cost, int value){
        super(name, cost);
        this.value = value;
    }

    @Override
    public void use(Hero hero) {
        hero.setCurrentHP(hero.getCurrentHP()+value);
    }
}
