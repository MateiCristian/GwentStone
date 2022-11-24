package implement;

import classes.Arena;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public final class CardUsesAbility {
    private CardUsesAbility() {
    }

    /**
     * cardUsesAbility implements the entire task
     * inputData is the input
     * output is the output
     * arena is the game arena
     * i is the row and j is the column
     * @param inputData
     * @param output
     * @param arena
     * @param i
     * @param j
     */
    public static void cardUsesAbility(final Input inputData, final ArrayNode output,
                                       final Arena arena, final int i, final int j) {
        int xattacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getX();
        int yattacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getY();
        int xattacked = inputData.getGames().get(i).getActions().get(j).getCardAttacked().getX();
        int yattacked = inputData.getGames().get(i).getActions().get(j).getCardAttacked().getY();
        Coord coordinatesattacked = new Coord(xattacked, yattacked);
        Coord coordinatesattacker = new Coord(xattacker, yattacker);
        int verify = 1;
        if ((verify == 1) && (arena.getMap().get(xattacker).get(yattacker).getFrozen() == 1)) {
            AddPojocoordinates clas = new AddPojocoordinates("cardUsesAbility",
                    "Attacker card is frozen.", coordinatesattacked, coordinatesattacker);
            output.addPOJO(clas);
            verify = 0;
        }
        if ((verify == 1) && (arena.getMap().get(xattacker).get(yattacker).getAttackedtur() == 1)) {
            AddPojocoordinates clas = new AddPojocoordinates("cardUsesAbility",
                    "Attacker card has already attacked this turn.", coordinatesattacked,
                    coordinatesattacker);
            output.addPOJO(clas);
            verify = 0;
        }
        if ((verify == 1) && (arena.getMap().get(xattacker).get(yattacker).getName()
                .equals("Disciple"))) {
            if ((xattacker == 0) && (xattacked > 1)) {
                AddPojocoordinates clas = new AddPojocoordinates("cardUsesAbility",
                        "Attacked card does not belong to the current player.",
                        coordinatesattacked, coordinatesattacker);
                output.addPOJO(clas);
                verify = 0;
            } else {
                if ((xattacker == (2 + 1)) && (xattacked < 2)) {
                    AddPojocoordinates clas = new AddPojocoordinates("cardUsesAbility",
                            "Attacked card does not belong to the current player.",
                            coordinatesattacked, coordinatesattacker);
                    output.addPOJO(clas);
                    verify = 0;
                } else {
                    arena.getMap().get(xattacked).get(yattacked).setHealth(
                            arena.getMap().get(xattacked).get(yattacked).getHealth() + 2);
                    verify = 0;
                }
            }
        }
        if (verify == 1) {
            if (arena.getMap().get(xattacker).get(yattacker).getName().equals("The Ripper")
                    || arena.getMap().get(xattacker).get(yattacker).getName().equals("Miraj")
                    || arena.getMap().get(xattacker).get(yattacker).getName()
                            .equals("The Cursed One")) {
                if ((xattacker < 2) && (xattacked < 2)) {
                    AddPojocoordinates clas = new AddPojocoordinates("cardUsesAbility",
                            "Attacked card does not belong to the current player.",
                            coordinatesattacked, coordinatesattacker);
                    output.addPOJO(clas);
                    verify = 0;
                } else {
                    if ((xattacker >= 2) && (xattacked >= 2)) {
                        AddPojocoordinates clas = new AddPojocoordinates("cardUsesAbility",
                                "Attacked card does not belong to the enemy.",
                                coordinatesattacked, coordinatesattacker);
                        output.addPOJO(clas);
                        verify = 0;
                    } else {
                        int semtank = -1;
                        if (xattacker < 2) {
                            for (int k = 0; k < arena.getMap().get(2).size(); k++) {
                                if (arena.getMap().get(2).get(k).getName().equals("Goliath")
                                        || arena.getMap().get(2).get(k)
                                        .getName().equals("Warden")) {
                                    semtank = k;
                                }
                            }
                        } else {
                            for (int k = 0; k < arena.getMap().get(1).size(); k++) {
                                if (arena.getMap().get(1).get(k).getName().equals("Goliath")
                                        || arena.getMap().get(1).get(k)
                                        .getName().equals("Warden")) {
                                    semtank = k;
                                }
                            }

                        }
                        if ((semtank == -1) || (arena.getMap().get(xattacked).get(yattacked)
                                .getName().equals("Goliath")
                                || arena.getMap().get(xattacked).get(yattacked)
                                .getName().equals("Warden"))) {
                            if (xattacked < arena.getMap().size() && yattacked < arena.getMap()
                                    .get(xattacked).size()) {
                                if (arena.getMap().get(xattacker).get(yattacker).getName()
                                        .equals("The Ripper")) {
                                    arena.getMap().get(xattacked).get(yattacked).setAttackDamage(
                                            arena.getMap().get(xattacked).get(yattacked)
                                                    .getAttackDamage() - 2);
                                    if (arena.getMap().get(xattacked).get(yattacked)
                                            .getAttackDamage() < 0) {
                                        arena.getMap().get(xattacked).get(yattacked)
                                                .setAttackDamage(0);
                                    }
                                    arena.getMap().get(xattacker).get(yattacker).setAttackedtur(1);

                                } else if (arena.getMap().get(xattacker).get(yattacker).getName()
                                        .equals("Miraj")) {
                                    int aux = arena.getMap().get(xattacker).get(yattacker)
                                            .getHealth();
                                    arena.getMap().get(xattacker).get(yattacker).setHealth(
                                            arena.getMap().get(xattacked).get(yattacked)
                                                    .getHealth());
                                    arena.getMap().get(xattacked).get(yattacked).setHealth(aux);
                                    arena.getMap().get(xattacker).get(yattacker)
                                            .setAttackedtur(1);

                                } else if (arena.getMap().get(xattacker).get(yattacker)
                                        .getName().equals("The Cursed One")) {
                                    int aux = arena.getMap().get(xattacked).get(yattacked)
                                            .getHealth();
                                    arena.getMap().get(xattacked).get(yattacked).setHealth(
                                            arena.getMap().get(xattacked).get(yattacked)
                                                    .getAttackDamage());
                                    arena.getMap().get(xattacked).get(yattacked)
                                            .setAttackDamage(aux);
                                    if (arena.getMap().get(xattacked).get(yattacked)
                                            .getHealth() == 0) {
                                        arena.getMap().get(xattacked).remove(yattacked);
                                    }
                                    arena.getMap().get(xattacker).get(yattacker).setAttackedtur(1);
                                }
                                verify = 0;
                            }
                        } else {
                            AddPojocoordinates clas = new AddPojocoordinates("cardUsesAbility",
                                    "Attacked card is not of type 'Tank'.", coordinatesattacked,
                                    coordinatesattacker);
                            output.addPOJO(clas);
                            verify = 0;
                        }
                    }
                }
            }
        }
    }
}
