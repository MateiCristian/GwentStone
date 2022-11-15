package implement;

import classes.Arena;
import classes.Coord;
import classes.addPojo_coordinates;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public class CardUsesAbility {
    public static void cardUsesAbility(Input inputData, ArrayNode output, Arena arena, int i, int j){
        int x_attacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getX();
        int y_attacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getY();
        int x_attacked = inputData.getGames().get(i).getActions().get(j).getCardAttacked().getX();
        int y_attacked = inputData.getGames().get(i).getActions().get(j).getCardAttacked().getY();
        Coord coordinates_attacked = new Coord(x_attacked, y_attacked);
        Coord coordinates_attacker = new Coord(x_attacker, y_attacker);
        int verify = 1;
        if ((verify == 1) && (arena.getMap().get(x_attacker).get(y_attacker).frozen == 1)){
            addPojo_coordinates clas = new addPojo_coordinates("cardUsesAbility",
                    "Attacker card is frozen.", coordinates_attacked, coordinates_attacker);
            output.addPOJO(clas);
            verify = 0;
        }
        if ((verify == 1) && (arena.getMap().get(x_attacker).get(y_attacker).attacked_tur == 1)){
            addPojo_coordinates clas = new addPojo_coordinates("cardUsesAbility",
                    "Attacker card has already attacked this turn.", coordinates_attacked, coordinates_attacker);
            output.addPOJO(clas);
            verify = 0;
        }
        if ((verify == 1) && (arena.getMap().get(x_attacker).get(y_attacker).getName().equals("Disciple"))) {
            if ((x_attacker == 0) && (x_attacked > 1)){
                    addPojo_coordinates clas = new addPojo_coordinates("cardUsesAbility",
                            "Attacked card does not belong to the current player.", coordinates_attacked, coordinates_attacker);
                    output.addPOJO(clas);
                    verify = 0;
            }else {
                if ((x_attacker == 3) && (x_attacked < 2)){
                    addPojo_coordinates clas = new addPojo_coordinates("cardUsesAbility",
                            "Attacked card does not belong to the current player.", coordinates_attacked, coordinates_attacker);
                    output.addPOJO(clas);
                    verify = 0;
                }else{
                    arena.getMap().get(x_attacked).get(y_attacked).setHealth(
                            arena.getMap().get(x_attacked).get(y_attacked).getHealth() + 2);
                    verify = 0;
                }
            }
        }
        if (verify == 1) {
            if (arena.getMap().get(x_attacker).get(y_attacker).getName().equals("The Ripper") ||
                    arena.getMap().get(x_attacker).get(y_attacker).getName().equals("Miraj") ||
                    arena.getMap().get(x_attacker).get(y_attacker).getName().equals("The Cursed One")) {
                if ((x_attacker < 2) && (x_attacked < 2)){
                    addPojo_coordinates clas = new addPojo_coordinates("cardUsesAbility",
                            "Attacked card does not belong to the current player.", coordinates_attacked, coordinates_attacker);
                    output.addPOJO(clas);
                    verify = 0;
                }else {
                    if ((x_attacker >= 2) && (x_attacked >= 2)){
                        addPojo_coordinates clas = new addPojo_coordinates("cardUsesAbility",
                                "Attacked card does not belong to the enemy.", coordinates_attacked, coordinates_attacker);
                        output.addPOJO(clas);
                        verify = 0;
                    }else{
                        int sem_tank = -1;
                        if (x_attacker < 2) {
                            for (int k = 0; k < arena.getMap().get(2).size(); k++) {
                                if (arena.getMap().get(2).get(k).getName().equals("Goliath") ||
                                        arena.getMap().get(2).get(k).getName().equals("Warden")) {
                                    sem_tank = k;
                                }
                            }
                        }else {
                            for (int k = 0; k < arena.getMap().get(1).size(); k++) {
                                if (arena.getMap().get(1).get(k).getName().equals("Goliath") ||
                                        arena.getMap().get(1).get(k).getName().equals("Warden")) {
                                    sem_tank = k;
                                }
                            }

                        }
                        if ((sem_tank == -1) || (arena.getMap().get(x_attacked).get(y_attacked).getName().equals("Goliath") ||
                                arena.getMap().get(x_attacked).get(y_attacked).getName().equals("Warden"))) {
                            if (x_attacked < arena.getMap().size() && y_attacked < arena.getMap().get(x_attacked).size()) {
                                if (arena.getMap().get(x_attacker).get(y_attacker).getName().equals("The Ripper")) {
                                    arena.getMap().get(x_attacked).get(y_attacked).setAttackDamage(
                                            arena.getMap().get(x_attacked).get(y_attacked).getAttackDamage() - 2);
                                    if (arena.getMap().get(x_attacked).get(y_attacked).getAttackDamage() < 0) {
                                        arena.getMap().get(x_attacked).get(y_attacked).setAttackDamage(0);
                                    }
                                    arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;

                                } else if (arena.getMap().get(x_attacker).get(y_attacker).getName().equals("Miraj")) {
                                    int aux = arena.getMap().get(x_attacker).get(y_attacker).getHealth();
                                    arena.getMap().get(x_attacker).get(y_attacker).setHealth(
                                            arena.getMap().get(x_attacked).get(y_attacked).getHealth());
                                    arena.getMap().get(x_attacked).get(y_attacked).setHealth(aux);
                                    arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;

                                } else if (arena.getMap().get(x_attacker).get(y_attacker).getName().equals("The Cursed One")) {
                                    int aux = arena.getMap().get(x_attacked).get(y_attacked).getHealth();
                                    arena.getMap().get(x_attacked).get(y_attacked).setHealth(
                                            arena.getMap().get(x_attacked).get(y_attacked).getAttackDamage());
                                    arena.getMap().get(x_attacked).get(y_attacked).setAttackDamage(aux);
                                    if (arena.getMap().get(x_attacked).get(y_attacked).getHealth() == 0) {
                                        arena.getMap().get(x_attacked).remove(y_attacked);
                                    }
                                    arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;
                                }
                                verify = 0;
                            }
                        }else{
                            addPojo_coordinates clas = new addPojo_coordinates("cardUsesAbility",
                                    "Attacked card is not of type 'Tank'.", coordinates_attacked, coordinates_attacker);
                            output.addPOJO(clas);
                            verify = 0;
                        }
                    }
                }
            }
        }
    }
}
