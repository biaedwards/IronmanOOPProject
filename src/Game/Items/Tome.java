package Game.Items;

import Game.Hero.Hero;

public class Tome extends Item implements Usable {
    private int value;

    Tome(String name, int cost, int value){
        super(name, cost);
        this.value = value;
    }

    @Override
    public void use(Hero hero) {
        hero.setXp(hero.getXp()+value);
    }
}
