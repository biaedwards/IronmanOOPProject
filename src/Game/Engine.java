package Game;

import Game.Exceptions.NoSuchHero;
import Game.Hero.Archer;
import Game.Hero.Hero;
import Game.Hero.Mage;
import Game.Hero.Warrior;
import Game.Items.HPPotion;
import Game.Items.Item;
import Game.Location.Difficulty;
import Game.Location.Dungeon;
import Game.Location.Enemy;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static Game.Message.*;

public class Engine {
    private HashSet<Hero> heroes = new HashSet<>();
    private Hero currentHero;
    private Dungeon currentDungeon;
    private int attack;
    private Scanner in = new Scanner(System.in);

    Engine() throws InterruptedException {
        start();
    }

    private void start() throws InterruptedException {
        System.out.println(startMessage);
        String name = in.nextLine();
        System.out.println(chooseHeroType);
        addHero(name, getHeroTypes(in.nextInt()));
        System.out.println(firstHeroAdded);
        showMyStats();
        System.out.println(firstDungeon);
        HPPotion potion = new HPPotion("Small potion (starter)",50);
        currentHero.addToInventory(potion);
        currentHero.addToInventory(potion);
        visitDungeon(Difficulty.FIRST);
    }

    private PlayerType getHeroTypes(int number) {
        switch (number) {
            case 1:
                return PlayerType.WARRIOR;
            case 2:
                return PlayerType.MAGE;
            case 3:
                return PlayerType.ARCHER;
            default:
                return PlayerType.MAGE;
        }
    }

    private void addHero(String name, PlayerType type) {
        if (type == PlayerType.WARRIOR) {
            currentHero = new Warrior(name);
            heroes.add(currentHero);
        } else if (type == PlayerType.MAGE) {
            currentHero = new Mage(name);
            heroes.add(currentHero);
        } else if (type == PlayerType.ARCHER) {
            currentHero = new Archer(name);
            heroes.add(currentHero);
        }
    }

    private void showMyHeroes() {
        heroes.forEach(System.out::println);
    }

    private void chooseCurrentHero(String name) throws NoSuchHero {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {
                currentHero = hero;
                System.out.printf("Hero is set to %s", hero);
                return;
            }
        }
        throw new NoSuchHero();
    }

    private void showMyStats() throws InterruptedException {
        System.out.println("HP: " + currentHero.getCurrentHP());
        System.out.println("XP: " + currentHero.getXp());
        System.out.println("Level: " + currentHero.getLevel());
        System.out.println("XP until next level: " + currentHero.getXpUntilNextlevel());
        System.out.println("Defence: " + currentHero.getDefence());
        System.out.println("Damage: " + currentHero.getDamage());
        System.out.println("Gold: " + currentHero.getGold());
        System.out.println("Weapon: " + currentHero.getWeapon().toString());
        System.out.println("Helmet: " + currentHero.getHelmet().toString());
        System.out.println("Vest: " + currentHero.getVest().toString());
        //Thread.sleep(3000);
    }

    private void showEnemyStats(Enemy enemy) throws InterruptedException {
        System.out.println("HP: " + enemy.getCurrentHP());
        System.out.println("Damage: " + enemy.getDamage());
        System.out.println("Gold reward:" + enemy.getGold());
        System.out.println("XP reward:" + enemy.getXP());
        System.out.println("Item reward: ???\n\n");
        //Thread.sleep(3000);

    }

    private void visitDungeon(Difficulty difficulty) throws InterruptedException {
        currentDungeon = new Dungeon(difficulty);
        for (Enemy enemy : currentDungeon.getEnemies()) {
            if (checkIfDead(currentHero.getCurrentHP())) {
                System.out.println("You have died :(");
                System.out.println("Game over");
                return;
            }
            System.out.printf("You explore the Dungeon and stumble upon %s\n\n", enemy.getName());
            showEnemyStats(enemy);
            while (enemy.getCurrentHP() > 0) {
                combatMenu();
                enemy.takeDamage(attack);
                System.out.printf("You dealt %d damage to the enemy\n\n", attack);
                if (checkIfDead(enemy.getCurrentHP())) {
                    System.out.println("You have killed the enemy!\n");
                    enemy.dropReward(currentHero);
                    currentHero.level();
                    break;

                }
                System.out.printf("The enemy has %d/%d HP remaining\n", enemy.getCurrentHP(), enemy.getMaxHP());

                attack = enemy.attack();
                currentHero.takeDamage(attack);
                System.out.printf("The enemy dealt %d damage to you\n", (attack-currentHero.getDefence()));
                if (checkIfDead(currentHero.getCurrentHP())) {
                    System.out.println("You have died :(");
                    System.out.println("Game over");

                    return;
                }
                System.out.printf("You have %d/%d HP remaining\n", currentHero.getCurrentHP(), currentHero.getMaxHP());


            }
            betweenfightMenu();


        }
        System.out.println("You have CLEARED the Dungeon!!");
        currentHero.setXp(currentDungeon.getXP());
        currentHero.setGold(currentDungeon.getGold());
        System.out.printf("You received %d gold and gained %d XP\n", currentDungeon.getGold(),currentDungeon.getXP());

    }

    private boolean checkIfDead(int number) {
        if (number <= 0) {
            return true;
        } else {
            return false;
        }

    }

    private void combatMenu() {
        System.out.println("\n1: Attack");
        System.out.println("2: Cast skill");
        switch (in.nextInt()) {
            case 1:
                attack = currentHero.attack();
                return;
            case 2:
                attack = currentHero.castSkill(currentHero.getAllSkills().get(0));
                return;
        }


    }

    private void betweenfightMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("1. Use an item");
            System.out.println("2. Equip a new item");
            System.out.println("3. Continue");
            switch (in.nextInt()) {
                case 1:
                    currentHero.printUsables();
                    currentHero.useUsables(in.nextInt());
                    break;
                case 2:
                    currentHero.printEquipables();
                    currentHero.equipEquipables(in.nextInt());
                    break;
                default:
                    inMenu=false;
                    break;

            }


        }
    }

    private void showMyInventory() {
        currentHero.getInventory().entrySet().forEach(System.out::println);
    }
}
