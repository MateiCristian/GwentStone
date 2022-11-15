package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Cards {
    int mana;
    String description;
    ArrayList<String> colors;
    String name;

    public Cards() {
    }

    public Cards(int mana, ArrayList<String> colors, String description){
        this.mana = mana;
        this.colors = colors;
        this.description = description;
    }
    public Cards(int mana, String name, ArrayList<String> colors, String description){
        this.mana = mana;
        this.name = name;
        this.colors = colors;
        this.description = description;
    }
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "mana=" + mana +
                ", description='" + description + '\'' +
                ", colors='" + colors + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

