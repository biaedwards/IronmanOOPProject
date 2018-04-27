package Game;

import Game.Items.Helmet;
import Game.Items.Item;
import Game.Items.Vest;
import Game.Items.Weapon;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Engine engine = new Engine();

    System.out.println("Hello and welcome to Team IRONMAN's video game!\n \n To start a new game you must first choose a name for your hero by typing it here: ");
    String name = in.nextLine();
    System.out.println("Great! Now choose the type of your hero by pressing 1 for Warrior, 2 for Mage or 3 for Archer.");
    switch (in.nextInt()) {
      case 1:
        engine.addHero(name, PlayerType.WARRIOR);
        break;
      case 2:
        engine.addHero(name, PlayerType.MAGE);
        break;
      case 3:
        engine.addHero(name, PlayerType.ARCHER);
        break;
    }
    System.out.println("That's an awesome choice! Here are your current stats, which you can improve on as you play.\n");
    engine.showMyStats();
    System.out.println("\n Now it's time for the real fun to begin. You don't have any gold yet, so let's go to a dungeon to win some.\n  This time we will start with an easy one but from now on you will be able to choose the difficulty yourself!\n");
  }
}
