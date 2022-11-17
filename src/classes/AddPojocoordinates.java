package classes;

public class AddPojocoordinates {
    private String command;
    private String error;
    private Coord cardAttacked, cardAttacker;

    public AddPojocoordinates(final String command, final String error,
                              final Coord cardAttacked, final Coord cardAttacker) {
        this.command = command;
        this.error = error;
        this.cardAttacked = cardAttacked;
        this.cardAttacker = cardAttacker;
    }
    public AddPojocoordinates(final String command, final String error, final Coord cardAttacker) {
        this.command = command;
        this.error = error;
        this.cardAttacker = cardAttacker;
    }

    /**
     * getCommand returns a command
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     * setCommand sets value for command
     * @param command
     */
    public void setCommand(final String command) {
        this.command = command;
    }

    /**
     * getError returns an error
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * setError sets value for error
     * @param error
     */
    public void setError(final String error) {
        this.error = error;
    }

    /**
     * getCardAttacked returns coordinates for attacked card
     * @return
     */
    public Coord getCardAttacked() {
        return cardAttacked;
    }

    /**
     * setCardAttacked sets coordinates for attacked card
     * @param cardAttacked
     */
    public void setCardAttacked(final Coord cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    /**
     * getCardAttacker returns coordinates for attacker card
     * @return
     */
    public Coord getCardAttacker() {
        return cardAttacker;
    }

    /**
     * setCardAttacker sets coordinates for attacker card
     * @param cardAttacker
     */
    public void setCardAttacker(final Coord cardAttacker) {
        this.cardAttacker = cardAttacker;
    }
}
