package Game.Items;

import Game.Hero.Hero;
import Game.PlayerType;

public class Weapon extends Item implements Equippable {
    private PlayerType type;
    private int damage;

    public Weapon(String name, int cost, int damage, PlayerType type) {
        super(name, cost);
        this.damage = damage;
        this.type = type;
    }

    @Override
    public String toString(){
        return String.format("Weapon %s. Damage %d. Type %s. Cost %d.", getName(), getDamage(), getType(), getCost());
    }

    public PlayerType getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }


    @Override
    public void equip(Hero hero) {
        hero.setWeapon(this);
    }
}
