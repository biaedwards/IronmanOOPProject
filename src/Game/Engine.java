package Game;

import Game.Exceptions.NoSuchHero;
import Game.Hero.Archer;
import Game.Hero.Hero;
import Game.Hero.Mage;
import Game.Hero.Warrior;
import Game.Items.Item;
import Game.Location.Enemy;

import java.util.HashSet;
public class Engine {
    HashSet<Skill> allSkills = new HashSet<>();
    HashSet<Item> allItems = new HashSet<>();
    HashSet<Enemy> allEnemies = new HashSet<>();
    HashSet<Hero> heroes = new HashSet<>();
    Hero currentHero;

    Engine(){

    };

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
        heroes.forEach(System.out::println);
    }

    public void chooseCurrentHero(String name) throws NoSuchHero{
            for (Hero hero:heroes){
                if(hero.getName().equals(name)){
                    currentHero = hero;
                    System.out.printf("Hero is set to %s", hero);
                    return;
                }
            }
            throw new NoSuchHero();
    }

    public void showMyStats(){
        System.out.println("HP: "+currentHero.getCurrentHP());
        System.out.println("XP: "+ currentHero.getXp());
        System.out.println("Level: "+ currentHero.getLevel());
        System.out.println("XP until next level: "+ currentHero.getXpUntilNextlevel());
        System.out.println("Defence: "+ currentHero.getDefence());
        System.out.println("Damage: "+ currentHero.getDamage());
        System.out.println("Gold: "+ currentHero.getGold());
        System.out.println("Weapon: "+ currentHero.getWeapon().toString());
        System.out.println("Helmet: "+ currentHero.getHelmet().toString());
        System.out.println("Vest: "+ currentHero.getVest().toString());
    }

    public void showMyInventory(){
        currentHero.getInventory().entrySet().forEach(System.out::println);
    }
}
