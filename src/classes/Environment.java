package classes;

import fileio.CardInput;

import java.util.ArrayList;

public class Environment extends Cards {
    public Environment() {
    }

    public Environment(final int mana, final String name,
                       final ArrayList<String> colors, final String description) {
        super(mana, name, colors, description);
    }

    public Environment(final Environment environment) {
        super(environment.getMana(), environment.getName(),
                environment.getColors(), environment.getDescription());
    }

    public Environment(final CardInput environment) {
        super(environment.getMana(), environment.getName(),
                environment.getColors(), environment.getDescription());
    }

    /**
     * getMana returns mana
     * @return
     */
    public int getMana() {
        return super.getMana();
    }
    /**
     * setMana sets mana
     * @return
     */
    public void setMana(final int mana) {
        super.setMana(super.getMana());
    }
    /**
     * getName returns name
     * @return
     */
    public String getName() {
        return super.getName();
    }
    /**
     * setName sets name
     * @return
     */
    public void setName(final String name) {
        super.setName(super.getName());
    }

    /**
     * getDescription returns description
     * @return
     */
    public String getDescription() {
        return super.getDescription();
    }
    /**
     * setDescription sets description
     * @return
     */
    public void setDescription(final String description) {
        super.setDescription(super.getDescription());
    }
    /**
     * getColors returns all colors
     * @return
     */
    public ArrayList<String> getColors() {
        return super.getColors();
    }
    /**
     * setColors sets all colors
     * @return
     */
    public void setColors(final ArrayList<String> colors) {
        super.setColors(super.getColors());
    }
    /**
     * toString returns an Environment card with all the paramethers for print
     * @return
     */
    @Override
    public String toString() {
        return "Environment{"
                + "mana=" + super.getMana()
                + ", description='" + super.getDescription() + '\''
                + ", colors=" + super.getColors()
                + ", name='" + super.getName() + '\''
                + '}';
    }
}
