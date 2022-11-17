package classes;

public final class UseEnvironment {
    private UseEnvironment() {
    }

    /**
     * This method implements all the task
     * arena is the game arena
     * card is the using environment card
     * affectedrow is the row where we will activate the card
     * p1 is the player1
     * p2 is the player2
     * @param arena
     * @param card
     * @param affectedrow
     * @param p1
     * @param p2
     */
    public static void environment(final Arena arena, final Environment card,
                                   final int affectedrow, final Playerv2 p1, final Playerv2 p2) {
        if (card.getName().equals("Firestorm")) {
            for (int i = 0; i < arena.getMap().get(affectedrow).size(); i++) {
                arena.getMap().get(affectedrow).get(i).setHealth(arena.getMap()
                        .get(affectedrow).get(i).getHealth() - 1);
                if (arena.getMap().get(affectedrow).get(i).getHealth() <= 0) {
                    arena.getMap().get(affectedrow).remove(i);
                    i--;
                }
            }
            if (affectedrow < 2) {
                p1.setMana(p1.getMana() - card.getMana());
            } else {
                p2.setMana(p2.getMana() - card.getMana());
            }
        }

        if (card.getName().equals("Winterfell")) {
            for (int i = 0; i < arena.getMap().get(affectedrow).size(); i++) {
                arena.getMap().get(affectedrow).get(i).setFrozen(1);
            }
            if (affectedrow < 2) {
                p1.setMana(p1.getMana() - card.getMana());
            } else {
                p2.setMana(p2.getMana() - card.getMana());
            }
        }

        if (card.getName().equals("Heart Hound")) {
            if (arena.getMap().get(2 + 1 - affectedrow).size() != (2 * 2 + 1)) {
                int maxhealth = 0;
                int indexmaxhealth = -1;
                for (int i = 0; i < arena.getMap().get(affectedrow).size(); i++) {
                    if (maxhealth < arena.getMap().get(affectedrow).get(i).getHealth()) {
                        maxhealth = arena.getMap().get(affectedrow).get(i).getHealth();
                        indexmaxhealth = i;
                    }
                }
                arena.getMap().get(2 + 1  - affectedrow).add(arena.getMap()
                        .get(affectedrow).get(indexmaxhealth));
                arena.getMap().get(affectedrow).remove(indexmaxhealth);
                if (affectedrow < 2) {
                    p1.setMana(p1.getMana() - card.getMana());
                } else {
                    p2.setMana(p2.getMana() - card.getMana());
                }
            }
        }
    }
}
