package classes;

import java.util.ArrayList;

public class Cards {
    private int mana;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public Cards() {
    }

    public Cards(final int mana, final ArrayList<String> colors, final String description) {
        this.mana = mana;
        this.colors = colors;
        this.description = description;
    }

    public Cards(final int mana, final String name,
                 final ArrayList<String> colors, final String description) {
        this.mana = mana;
        this.name = name;
        this.colors = colors;
        this.description = description;
    }

    /**
     * getMana returns mana
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     * setMana sets value for mana
     * @param mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * getDescription returns description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * setDescription sets a description
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * getColors returns all colors
     * @return
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * setColors sets colors
     * @param colors
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * getName returns the name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setName sets value for name
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * basic toString method returning mana, description, colors and name
     * @return
     */
    @Override
    public String toString() {
        return "Cards{"
                + "mana=" + mana
                + ", description='" + description + '\''
                + ", colors='" + colors + '\''
                + ", name='" + name + '\''
                + '}';
    }
}

