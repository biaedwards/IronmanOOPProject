package Game.Location;

import Game.Entities.Enemy;

import java.util.ArrayList;

public class Dungeon {

    private double coefficient;
    private int enemiesNumbers;
    private int gold;
    private int XP;

    private ArrayList enemies = new ArrayList<>();

    public Dungeon(Difficulty difficulty) {

        switch (difficulty) {
            case EASY:
                coefficient = 0.8;
                enemiesNumbers = 4;
                System.out.printf("You will face %d enemies.\n", enemiesNumbers);
                gold = 100;
                XP = 50;
                break;
            case MEDIUM:
                coefficient = 1.0;
                enemiesNumbers = 6;
                System.out.printf("You will face %d enemies.\n", enemiesNumbers);

                gold = 300;
                XP = 200;
                break;
            case HARD:
                coefficient = 1.2;
                enemiesNumbers = 10;
                System.out.printf("You will face %d enemies.\n", enemiesNumbers);

                gold = 500;
                XP = 300;
                break;
            case FIRST:
                coefficient = 0.5;
                enemiesNumbers = 2;
                gold = 100;
                XP = 100;
                System.out.printf("You will face %d enemies.\n\n\n", enemiesNumbers);
                break;

            default:
                coefficient = 1.0;
                enemiesNumbers = 10;
                gold = 100;
                XP = 100;
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
            enemies.add(new Enemy(coefficient));
        }
        return enemies;
    }
}
