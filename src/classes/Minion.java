package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

import java.util.ArrayList;

public class Minion extends Cards {
    private int health;
    private int attackDamage;

    @JsonIgnore
    private int frozen;

    @JsonIgnore
    private int attackedtur;

    public Minion(final int mana, final String name, final ArrayList<String> colors,
                  final String description, final int attackDamage, final int health) {
        super(mana, name, colors, description);
        this.attackDamage = attackDamage;
        this.health = health;
    }

    public Minion(final Minion min) {
        this.health = min.getHealth();
        this.attackDamage = min.getAttackDamage();
        this.setMana(min.getMana());
        this.setName(min.getName());
        this.setColors(min.getColors());
        this.setDescription(min.getDescription());
        this.frozen = min.frozen;
        this.attackedtur = min.attackedtur;
    }

    public Minion(final CardInput min) {
        this.health = min.getHealth();
        this.attackDamage = min.getAttackDamage();
        this.setMana(min.getMana());
        this.setName(min.getName());
        this.setColors(min.getColors());
        this.setDescription(min.getDescription());
    }

    public Minion() {
    }

    /**
     * getHealth returns the health of a minion
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * setHealth sets the health for a minion
     * @param health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * getAttackedtur returns attackedtur (1 if minion attacked
     * already this round and 0 if not)
     * @return
     */
    public int getAttackedtur() {
        return attackedtur;
    }

    /**
     * seAttackedtur sets the attackedtur
     * @param attackedtur
     */
    public void setAttackedtur(final int attackedtur) {
        this.attackedtur = attackedtur;
    }

    /**
     * setattackedDamage sets the attackedDamage
     * @param attackDamage
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * getFrozen returns if a minion is frozen
     * @return
     */
    public int getFrozen() {
        return frozen;
    }

    /**
     * setFrozen sets the frozen propperty for a minion
     * @param frozen
     */
    public void setFrozen(final int frozen) {
        this.frozen = frozen;
    }

    /**
     * getattackDamage returns attackDamage
     * @return
     */
    public int getAttackDamage() {
        return attackDamage;
    }
}
