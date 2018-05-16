package game;

import static game.Message.chooseHeroType;
import static game.Message.firstDungeon;
import static game.Message.heroAdded;
import static game.Message.newHero;
import static game.Message.startMessage;

import game.entities.Archer;
import game.entities.Enemy;
import game.entities.Hero;
import game.entities.Mage;
import game.entities.Warrior;

import game.exceptions.InvalidInputException;
import game.items.HpPotion;
import game.items.Item;
import game.items.Names;
import game.location.Difficulty;
import game.location.Dungeon;
import game.location.Town;
import game.location.shop.Shop;

import java.util.ArrayList;
import java.util.Scanner;

class Engine {
  private ArrayList<Hero> heroes = new ArrayList<>();
  private Hero currentHero;
  private int attack;
  private boolean specialUsed;
  private boolean multicastUsed;
  private Scanner in = new Scanner(System.in);

  Engine() {
    new Names();
    System.out.println(startMessage);
    start();
  }

  private void start() {
    System.out.println(newHero);
    String name = in.nextLine();
    System.out.println(chooseHeroType);
    addHero(name, getHeroTypes(validateInput(in.nextLine(), 3)));
    System.out.println(heroAdded);
    showMyStats();
    System.out.println(firstDungeon);
    HpPotion potion = new HpPotion("Small potion (starter)", 50);
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
    int counter = 1;
    System.out.println("Here are your heroes to choose from.");
    for (Hero hero : heroes) {
      System.out.printf("%d: %s\n", counter++, hero.toString());
    }
  }

  private void chooseCurrentHero(int number) {
    int counter = 1;
    for (Hero hero : heroes) {
      if (counter == number) {
        currentHero = hero;
        return;
      }
      counter++;
    }
  }

  private void showMyStats() {
    System.out.println(currentHero.statsToString());
  }

  private void visitDungeon(Difficulty difficulty) {
    Dungeon currentDungeon = new Dungeon(difficulty);
    for (Enemy enemy : currentDungeon.getEnemies()) {
      ArrayList<Skill> unusedSkills = new ArrayList<>(currentHero.getSkills());
      specialUsed = false;
      if (currentHero.getCurrentHp() <= 0) {
        System.out.println("You have died :(");
        System.out.println("game over");
        System.out.println("Press a number depending on what you want to do next.");
        System.out.println("1: Quit the game.");
        System.out.println("2: Continue playing with another hero.");
        switch (validateInput(in.nextLine(), 2)) {
          case 1:
            return;
          case 2:
            showMyHeroes();
            System.out.printf("Press the corresponding number for your chosen hero or "
                + "%d to add a new hero.", heroes.size() + 1);
            int choice = validateInput(in.nextLine(), heroes.size() + 1);
            if (choice == heroes.size() + 1) {
              start();
            } else {
              chooseCurrentHero(choice);
              System.out.println("Great! Now choose what to do next.");
              visitLocation();
            }
            break;
          default:
            return;
        }
      }
      System.out.printf("You explore the Dungeon and stumble upon %s with stats:\n\n",
          enemy.getName());
      enemy.showStats();

      while (true) {
        attack = 0;
        combatMenu(enemy, unusedSkills);
        if (attack != 0) {
          enemy.takeDamage(attack);
          System.out.printf("You dealt %d damage to the enemy\n\n", attack);
        }
        if (enemy.getCurrentHp() <= 0) {
          System.out.println("You have killed the enemy!\n");
          enemy.dropReward(currentHero);
          break;

        }
        System.out.printf("The enemy has %d/%d HP remaining\n",
            enemy.getCurrentHp(), enemy.getMaxHp());
        if (enemy.isBashed()) {
          enemy.setBashed(false);
          continue;
        }

        attack = enemy.attack();
        currentHero.takeDamage(attack);
        System.out.printf("The enemy dealt %d damage to you\n",
            (attack - currentHero.getDefence()) > 0 ? attack - currentHero.getDefence() : 1);
        if (currentHero.getCurrentHp() <= 0) {
          System.out.println("You have died :(");
          System.out.println("game over");

          return;
        }
        System.out.printf("You have %d/%d HP remaining\n",
            currentHero.getCurrentHp(), currentHero.getMaxHp());
      }
      betweenFightMenu();
    }
    System.out.println("You have CLEARED the Dungeon!!");
    System.out.printf("You received %d gold and gained %d XP\n",
        currentDungeon.getGold(), currentDungeon.getXp());
    currentHero.setXp(currentDungeon.getXp());
    currentHero.setGold(currentDungeon.getGold());
    System.out.println("Choose what to do next by pressing a number.");
    visitLocation();
  }

  private int validateInput(String input, int limit) {
    try {
      for (int i = 0; i < input.length(); i++) {
        if (input.charAt(i) < '0' || input.charAt(i) > '9') {
          throw new InvalidInputException();
        }
      }
      if (Integer.parseInt(input) > limit) {
        throw new InvalidInputException();
      }
      return Integer.parseInt(input);
    } catch (InvalidInputException e) {
      System.out.println("Your input is invalid. Please write a valid input here.");
      return validateInput(in.nextLine(), limit);
    }
  }

  private void combatMenu(Enemy enemy, ArrayList<Skill> unusedSkills) {
    System.out.println("\nMake your choice by pressing a number. \n"
        + "1: Attack \n2: Cast skill\n3: Cast class skill\n");
    switch (validateInput(in.nextLine(), 3)) {
      case 1:
        attack = currentHero.attack();
        return;
      case 2:
        int counter = 0;
        do {
          if (currentHero.getSkills().isEmpty()) {
            System.out.println("You have no learned skill, so you will have to attack this time.");
            attack = currentHero.attack();
            return;
          }
          if (unusedSkills.isEmpty()) {
            System.out.println("You have used all of your skills in this battle, "
                + "so you will have to attack this time.");
            attack = currentHero.attack();
            return;
          }
          System.out.println("Choose one of your skills to use this time by pressing a number. "
              + "WARNING: You can only use a skill once in a battle.");
          int x = 1;
          for (Skill skill : unusedSkills) {
            System.out.printf("%d: %s\n", x++, skill.toString());
          }
          x = validateInput(in.nextLine(), unusedSkills.size());
          attack += currentHero.castSkill(unusedSkills.get(x - 1));
          if (!multicastUsed) {
            unusedSkills.remove(x - 1);
          }
          if (counter == 1) {
            multicastUsed = false;
          }
          counter++;
        } while (multicastUsed);
        return;
      case 3:
        if (!specialUsed) {
          if (currentHero.getType() == PlayerType.ARCHER) {
            System.out.print("1. Attempt to execute the enemy\n");
            System.out.print("2. Focus\n");
            switch (validateInput(in.nextLine(), 2)) {
              case 1:
                ((Archer) currentHero).execute(enemy);
                specialUsed = true;
                break;
              case 2:
                ((Archer) currentHero).focus();
                attack = currentHero.attack();
                specialUsed = true;
                break;
              default:
                ((Archer) currentHero).execute(enemy);
                specialUsed = true;
                break;
            }
          }
          if (currentHero.getType() == PlayerType.MAGE) {
            System.out.print("1. Defensive spell\n");
            System.out.print("2. Multicast\n");
            switch (validateInput(in.nextLine(), 2)) {
              case 1:
                ((Mage) currentHero).spell();
                specialUsed = true;
                break;
              case 2:
                ((Mage) currentHero).multicast();
                specialUsed = true;
                multicastUsed = true;
                break;
              default:
                ((Mage) currentHero).spell();
                specialUsed = true;
                break;
            }
          }
          if (currentHero.getType() == PlayerType.WARRIOR) {
            System.out.print("1. Buff\n");
            System.out.print("2. Bash the enemy\n");
            switch (validateInput(in.nextLine(), 2)) {
              case 1:
                ((Warrior) currentHero).buff();
                attack = currentHero.attack();
                specialUsed = true;
                break;
              case 2:
                ((Warrior) currentHero).bash(enemy);
                specialUsed = true;
                break;
              default:
                ((Warrior) currentHero).buff();
                attack = currentHero.attack();
                specialUsed = true;
                break;
            }
          }
        } else {
          System.out.println("You have already used your class skill");
        }

    }
  }

  private void betweenFightMenu() {
    boolean inMenu = true;
    while (inMenu) {
      System.out.println("Choose your next move by pressing a number. \n"
          + "1. Use an item.\n2. Equip a new item.\n3. Continue to fight. ");
      switch (validateInput(in.nextLine(), 3)) {
        case 1:
          System.out.println("Here are your usable items. Pick one by pressing a number.");
          currentHero.printUsables();
          currentHero.useUsables(validateInput(in.nextLine(), currentHero.getUsables().size()));
          break;
        case 2:
          System.out.println("Here are your equipable items. Pick one by pressing a number.");
          currentHero.printEquipables();
          currentHero.equipEquipables(validateInput(in.nextLine(),
              currentHero.getEquipables().size()));
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
    switch (validateInput(in.nextLine(), 2)) {
      case 1:
        visitTown();
        break;
      case 2:
        createDungeon();
        break;
      default:
        visitTown();
        break;
    }
  }

  private void visitTown() {
    Town currentTown = new Town();
    System.out.println("You have visited a nearby town and recovered your HP");
    currentHero.changeCurrentHp(9999);
    boolean inTown = true;
    while (inTown) {
      int counter = 1;
      for (Shop shop : currentTown.getShops()) {
        System.out.printf("%d: Buy %s\n", counter, shop.getName());
        counter++;
      }
      System.out.println("5: Sell your items");
      System.out.println("6: Show stats");
      System.out.println("7: Equip items");
      System.out.println("8: Use items");
      System.out.println("9: Leave town and go to dungeon");
      System.out.println("10: Change your hero");
      counter = validateInput(in.nextLine(), 10);
      if (counter == 5) {
        while (true) {
          System.out.println("Here are your items with their quantities indicated at the end"
              + " of each line. Choose which one to sell by pressing the corresponding number.");
          currentHero.printInventory();
          int limit = currentHero.getInventory().size() + 1;
          System.out.printf("Press %d to go back.\n", limit);
          counter = validateInput(in.nextLine(), limit);
          if (counter == limit) {
            break;
          }
          currentHero.sellItem(counter);
          System.out.printf("Gold: %d\n", currentHero.getGold());
          System.out.println("1. Sell another item");
          System.out.println("2. Return to town");
          counter = validateInput(in.nextLine(), 2);
          if (counter == 2) {
            break;
          }
        }
      } else if (counter == 6) {
        showMyStats();
      } else if (counter == 7) {
        System.out.println("Here are your equipable items. Pick one by pressing a number.");
        currentHero.printEquipables();
        currentHero.equipEquipables(validateInput(in.nextLine(),
            currentHero.getEquipables().size()));
        System.out.println("Your stats have changed. Here is what they look like now.");
        showMyStats();
      } else if (counter == 8) {
        System.out.println("Here are your usable items. Pick one by pressing a number.");
        currentHero.printUsables();
        currentHero.useUsables(validateInput(in.nextLine(), currentHero.getUsables().size()));
      } else if (counter == 9) {
        createDungeon();
      } else if (counter == 10) {
        showMyHeroes();
        System.out.printf("Press the corresponding number for your "
                + "chosen hero or %d to add a new hero.\n",
            heroes.size() + 1);
        int choice = validateInput(in.nextLine(), heroes.size() + 1);
        if (choice == heroes.size() + 1) {
          start();
        } else {
          chooseCurrentHero(choice);
          System.out.println("Great! Now choose what to do next.");
          visitLocation();
        }
      } else if (counter < 5) {
        currentTown.getShops().get(counter - 1).printInventory();
        System.out.printf("Gold: %d\n", currentHero.getGold());
        int shopIndex = counter;
        counter = validateInput(in.nextLine(),
            currentTown.getShops().get(shopIndex - 1).getInventory().size());
        int newCounter = 1;
        for (Item item : currentTown.getShops().get(shopIndex - 1).getInventory()) {
          if (newCounter == counter) {
            currentHero.buyItem(item);
            break;
          }
          newCounter++;
        }
      }
    }
    System.out.println();
  }

  private void createDungeon() {
    System.out.println("Choose which difficulty of dungeon to visit");
    System.out.println("1. Easy");
    System.out.println("2. Medium");
    System.out.println("3. Hard");
    switch (validateInput(in.nextLine(), 3)) {
      case 1:
        visitDungeon(Difficulty.EASY);
        break;
      case 2:
        visitDungeon(Difficulty.MEDIUM);
        break;
      case 3:
        visitDungeon(Difficulty.HARD);
        break;
      default:
        visitDungeon(Difficulty.MEDIUM);
        break;
    }
  }
}
