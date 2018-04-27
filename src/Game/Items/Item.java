package Game.Items;

public abstract class Item {
    private int cost;
    private String name;

    public Item(String name, int cost){
        this.name = name;
        this.cost = cost;
    }
   public Item(){

    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }
}
