package Game.Items;

import Game.PlayerType;

public abstract class Armor extends Item  {
    PlayerType type;
    int defence;

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
