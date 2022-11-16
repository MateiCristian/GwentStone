package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

import java.util.ArrayList;

public class Minion extends Cards{
    int health;
    int attackDamage;

    @JsonIgnore
    public int frozen;

    @JsonIgnore
    public int attacked_tur;
    public Minion(int mana, String name, ArrayList<String> colors, String description, int attackDamage, int health) {
        super(mana, name, colors, description);
        this.attackDamage = attackDamage;
        this.health = health;
    }
    public Minion(Minion min){
        this.health = min.getHealth();
        this.attackDamage = min.getAttackDamage();
        this.setMana(min.getMana());
        this.setName(min.getName());
        this.setColors(min.getColors());
        this.setDescription(min.getDescription());
        this.frozen = min.frozen;
        this.attacked_tur = min.attacked_tur;
    }
    public Minion(CardInput min){
        this.health = min.getHealth();
        this.attackDamage = min.getAttackDamage();
        this.setMana(min.getMana());
        this.setName(min.getName());
        this.setColors(min.getColors());
        this.setDescription(min.getDescription());
    }
    public Minion() {}

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttacked_tur() {
        return attacked_tur;
    }

    public void setAttacked_tur(int attacked_tur) {
        this.attacked_tur = attacked_tur;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getFrozen() {
        return frozen;
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    @Override
    public String toString() {
        return "Minion{" +
                "mana=" + mana +
                ", attackDamage=" + attackDamage +
                ", health=" + health +
                ", description='" + description + '\'' +
                ", colors=" + colors +
                ", name='" + name + '\'' +
                '}';
    }
}

//class Sentinel extends Minion{
//
//}
//class Berserker extends Minion{
//
//    public Berserker(int mana, int health, int attackDamage, String description, ArrayList<String> colors, String name) {
//        super(mana, health, attackDamage, description, colors, name);
//    }
//}
//class Goliath extends Minion{
//
//}
//class Warden extends Minion{
//
//}
//class TheRipper extends Minion{
//
//}
//class Miraj extends Minion{
//
//}
//class TheCursedOne extends Minion{
//
//}
//class Disciple extends Minion{
//
//}