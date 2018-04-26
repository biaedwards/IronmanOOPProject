package Game.Hero;

import Game.PlayerType;
import Game.Skill;
import Game.Stat;

public class Warrior extends Hero {

    public Warrior(String name) {
        super(name);
        setType(PlayerType.WARRIOR);
        setPrimaryStat(Stat.STRENGTH);
    }

    @Override
    public String toString(){
        return String.format("%s, Level %d Warrior", getName(), getLevel());
    }

    @Override
    public void level() {
        if (getXp() >= getXpUntilNextlevel()) {
            setLevel(getLevel() + 1);
            setXp(getXp() - getXpUntilNextlevel());
            setXpUntilNextlevel(getXpUntilNextlevel() * 1.5);
            setMaxHP(getMaxHP() + 25);
            setDefence(getDefence()+1);
            setStat(getStat() + 3);
        }
    }

    public void buff(){
        setDefence(getDefence()+1);
        setCurrentHP(getCurrentHP()+20);
        if (getCurrentHP()>getMaxHP()){
            setCurrentHP(getMaxHP());
        }
        System.out.println("You have buffed yourself and received +1 defence and +20 HP");

    }

    public int bash(){
        System.out.printf("You bashed the enemy, dealing %d damage and made it skip a turn\n",getDamage()+10);
        return getDamage()+10;
    }
}
