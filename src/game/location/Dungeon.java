package game.location;

import game.entities.Enemy;

import java.util.ArrayList;

public class Dungeon {

  private double coefficient;
  private int enemiesNumbers;
  private int gold;
  private int xp;

  private ArrayList enemies = new ArrayList<>();

  /**
   * Public constructor for with Difficulty difficulty parameter.
   * */
  public Dungeon(Difficulty difficulty) {

    switch (difficulty) {
      case EASY:
        coefficient = 1;
        enemiesNumbers = 4;
        System.out.printf("You will face %d enemies.\n", enemiesNumbers);
        gold = 1500;
        xp = 2000;
        break;
      case MEDIUM:
        coefficient = 2;
        enemiesNumbers = 6;
        System.out.printf("You will face %d enemies.\n", enemiesNumbers);

        gold = 2500;
        xp = 3000;
        break;
      case HARD:
        coefficient = 3;
        enemiesNumbers = 8;
        System.out.printf("You will face %d enemies.\n", enemiesNumbers);

        gold = 4000;
        xp = 5000;
        break;
      case FIRST:
        coefficient = 0.25;
        enemiesNumbers = 2;
        gold = 1000;
        xp = 500;
        System.out.printf("You will face %d enemies.\n\n\n", enemiesNumbers);
        break;

      default:
        coefficient = 0.5;
        enemiesNumbers = 10;
        gold = 100;
        xp = 100;
        break;
    }
    enemies = generateEnemies();
  }

  public int getGold() {
    return gold;
  }

  public int getXp() {
    return xp;
  }

  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }

  private ArrayList generateEnemies() {
    for (int i = 0; i < enemiesNumbers; i++) {
      enemies.add(new Enemy(coefficient));
    }
    return enemies;
  }
}
