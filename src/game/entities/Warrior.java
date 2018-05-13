package game.entities;

import game.PlayerType;
import game.Stat;
import game.items.Helmet;
import game.items.Vest;
import game.items.Weapon;


public class Warrior extends Hero {

  public Warrior(String name) {
    super(name);
    setType(PlayerType.WARRIOR);
    setPrimaryStat(Stat.STRENGTH);
    createDefaultInventory();
  }

  public void buff() {
    setDefence(getDefence() + 1);
    changeCurrentHP(20);
    System.out.println("You have buffed yourself and received +1 defence and +20 HP");
  }

  public void bash(Enemy enemy) {
    System.out.printf("You bashed the enemy, dealing %d damage and made it skip a turn\n", attack() + 10);
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
      setMaxHP(getMaxHP() + 25);
      setDefence(getDefence() + 1);
      setStat(getStat() + 3);
      super.level();
    }
  }
}
