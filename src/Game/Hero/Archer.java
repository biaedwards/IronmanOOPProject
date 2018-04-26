package Game.Hero;

public class Archer extends Hero {

    public Archer(String name) {
        super(name);
    }

    public String toString() {
        return String.format("%s, Level %d Archer", name, level);
    }
}
