package Game.Entities;

import Game.Entities.Hero;
import Game.Items.Helmet;
import Game.Items.Vest;
import Game.Items.Weapon;
import Game.PlayerType;
import Game.Skill;
import Game.Stat;

import static Game.Items.Names.allSkills;

public class Mage extends Hero {


    public Mage(String name) {
        super(name);
        setType(PlayerType.MAGE);
        setPrimaryStat(Stat.INTELLIGENCE);
        createDefaultInventory();
    }

    @Override
    public void createDefaultInventory() {
        this.setWeapon(new Weapon("Default staff", 0, 20, super.getType()));
        this.setHelmet(new Helmet("Default helmet", 0, 5, 0));
        this.setVest(new Vest("Default vest", 0, 5, 0));
    }

    @Override
    public String toString() {
        return String.format("%s, Level %d Mage", this.getName(), this.getLevel());
    }

    @Override
    public void level() {
        if (xp >= getXpUntilNextlevel()) {
            setLevel(getLevel() + 1);
            xp-=getXpUntilNextlevel();
            setXpUntilNextlevel((int)(getXpUntilNextlevel() * 1.25));
            setMaxHP(getMaxHP() + 10);
            setStat(getStat() + 3);
            super.level();
        }
    }

    public void spell() {
        changeCurrentHP(30);
        setStat(getStat() + 1);
        System.out.println("You cast a defensive spell which increases your HP by 30 and your primary stat by 1");
    }

    public void multicast() {
        System.out.println("You can cast 2 spells this turn");
    }


}
