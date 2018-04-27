package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

public class Weapon extends Item implements Equippable {
    private PlayerType type;
    private int damage;

    Weapon(String name, int cost, int damage, PlayerType type) {
        super(name, cost);
        this.damage = damage;
        this.type = type;
    }

    public PlayerType getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }


    @Override
    public void equip(Hero hero) {
        hero.setDamage(hero.getDamage() + damage);
    }
}
