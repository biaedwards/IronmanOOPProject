package Game.Hero;


import Game.Items.Helmet;
import Game.Items.Vest;
import Game.Items.Weapon;
import Game.Location.Enemy;
import Game.PlayerType;
import Game.Stat;

public class Archer extends Hero {

    public Archer(String name) {
        super(name);
        setType(PlayerType.ARCHER);
        setPrimaryStat(Stat.AGILITY);
        createDefaultInventory();
    }

    public String toString() {
        return String.format("%s, Level %d Archer", this.getName(), this.getLevel());
    }

    public void createDefaultInventory() {
        this.setWeapon(new Weapon("Default", 0, 20, getType()));
        this.setHelmet(new Helmet("Default", 0, 10, 3, getType()));
        this.setVest(new Vest("Default", 0, 30, 15, getType()));
        addToInventory(getWeapon());
        addToInventory(getHelmet());
        addToInventory(getVest());
    }

    @Override
    public void level() {
        if (getXp() >= getXpUntilNextlevel()) {
            setLevel(getLevel() + 1);
            setXp(getXp() - getXpUntilNextlevel());
            setXpUntilNextlevel(getXpUntilNextlevel() * 1.5);
            setMaxHP(getMaxHP() + 15);
            setStat(getStat() + 5);
        }
    }

    public boolean execute(Enemy enemy){
        if(enemy.getCurrentHP()*100/enemy.getMaxHP()<=15){
            System.out.printf("You have executed %s!!!!\n", enemy.getName());
            return true;
        }
        else{
            System.out.println("You failed the execution");
            return false;
        }

    }
    public void focus(){
        setStat(getStat()+2);
        System.out.println("You increased your primary stat by +2");
    }


}
