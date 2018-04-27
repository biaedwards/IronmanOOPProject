package Game.Location;

import Game.Location.Difficulty;
import Game.Location.Location;

import java.util.ArrayList;

public class Dungeon extends Location {

  double coefficient;
  int enemiesNumbers;

  ArrayList<Enemy> enemies = new ArrayList<>();

  public Dungeon(Difficulty difficulty){

    switch (difficulty){
      case EASY: coefficient = 0.8;
      enemiesNumbers = 5;
      break;
      case MEDIUM: coefficient = 1.0;
      enemiesNumbers = 10;
      break;
      case HARD: coefficient = 1.2;
      enemiesNumbers = 20;
    }

  }void generateEnemies(){
    for (int i = 0; i < enemiesNumbers; i++) {
       enemies.add(new Enemy(100.00*coefficient));

    }

  }

}
