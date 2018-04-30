package Game.Items;

import java.util.ArrayList;

public class ItemNames {
    static ArrayList<String> armorFirstPart;
    static ArrayList<String> armorSecondPart;

    public ItemNames(){
        armorFirstPart = new ArrayList<>();
        armorSecondPart = new ArrayList<>();

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
    }
}
