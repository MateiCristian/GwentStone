package implement;

import classes.Arena;
import classes.Playerv2;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public final class UseAttackHero {
    private UseAttackHero() {
    }

    /**
     * useAttackHero implements all the task
     * inputData is the input, output is the output
     * arena is the game arena, i is the row, j is the column
     * player1 is first player and player2 is second player
     * @param inputData
     * @param output
     * @param arena
     * @param i
     * @param j
     * @param player1
     * @param player2
     * @return
     */
    public static int useAttackHero(final Input inputData, final ArrayNode output,
                                    final Arena arena, final int i, final int j,
                                    final Playerv2 player1, final Playerv2 player2) {
        int xattacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getX();
        int yattacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getY();
        Coord coordinatesattacker = new Coord(xattacker, yattacker);
        int verify = 1;
        if (xattacker < arena.getMap().size() && yattacker
                < arena.getMap().get(xattacker).size()) {
            if ((verify == 1) && (arena.getMap().get(xattacker).get(yattacker).getFrozen() == 1)) {
                AddPojoattacker clas = new AddPojoattacker("useAttackHero",
                        "Attacker card is frozen.", coordinatesattacker);
                output.addPOJO(clas);
                verify = 0;
            }
            if ((verify == 1) && (arena.getMap().get(xattacker).get(yattacker)
                    .getAttackedtur() == 1)) {
                AddPojoattacker clas = new AddPojoattacker("useAttackHero",
                        "Attacker card has already attacked this turn.", coordinatesattacker);
                output.addPOJO(clas);
                verify = 0;
            }
            if (verify == 1) {
                int sem = 1;
                if (xattacker < 2) {
                    for (int k = 0; k < arena.getMap().get(2).size(); k++) {
                        if (arena.getMap().get(2).get(k).getName().equals("Goliath")
                                || arena.getMap().get(2).get(k).getName().equals("Warden")) {
                            sem = 0;
                            break;
                        }
                    }
                } else {
                    for (int k = 0; k < arena.getMap().get(1).size(); k++) {
                        if (arena.getMap().get(1).get(k).getName().equals("Goliath")
                                || arena.getMap().get(1).get(k).getName().equals("Warden")) {
                            sem = 0;
                            break;
                        }
                    }
                }
                if (sem == 0) {
                    AddPojoattacker clas = new AddPojoattacker("useAttackHero",
                            "Attacked card is not of type 'Tank'.", coordinatesattacker);
                    output.addPOJO(clas);
                } else {
                    if (xattacker < 2) {
                        player1.getHero().setHealth(player1.getHero().getHealth()
                                - arena.getMap().get(xattacker).get(yattacker).getAttackDamage());
                        arena.getMap().get(xattacker).get(yattacker).setAttackedtur(1);
                        if (player1.getHero().getHealth() <= 0) {
                            output.addObject().put("gameEnded",
                                    "Player two killed the enemy hero.");
                            return -1;
                        }
                    } else {
                        player2.getHero().setHealth(player2.getHero().getHealth()
                                - arena.getMap().get(xattacker).get(yattacker).getAttackDamage());
                        arena.getMap().get(xattacker).get(yattacker).setAttackedtur(1);
                        if (player2.getHero().getHealth() <= 0) {
                            output.addObject().put("gameEnded",
                                    "Player one killed the enemy hero.");
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
