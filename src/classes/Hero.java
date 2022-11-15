package classes;

import java.util.ArrayList;

public class Hero extends Cards{
    int health;
    public Hero(int mana, String name, ArrayList<String> colors, String description, int health) {
        super(mana, name, colors, description);
        this.health = health;
    }

    public Hero() {
        health = 30;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
//class LordRoyce extends Hero{
//
//}
//class EmpressThorina extends Hero{
//
//}
//class KingMudface extends Hero{
//
//}
//class GeneralKocioraw extends Hero{
//
//}