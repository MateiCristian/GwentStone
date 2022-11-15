package classes;

import fileio.CardInput;
import fileio.Input;

public class Add_Deck {
    public static void Add_deck(Playerv2 player, Input inputData, int idx_player, int nr_player){
        for(int j = 0 ; j < player.getNrCardsInDeck(); j++) {
            CardInput card;
            if(nr_player == 1) {
                card = inputData.getPlayerOneDecks().getDecks().get(idx_player).get(j);
            }else{
                card = inputData.getPlayerTwoDecks().getDecks().get(idx_player).get(j);
            }
            if (card.getName().equals("Firestorm") || card.getName().equals("Winterfell") || card.getName().equals("Heart Hound")) {
                Environment newcard = new Environment(card.getMana(), card.getName(),
                        card.getColors(), card.getDescription());
                player.getDeck().add(newcard);
            }else {
                Minion newcard = new Minion(card.getMana(), card.getName(),
                        card.getColors(), card.getDescription(), card.getAttackDamage(), card.getHealth());
                player.getDeck().add(newcard);
            }
        }
    }
}
