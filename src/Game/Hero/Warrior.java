package Game.Hero;

import Game.Items.Helmet;
import Game.Items.Vest;
import Game.Items.Weapon;
import Game.PlayerType;
import Game.Stat;

public class Warrior extends Hero {

    public Warrior(String name) {
        super(name);
        setType(PlayerType.WARRIOR);
        setPrimaryStat(Stat.STRENGTH);
        createDefaultInventory();
        learnSkill(getAllSkills().get(1));
    }

    @Override
    public String toString() {
        return String.format("%s, Level %d Warrior", getName(), getLevel());
    }

    public void createDefaultInventory() {
        this.setWeapon(new Weapon("Default", 0, 20, this.getType()));
        this.setHelmet(new Helmet("Default", 0, 5, 0));
        this.setVest(new Vest("Default", 0, 5, 0));
    }

    @Override
    public void level() {
        if (getXp() >= getXpUntilNextlevel()) {
            setLevel(getLevel() + 1);
            setXp(getXp() - getXpUntilNextlevel());
            setXpUntilNextlevel(getXpUntilNextlevel() * 1.5);
            setMaxHP(getMaxHP() + 25);
            setDefence(getDefence() + 1);
            setStat(getStat() + 3);
        }
    }

    public void buff() {
        setDefence(getDefence() + 1);
        changeCurrentHP(20);
        System.out.println("You have buffed yourself and received +1 defence and +20 HP");

    }

    public int bash() {
        System.out.printf("You bashed the enemy, dealing %d damage and made it skip a turn\n", attack() + 10);
        return attack() + 10;
    }
}
