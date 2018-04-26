package Game;

import Game.Enemy.Enemy;
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
            heroes.add(new Warrior(name));
        } else if(type==PlayerType.MAGE){
            heroes.add(new Mage(name));
        } else if(type==PlayerType.ARCHER){
            heroes.add(new Archer(name));
        }
    }

    public void showMyHeroes(){
        heroes.forEach(System.out::println);
    }

    public void chooseCurrentHero(String name){
        for (Hero hero:heroes){
            if(hero.getName().equals(name)){
                currentHero = hero;
                System.out.printf("Hero is set to %s", hero);
            }
        }
    }

    private void generateAllSkills(){



    }

    private void generateAllItems(){



    }

    private void generateAllEnemies(){


    }
}
