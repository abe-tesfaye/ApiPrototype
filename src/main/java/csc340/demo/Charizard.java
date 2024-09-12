package csc340.demo;

import java.util.Arrays;

public class Charizard {
    private String name;
    private int height;
    private int weight;
    private String[] abilities;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    @Override
    public String toString() {
        return "Charizard{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", abilities=" + Arrays.toString(abilities) +
                '}';
    }
}
