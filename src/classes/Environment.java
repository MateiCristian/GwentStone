package classes;

import fileio.CardInput;

import java.util.ArrayList;

public class Environment extends Cards{
    public Environment() {
    }

    public Environment(int mana, String name, ArrayList<String> colors, String description) {
        super(mana, name, colors, description);
    }
    public Environment(Environment environment){
        super(environment.getMana(), environment.getName(), environment.getColors(), environment.getDescription());
    }
    public Environment(CardInput environment){
        super(environment.getMana(), environment.getName(), environment.getColors(), environment.getDescription());
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Environment{" +
                "mana=" + mana +
                ", description='" + description + '\'' +
                ", colors=" + colors +
                ", name='" + name + '\'' +
                '}';
    }
}
//class Firestorm extends Environment{
//    public Firestorm() {
//    }
//}
//class Winterfell extends Environment{
//    public Winterfell() {
//    }
//}
//class HeartHound extends Environment{
//    public HeartHound() {
//    }
//}