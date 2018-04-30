package Game.Items;

import Game.PlayerType;
import Game.Skill;

import java.util.ArrayList;

public class Names {
    static ArrayList<String> armorFirstPart;
    static ArrayList<String> armorSecondPart;
    public static ArrayList<String> enemiesNames;
    static ArrayList<String> weaponFirstPart;
    static ArrayList<String> weaponSecondPart;
    static ArrayList<String> weaponThirdPart;
    public static ArrayList<Skill> allSkills;

    public Names(){
        armorFirstPart = new ArrayList<>();
        armorSecondPart = new ArrayList<>();
        enemiesNames = new ArrayList<>();
        weaponFirstPart = new ArrayList<>();
        weaponSecondPart = new ArrayList<>();
        weaponThirdPart = new ArrayList<>();
        allSkills = new ArrayList<>();


        armorFirstPart.add("Paper");
        armorFirstPart.add("Cloth");
        armorFirstPart.add("Leather");
        armorFirstPart.add("Wooden");
        armorFirstPart.add("Chain");
        armorFirstPart.add("Copper");
        armorFirstPart.add("Steel");
        armorFirstPart.add("Silver");
        armorFirstPart.add("Gold");
        armorFirstPart.add("Titanium");
        armorFirstPart.add("Diamond");
        armorFirstPart.add("Epic");
        armorFirstPart.add("Godly");
        armorFirstPart.add("Legendary");


        armorSecondPart.add("Weaklings");
        armorSecondPart.add("Juniours");
        armorSecondPart.add("Nobles");
        armorSecondPart.add("Barons");
        armorSecondPart.add("Warlords");
        armorSecondPart.add("Demigods");

        enemiesNames.add("Adrian");
        enemiesNames.add("Borislav");
        enemiesNames.add("Dido");
        enemiesNames.add("Drago");
        enemiesNames.add("Edward");
        enemiesNames.add("Elizar");
        enemiesNames.add("Kiril");
        enemiesNames.add("Krasimir");
        enemiesNames.add("MariaONE");
        enemiesNames.add("MariaTWO");
        enemiesNames.add("MartinONE");
        enemiesNames.add("MartinTwo");
        enemiesNames.add("RadoONE");
        enemiesNames.add("RadoTWO");
        enemiesNames.add("Oxana");
        enemiesNames.add("Nasko");
        enemiesNames.add("Sasho");
        enemiesNames.add("Danail");
        enemiesNames.add("Irina");
        enemiesNames.add("Doncho");
        enemiesNames.add("Georgi");
        enemiesNames.add("Ivan");
        enemiesNames.add("Ivelin");
        enemiesNames.add("Ivo");
        enemiesNames.add("Krisi");
        enemiesNames.add("Lu");
        enemiesNames.add("Penyo");
        enemiesNames.add("Pepi");
        enemiesNames.add("Pesho");
        enemiesNames.add("Rosi");
        enemiesNames.add("Steven");
        enemiesNames.add("Maya");
        enemiesNames.add("Viktor");
        enemiesNames.add("Yana");
        enemiesNames.add("Yani");
        enemiesNames.add("Yasen");

        weaponFirstPart.add("Wooden");
        weaponFirstPart.add("Copper");
        weaponFirstPart.add("Silver");
        weaponFirstPart.add("Golden");
        weaponFirstPart.add("Platinum");
        weaponFirstPart.add("Frozen");
        weaponFirstPart.add("Burning");
        weaponFirstPart.add("Frostfire");
        weaponFirstPart.add("Epic");
        weaponFirstPart.add("Legendary");
        weaponFirstPart.add("Unholy");
        weaponFirstPart.add("Divine");
        weaponFirstPart.add("Otherworldly");
        weaponFirstPart.add("Absurd");

        weaponSecondPart.add(" Sword of ");
        weaponSecondPart.add(" Bow of ");
        weaponSecondPart.add(" Staff of ");

        weaponThirdPart.add("Weaklings");
        weaponThirdPart.add("Newbies");
        weaponThirdPart.add("Recruits");
        weaponThirdPart.add("Proteges");
        weaponThirdPart.add("Nobles");
        weaponThirdPart.add("Masters");
        weaponThirdPart.add("Archangels");
        weaponThirdPart.add("Demigods");
        weaponThirdPart.add("Beasts");
        weaponThirdPart.add("The Dark Ones");
        weaponThirdPart.add("The Gods");

        allSkills.add(new Skill(PlayerType.MAGE, "Fireball", 5, true,2));
        allSkills.add(new Skill(PlayerType.WARRIOR, "Stab", 5, true,2));
        allSkills.add( new Skill(PlayerType.ARCHER, "Ice Arrow", 5, true,2));
        allSkills.add(new Skill(PlayerType.MAGE, "Blizzard", 15, true,4));
        allSkills.add(new Skill(PlayerType.WARRIOR, "Pulverize", 10, true,4));
        allSkills.add(new Skill(PlayerType.ARCHER, "Volley", 10, true,4));
        allSkills.add(new Skill(PlayerType.MAGE, "Meteor Strike", 30, true,4));
        allSkills.add(new Skill(PlayerType.ARCHER, "Rain of arrows", 20, true,4));
        allSkills.add(new Skill(PlayerType.WARRIOR, "Shockwave", 20, true,6));
        allSkills.add(new Skill(PlayerType.MAGE, "Armageddon", 200, false,6));
        allSkills.add(new Skill(PlayerType.ARCHER, "Headshot", 100, true,6));
        allSkills.add(new Skill(PlayerType.WARRIOR, "Earthquake", 150, false,6));
    }
}
