package Game.Hero;

public class Mage extends Hero {

    public Mage(String name) {
        super(name);
    }

    public String toString(){
        return String.format("%s, Level %d Mage", name, level);
    }
}
