package game;

public class Skill {
  private PlayerType type;
  private String name;
  private int damage;
  private boolean usesWeapon;
  private int level;


  public Skill(PlayerType type, String name, int damage, boolean usesWeapon, int level) {
    this.type = type;
    this.name = name;
    this.damage = damage;
    this.usesWeapon = usesWeapon;
    this.level = level;
  }

  public PlayerType getType() {
    return type;
  }

  public int getDamage() {
    return damage;
  }

  public boolean usesWeapon() {
    return usesWeapon;
  }

  public int getLevel() {
    return level;
  }

  @Override
  public String toString() {
    return String.format("%s with damage %d", name, damage);
  }

  String getName() {
    return name;
  }

}

