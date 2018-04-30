package Game;

import Game.*;
import Game.Entities.*;
import Game.Exceptions.NoSuchHero;
import Game.Items.HPPotion;
import Game.Items.Names;
import Game.Location.Difficulty;
import Game.Location.Dungeon;
import Game.Location.Shop.Shop;
import Game.Location.Town;
import javafx.scene.shape.Arc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static Game.Message.*;

public class Engine {
    private HashSet<Hero> heroes = new HashSet<>();
    private Hero currentHero;
    private Dungeon currentDungeon;
    private Town currentTown;
    private int attack;
    private boolean specialUsed;
    private Scanner in = new Scanner(System.in);

    Engine() throws InterruptedException {
        new Names();
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
        HPPotion potion = new HPPotion("Small potion (starter)", 50);
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
        System.out.println(currentHero.statsToString());
        //Thread.sleep(3000);
    }

    private void visitDungeon(Difficulty difficulty) throws InterruptedException {
        currentDungeon = new Dungeon(difficulty);
        for (Enemy enemy : currentDungeon.getEnemies()) {
            specialUsed = false;
            if (currentHero.getCurrentHP() <= 0) {
                System.out.println("You have died :(");
                System.out.println("Game over");
                return;
            }
            System.out.printf("You explore the Dungeon and stumble upon %s with stats:\n\n", enemy.getName());
            enemy.showStats();

            while (true) {
                attack=0;
                ArrayList<Skill> unusedSkills = new ArrayList<>();
                unusedSkills.addAll(currentHero.getSkills());
                combatMenu(enemy, unusedSkills);
                enemy.takeDamage(attack);
                System.out.printf("You dealt %d damage to the enemy\n\n", attack);
                if (enemy.getCurrentHP() <= 0) {
                    System.out.println("You have killed the enemy!\n");
                    enemy.dropReward(currentHero);
                    currentHero.level();
                    break;

                }
                System.out.printf("The enemy has %d/%d HP remaining\n", enemy.getCurrentHP(), enemy.getMaxHP());

                attack = enemy.attack();
                currentHero.takeDamage(attack);
                System.out.printf("The enemy dealt %d damage to you\n", (attack - currentHero.getDefence()));
                if (currentHero.getCurrentHP() <= 0) {
                    System.out.println("You have died :(");
                    System.out.println("Game over");

                    return;
                }
                System.out.printf("You have %d/%d HP remaining\n", currentHero.getCurrentHP(), currentHero.getMaxHP());


            }
            betweenFightMenu();
        }
        System.out.println("You have CLEARED the Dungeon!!");
        currentHero.setXp(currentDungeon.getXP());
        currentHero.setGold(currentDungeon.getGold());
        System.out.printf("You received %d gold and gained %d XP\n", currentDungeon.getGold(), currentDungeon.getXP());
        visitLocation();
    }

    private void combatMenu(Enemy enemy, ArrayList<Skill> unusedSkills) {
        System.out.println("\nMake your choice by pressing a number. \n1: Attack \n2: Cast skill\n3: Cast class skill");
        switch (in.nextInt()) {
            case 1:
                attack = currentHero.attack();
                return;
            case 2:
                if (currentHero.getSkills().isEmpty()) {
                    System.out.println("You have no learned skill, so you will have to attack this time.");
                    attack = currentHero.attack();
                    return;
                }
                System.out.println("Choose one of your skills to use this time by pressing a number. WARNING: You can only use a skill once in a battle.");
                int x = 1;
                for (Skill skill : unusedSkills) {
                    System.out.printf("%d: %s\n", x++, skill.toString());
                }
                x = in.nextInt();
                attack = currentHero.castSkill(unusedSkills.get(x-1));
                unusedSkills.remove(x-1);
                return;
            case 3:
                if (!specialUsed) {
                    if (currentHero.getType() == PlayerType.ARCHER) {
                        System.out.printf("1. Attempt to execute the enemy\n");
                        System.out.printf("2. Focus\n");
                        switch (in.nextInt()) {
                            case 1:
                                ((Archer) currentHero).execute(enemy);
                                specialUsed = true;
                                break;
                            case 2:
                                ((Archer) currentHero).focus();
                                specialUsed = true;

                                break;
                        }
                    }
                    if (currentHero.getType() == PlayerType.MAGE) {
                        System.out.printf("1. Defensive spell\n");
                        System.out.printf("2. Multicast\n");
                        switch (in.nextInt()) {
                            case 1:
                                ((Mage) currentHero).spell();
                                specialUsed = true;

                                break;
                            case 2:
                                ((Mage) currentHero).multicast();
                                specialUsed = true;

                                break;
                        }
                    }
                    if (currentHero.getType() == PlayerType.WARRIOR) {
                        System.out.printf("1. Buff\n");
                        System.out.printf("2. Bash the enemy\n");
                        switch (in.nextInt()) {
                            case 1:
                                ((Warrior) currentHero).buff();
                                specialUsed = true;

                                break;
                            case 2:
                                ((Warrior) currentHero).bash();
                                specialUsed = true;
                                break;
                        }
                    }
                } else {
                    System.out.println("You have already used your class skill");
                }

        }


    }

    private void betweenFightMenu() throws InterruptedException {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("Choose your next move by pressing a number. \n1. Use an item.\n2. Equip a new item.\n3. Continue to fight. ");
            switch (in.nextInt()) {
                case 1:
                    System.out.println("Here are your usable items. Pick one by pressing a number.");
                    currentHero.printUsables();
                    currentHero.useUsables(in.nextInt());
                    currentHero.level();
                    break;
                case 2:
                    System.out.println("Here are your equipable items. Pick one by pressing a number.");
                    currentHero.printEquipables();
                    currentHero.equipEquipables(in.nextInt());
                    System.out.println("Your stats have changed. Here is what they look like now.");
                    showMyStats();
                    break;
                default:
                    inMenu = false;
                    break;
            }


        }
    }

    private void visitLocation() {
        System.out.println("1. Visit the town");
        System.out.println("2. Visit a dungeon");
        switch (in.nextInt()) {
            case 1: visitTown();
            break;
        }

    }

    private void visitTown() {
        currentTown = new Town();
        int counter = 1;
        for (Shop shop : currentTown.getShops()) {
            System.out.printf("%d: %s\n", counter, shop.getName());
            counter++;
        }
        counter = in.nextInt();
        currentTown.getShops().get(counter-1).printInventory();



    }

    private void showMyInventory() {
        currentHero.getInventory().entrySet().forEach(System.out::println);
    }
}
