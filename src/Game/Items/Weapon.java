package Game.Items;

import Game.PlayerType;

public class Weapon extends Item {
    PlayerType type;
    int damage;

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
