package Game;

import java.util.ArrayList;

public class Skill {
    private PlayerType type;
    private String name;
    private int damage;
    private boolean usesWeapon;

    public Skill(PlayerType type, String name, int damage, boolean usesWeapon) {
        setType(type);
        setName(name);
        setDamage(damage);
        setUsesWeapon(usesWeapon);
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isUsesWeapon() {
        return usesWeapon;
    }

    public void setUsesWeapon(boolean usesWeapon) {
        this.usesWeapon = usesWeapon;
    }


}

