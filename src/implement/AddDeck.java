package implement;

import classes.Environment;
import classes.Minion;
import classes.Playerv2;
import fileio.CardInput;
import fileio.Input;

public final class AddDeck {
    private AddDeck() {
    }

    /**
     * Add_deck implements the entire task
     * @param player is the player where you will add the deck
     * @param inputData is the input
     * @param idxplayer is the index for the deck
     * @param nrplayer represents the number for player 1 for player 1 (2 for player 2)
     */
    public static void adddeck(final Playerv2 player, final Input inputData,
                               final int idxplayer, final int nrplayer) {
        for (int j = 0; j < player.getNrCardsInDeck(); j++) {
            CardInput card;
            if (nrplayer == 1) {
                card = inputData.getPlayerOneDecks().getDecks().get(idxplayer).get(j);
            } else {
                card = inputData.getPlayerTwoDecks().getDecks().get(idxplayer).get(j);
            }
            if (card.getName().equals("Firestorm") || card.getName().equals("Winterfell")
                    || card.getName().equals("Heart Hound")) {
                Environment newcard = new Environment(card);
                player.getDeck().add(newcard);
            } else {
                Minion newcard = new Minion(card);
                player.getDeck().add(newcard);
            }
        }
    }
}
