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
                coefficient = 1;
                enemiesNumbers = 4;
                System.out.printf("You will face %d enemies.\n", enemiesNumbers);
                gold = 1500;
                XP = 1000;
                break;
            case MEDIUM:
                coefficient = 1.5;
                enemiesNumbers = 6;
                System.out.printf("You will face %d enemies.\n", enemiesNumbers);

                gold = 2500;
                XP = 2000;
                break;
            case HARD:
                coefficient = 2;
                enemiesNumbers = 8;
                System.out.printf("You will face %d enemies.\n", enemiesNumbers);

                gold = 4000;
                XP = 5000;
                break;
            case FIRST:
                coefficient = 0.25;
                enemiesNumbers = 2;
                gold = 1000;
                XP = 500;
                System.out.printf("You will face %d enemies.\n\n\n", enemiesNumbers);
                break;

            default:
                coefficient = 0.5;
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
