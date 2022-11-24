package implement;

public class AddPojoattacker {
    private final String command;
    private final String error;
    private final Coord cardAttacker;
    public AddPojoattacker(final String command, final String error, final Coord cardAttacker) {
        this.command = command;
        this.error = error;
        this.cardAttacker = cardAttacker;
    }

    /**
     *
     * @return
     * getCommand returns a command
     */
    public String getCommand() {
        return command;
    }

    /**
     * getError returns an error
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * getCardAttacker returns an attacker with coordinates x and y
     * @return
     */
    public Coord getCardAttacker() {
        return cardAttacker;
    }
}
