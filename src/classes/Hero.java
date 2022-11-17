package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public final class Hero extends Cards {
    private int health = 30;
    @JsonIgnore
    private int attackedtur;

    public Hero(final int mana, final String name, final ArrayList<String> colors,
                final String description, final int health) {
        super(mana, name, colors, description);
        this.health = health;
    }

    public Hero(final Hero hero) {
        super(hero.getMana(), hero.getName(), hero.getColors(), hero.getDescription());
        this.health = hero.getHealth();
        this.attackedtur = hero.attackedtur;
    }

    public Hero() {

    }

    /**
     * getHealth returns health
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * setHealth sets hero health
     * @param health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * getAttackedtur returns attackedtur
     * 1 if hero attacked this round and 0 if not
     * @return
     */
    public int getAttackedtur() {
        return attackedtur;
    }

    /**
     * setAttackedtur sets value for attackedtur
     * @param attackedtur
     */
    public void setAttackedtur(final int attackedtur) {
        this.attackedtur = attackedtur;
    }
}
