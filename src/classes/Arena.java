package classes;

import java.util.ArrayList;

public class Arena {
    private ArrayList<ArrayList<Minion>> map = new ArrayList<>();

    /**
     * getMap returns the game arena
     * @return
     */
    public ArrayList<ArrayList<Minion>> getMap() {
        return map;
    }

    /**
     * setMap sets the values for a game arena
     * @param map
     */
    public void setMap(final ArrayList<ArrayList<Minion>> map) {
        this.map = map;
    }
}
