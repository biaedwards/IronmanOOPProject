package Game.Hero;

import Game.Items.Helmet;
import Game.Items.Vest;
import Game.Items.Weapon;
import Game.PlayerType;
import Game.Stat;

public class Mage extends Hero {


    public Mage(String name) {
        super(name);
        setType(PlayerType.MAGE);
        setPrimaryStat(Stat.INTELLIGENCE);
        createDefaultInventory();
    }

    @Override
    public void createDefaultInventory() {
        this.setWeapon(new Weapon("Default", 0, 20, super.getType()));
        this.setHelmet(new Helmet("Default", 0, 10, 3));
        this.setVest(new Vest("Default", 0, 30, 15));
        addToInventory(getWeapon());
        addToInventory(getHelmet());
        addToInventory(getVest());
    }

    public String toString() {
        return String.format("%s, Level %d Mage", this.getName(), this.getLevel());
    }

    @Override
    public void level() {
        if (getXp() >= getXpUntilNextlevel()) {
            setLevel(getLevel() + 1);
            setXp(getXp() - getXpUntilNextlevel());
            setXpUntilNextlevel(getXpUntilNextlevel() * 1.5);
            setMaxHP(getMaxHP() + 10);
            setStat(getStat() + 3);
        }
    }

    public void spell(){
        changeCurrentHP(30);
        setStat(getStat()+1);
        System.out.println("You cast a defensive spell which increases your HP by 30 and your primary stat by 1");
    }

    public void multicast(){
        System.out.println("You can cast 2 spells this turn");
    }




}
