package classes;

public class addPojo_coordinates {
    String command;
    String error;
    Coord cardAttacked, cardAttacker;

    public addPojo_coordinates(String command, String error, Coord cardAttacked, Coord cardAttacker) {
        this.command = command;
        this.error = error;
        this.cardAttacked = cardAttacked;
        this.cardAttacker = cardAttacker;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Coord getCardAttacked() {
        return cardAttacked;
    }

    public void setCardAttacked(Coord cardAttacked) {
        this.cardAttacked = cardAttacked;
    }

    public Coord getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(Coord cardAttacker) {
        this.cardAttacker = cardAttacker;
    }
}
