package Game;

import Game.Enemy.Enemy;
import Game.Exceptions.NoSuchHero;
import Game.Hero.Archer;
import Game.Hero.Hero;
import Game.Hero.Mage;
import Game.Hero.Warrior;
import Game.Items.Item;

import java.util.HashSet;
public class Engine {
    HashSet<Skill> allSkills = new HashSet<>();
    HashSet<Item> allItems = new HashSet<>();
    HashSet<Enemy> allEnemies = new HashSet<>();
    HashSet<Hero> heroes = new HashSet<>();
    Hero currentHero;

    Engine(){
        generateAllEnemies();
        generateAllItems();
        generateAllSkills();
    }

    public void addHero(String name, PlayerType type){
        if(type==PlayerType.WARRIOR){
            currentHero = new Warrior(name);
            heroes.add(currentHero);
        } else if(type==PlayerType.MAGE){
            currentHero = new Mage(name);
            heroes.add(currentHero);
        } else if(type==PlayerType.ARCHER){
            currentHero = new Archer(name);
            heroes.add(currentHero);
        }
    }

    public void showMyHeroes(){
        if(heroes.isEmpty()){
            System.out.println("You have no heroes. Create one now.");
            return;
        }
        heroes.forEach(System.out::println);
    }

    public void chooseCurrentHero(String name){
        try{
            for (Hero hero:heroes){
                if(hero.getName().equals(name)){
                    currentHero = hero;
                    System.out.printf("Hero is set to %s", hero);
                }
                else {
                    throw new NoSuchHero();
                }
            }
        }
        catch (NoSuchHero ex){
            System.out.println("You don't have this hero. Either create it or choose one of the above: ");
            showMyHeroes();
        }
    }

    private void generateAllSkills(){



    }

    private void generateAllItems(){



    }

    private void generateAllEnemies(){


    }
}
