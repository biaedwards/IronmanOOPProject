package Game.Entities;


import Game.Entities.Hero;
import Game.Items.Helmet;
import Game.Items.Vest;
import Game.Items.Weapon;
import Game.Entities.Enemy;
import Game.PlayerType;
import Game.Stat;

import static Game.Items.Names.allSkills;

public class Archer extends Hero {

    public Archer(String name) {
        super(name);
        setType(PlayerType.ARCHER);
        setPrimaryStat(Stat.AGILITY);
        createDefaultInventory();
    }


    @Override
    public String toString() {
        return String.format("%s, Level %d Archer", this.getName(), this.getLevel());
    }

    @Override
    public void createDefaultInventory() {
        this.setWeapon(new Weapon("Default", 0, 20, getType()));
        this.setHelmet(new Helmet("Default", 0, 5, 0));
        this.setVest(new Vest("Default", 0, 5, 0));

    }

    @Override
    public void level() {
        if (xp >= getXpUntilNextlevel()) {
            setLevel(getLevel() + 1);
            setXp(-getXpUntilNextlevel());
            setXpUntilNextlevel((int)(getXpUntilNextlevel() * 1.5));
            setMaxHP(getMaxHP() + 15);
            setStat(getStat() + 5);
            super.level();
        }
    }


    public void execute(Enemy enemy) {
        if (enemy.getCurrentHP() * 100 / enemy.getMaxHP() <= 25) {
            System.out.printf("You have executed %s!!!!\n", enemy.getName());
            enemy.takeDamage(9999);

        } else {
            System.out.println("You failed the execution");
        }

    }

    public void focus() {
        setStat(getStat() + 2);
        System.out.println("You increased your primary stat by +2");
    }


}
