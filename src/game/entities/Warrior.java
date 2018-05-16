package game.entities;

import game.PlayerType;
import game.Stat;
import game.items.Helmet;
import game.items.Vest;
import game.items.Weapon;


public class Warrior extends Hero {

  /**
   * Public constructor with String name parameter.
   * */
  public Warrior(String name) {
    super(name);
    setType(PlayerType.WARRIOR);
    setPrimaryStat(Stat.STRENGTH);
    createDefaultInventory();
  }

  /**
   * Special class method for Hero Type Warrior.
   * */
  public void buff() {
    setDefence(getDefence() + 1);
    changeCurrentHp(20);
    System.out.println("You have buffed yourself and received +1 defence and +20 HP");
  }

  /**
   * Special class method for Hero Type Warrior. This method is making
   * more damage than buff to the enemy.
   * */
  public void bash(Enemy enemy) {
    System.out.printf("You bashed the enemy, dealing %d damage and made it skip a turn\n",
        attack() + 10);
    enemy.takeDamage(attack() + 10);
    enemy.setBashed(true);
  }

  @Override
  public String toString() {
    return String.format("%s, Level %d Warrior", getName(), getLevel());
  }

  @Override
  public void createDefaultInventory() {
    this.setWeapon(new Weapon("Default sword", 0, 20, this.getType()));
    this.setHelmet(new Helmet("Default helmet", 0, 5, 0));
    this.setVest(new Vest("Default vest", 0, 5, 0));
  }

  @Override
  public void level() {
    if (xp >= getXpUntilNextlevel()) {
      setLevel(getLevel() + 1);
      xp -= getXpUntilNextlevel();
      setXpUntilNextlevel((int) (getXpUntilNextlevel() * 1.5));
      setMaxHp(getMaxHp() + 25);
      setDefence(getDefence() + 1);
      setStat(getStat() + 3);
      super.level();
    }
  }
}
