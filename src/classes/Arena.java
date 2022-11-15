package classes;

import java.util.ArrayList;

public class Arena {
    ArrayList<ArrayList<Minion>> map = new ArrayList<>(4);

    public ArrayList<ArrayList<Minion>> getMap() {
        return map;
    }

    public void setMap(ArrayList<ArrayList<Minion>> map) {
        this.map = map;
    }
}
