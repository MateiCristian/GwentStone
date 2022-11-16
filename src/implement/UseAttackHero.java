package implement;

import classes.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;

public class UseAttackHero {
    public static int useAttackHero(Input inputData, ArrayNode output, Arena arena, int i, int j, Playerv2 player1, Playerv2 player2) {
        int x_attacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getX();
        int y_attacker = inputData.getGames().get(i).getActions().get(j).getCardAttacker().getY();
        Coord coordinates_attacker = new Coord(x_attacker, y_attacker);
        int verify = 1;
        if (x_attacker < arena.getMap().size() && y_attacker < arena.getMap().get(x_attacker).size()) {
            if ((verify == 1) && (arena.getMap().get(x_attacker).get(y_attacker).frozen == 1)) {
                addPojo_attacker clas = new addPojo_attacker("useAttackHero",
                        "Attacker card is frozen.", coordinates_attacker);
                output.addPOJO(clas);
                verify = 0;
            }
            if ((verify == 1) && (arena.getMap().get(x_attacker).get(y_attacker).attacked_tur == 1)) {
                addPojo_attacker clas = new addPojo_attacker("useAttackHero",
                        "Attacker card has already attacked this turn.", coordinates_attacker);
                output.addPOJO(clas);
                verify = 0;
            }
            if (verify == 1){
                int sem = 1;
                if (x_attacker < 2){
                    for (int k = 0; k < arena.getMap().get(2).size(); k++){
                        if(arena.getMap().get(2).get(k).getName().equals("Goliath") ||
                        arena.getMap().get(2).get(k).getName().equals("Warden")) {
                            sem = 0;
                            break;
                        }
                    }
                }else {
                    for (int k = 0; k < arena.getMap().get(1).size(); k++){
                        if(arena.getMap().get(1).get(k).getName().equals("Goliath") ||
                                arena.getMap().get(1).get(k).getName().equals("Warden")) {
                            sem = 0;
                            break;
                        }
                    }
                }
                if (sem == 0){
                    addPojo_attacker clas = new addPojo_attacker("useAttackHero",
                            "Attacked card is not of type 'Tank'.", coordinates_attacker);
                    output.addPOJO(clas);
                    verify = 0;
                }else {
                    if (x_attacker < 2){
                        player1.getHero().setHealth(player1.getHero().getHealth() -
                                arena.getMap().get(x_attacker).get(y_attacker).getAttackDamage());
                        verify = 0;
                        arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;
                        if (player1.getHero().getHealth() <= 0){
                            output.addObject().put("gameEnded", "Player two killed the enemy hero.");
                            return -1;
                        }
                    }else {
                        player2.getHero().setHealth(player2.getHero().getHealth() -
                                arena.getMap().get(x_attacker).get(y_attacker).getAttackDamage());
                        verify = 0;
                        arena.getMap().get(x_attacker).get(y_attacker).attacked_tur = 1;
                        if (player2.getHero().getHealth() <= 0){
                            output.addObject().put("gameEnded", "Player one killed the enemy hero.");
                            return 1;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
