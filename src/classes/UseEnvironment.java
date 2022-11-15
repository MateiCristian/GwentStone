package classes;

public class UseEnvironment {
    public static void Environment(Arena arena, Environment card, int affected_row, Playerv2 p1, Playerv2 p2){
        if (card.getName().equals("Firestorm")) {
            for (int i = 0; i < arena.getMap().get(affected_row).size(); i++) {
                arena.getMap().get(affected_row).get(i).setHealth(arena.getMap().get(affected_row).get(i).getHealth() - 1);
                if (arena.getMap().get(affected_row).get(i).getHealth() <= 0) {
                    arena.getMap().get(affected_row).remove(i);
                    i--;
                }
            }
            if (affected_row < 2){
                p1.setMana(p1.getMana() - card.getMana());
            }else {
                p2.setMana(p2.getMana() - card.getMana());
            }
        }

        if (card.getName().equals("Winterfell")) {
            for (int i = 0; i < arena.getMap().get(affected_row).size(); i++) {
                arena.getMap().get(affected_row).get(i).frozen = 1;
            }
            if (affected_row < 2){
                p1.setMana(p1.getMana() - card.getMana());
            }else {
                p2.setMana(p2.getMana() - card.getMana());
            }
        }

        if (card.getName().equals("Heart Hound")) {
            if (arena.getMap().get(3 - affected_row).size() != 5) {
                int max_health = 0;
                int index_max_health = -1;
                for (int i = 0; i < arena.getMap().get(affected_row).size(); i++) {
                    if (max_health < arena.getMap().get(affected_row).get(i).getHealth()) {
                        max_health = arena.getMap().get(affected_row).get(i).getHealth();
                        index_max_health = i;
                    }
                }
                arena.getMap().get(3 - affected_row).add(arena.getMap().get(affected_row).get(index_max_health));
                arena.getMap().get(affected_row).remove(index_max_health);
                if (affected_row < 2){
                    p1.setMana(p1.getMana() - card.getMana());
                }else {
                    p2.setMana(p2.getMana() - card.getMana());
                }
            }
        }
    }
}
