package csc340.demo;
public class Charizard {
    private String name;
    private int height;
    private int weight;
    private String[] abilities;

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Height
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Getter and Setter for Weight
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // Getter and Setter for Abilities
    public String[] getAbilities() {
        return abilities;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Charizard{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", abilities=" + String.join(", ", abilities) +
                '}';
    }
}