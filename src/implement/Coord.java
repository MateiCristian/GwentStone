package implement;

public class Coord {
    private int x;
    private int y;

    /**
     * getX returns value for x
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * setX sets value for x
     * @param x
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * getY returns value for y
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * setY sets value for y
     * @param y
     */
    public void setY(final int y) {
        this.y = y;
    }

    public Coord(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
