package game.entities;

import game.PlayerType;
import game.Stat;
import game.items.Helmet;
import game.items.Vest;
import game.items.Weapon;


public class Archer extends Hero {

  /**
   * Archer is one of the Hero types.
   */

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
    this.setWeapon(new Weapon("Default bow", 0, 20, getType()));
    this.setHelmet(new Helmet("Default helmet", 0, 5, 0));
    this.setVest(new Vest("Default vest", 0, 5, 0));

  }

  @Override
  public void level() {
    if (xp >= getXpUntilNextlevel()) {
      setLevel(getLevel() + 1);
      xp -= getXpUntilNextlevel();
      setXpUntilNextlevel((int) (getXpUntilNextlevel() * 1.5));
      setMaxHp(getMaxHp() + 15);
      setStat(getStat() + 5);
      super.level();
    }
  }

  /**
   * This method is checking if the Enemy is still alive.
   */
  public void execute(Enemy enemy) {
    if (enemy.getCurrentHp() * 100 / enemy.getMaxHp() <= 25) {
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

