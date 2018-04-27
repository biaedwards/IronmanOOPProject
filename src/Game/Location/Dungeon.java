package Game.Location;

import Game.Location.Difficulty;
import Game.Location.Location;

import java.util.ArrayList;

public class Dungeon extends Location {

  double coefficient;
  int enemiesNumbers;
  int gold;
  int XP;

 private ArrayList<Enemy> enemies = new ArrayList<>();

  public Dungeon(Difficulty difficulty) {

    switch (difficulty) {
      case EASY:
        coefficient = 0.8;
        enemiesNumbers = 5;
        gold = 100;
        XP = 50;
        break;
      case MEDIUM:
        coefficient = 1.0;
        enemiesNumbers = 10;
        gold = 300;
        XP = 200;
        break;
      case HARD:
        coefficient = 1.2;
        enemiesNumbers = 20;
        gold = 500;
        XP = 300;
        break;
      default:
        coefficient = 1.0;
        enemiesNumbers = 10;
        break;
    }
    enemies = generateEnemies();
  }

  public int getGold() {
    return gold;
  }

  public int getXP() {
    return XP;
  }

  public ArrayList<Enemy> getEnemies() {
    return enemies;
  }

  private ArrayList generateEnemies() {
    for (int i = 0; i < enemiesNumbers; i++) {
      enemies.add(new Enemy((int) coefficient));
    }return enemies;
  }
}
