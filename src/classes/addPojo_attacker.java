package classes;

public class addPojo_attacker {
    String command;
    String error;
    Coord cardAttacker;
    public addPojo_attacker(String command, String error, Coord cardAttacker) {
        this.command = command;
        this.error = error;
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

    public Coord getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(Coord cardAttacker) {
        this.cardAttacker = cardAttacker;
    }
}
