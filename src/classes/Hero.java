package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Hero extends Cards{
    int health;
    @JsonIgnore
    public int attacked_tur;
    public Hero(int mana, String name, ArrayList<String> colors, String description, int health) {
        super(mana, name, colors, description);
        this.health = health;
    }
    public Hero(Hero hero){
        super(hero.getMana(), hero.getName(), hero.getColors(), hero.getDescription());
        this.health = hero.getHealth();
        this.attacked_tur = hero.attacked_tur;
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