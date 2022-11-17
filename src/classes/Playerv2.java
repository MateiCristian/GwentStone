package classes;

import java.util.ArrayList;

public class Playerv2 {
    private int nrCardsInDeck = 0;
    private ArrayList<Cards> deck = new ArrayList<Cards>();

    private ArrayList<Cards> hand = new ArrayList<Cards>();
    private Hero hero;
    private int mana;

    public Playerv2() {
    }

    public Playerv2(final Playerv2 player) {
        this.setHero(new Hero(player.getHero()));
        this.setMana(player.getMana());
        this.setNrCardsInDeck(player.getNrCardsInDeck());
        this.setDeck(player.getDeck());
        this.setHand(player.getHand());
    }

    /**
     * getNrCardsInDeck returns the number of cards
     * @return
     */
    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    /**
     * setNrCardsInDeck sets the number of cards
     * @param nrCardsInDeck
     */
    public void setNrCardsInDeck(final int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    /**
     * getDeck returns the deck of cards
     * @return
     */
    public ArrayList<Cards> getDeck() {
        return deck;
    }

    /**
     * getHand returns the cards in hand
     * @return
     */
    public ArrayList<Cards> getHand() {
        return hand;
    }

    /**
     * setHand sets the cards in hand
     * @param hand
     */
    public void setHand(final ArrayList<Cards> hand) {
        this.hand = hand;
    }

    /**
     * setDeck sets the cards in deck
     * @param deck
     */
    public void setDeck(final ArrayList<Cards> deck) {
        this.deck = deck;
    }

    /**
     * getHero returns hero
     * @return
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * setHero sets hero
     * @param hero
     */
    public void setHero(final Hero hero) {
        this.hero = hero;
    }

    /**
     * getMana returns mana
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     * setMana sets mana
     * @param mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * toString returns all variables for prin
     * @return
     */
    @Override
    public String toString() {
        return "Playerv2{"
                + "nrCardsInDeck=" + nrCardsInDeck
                + ", deck=" + deck
                + ", hand=" + hand
                + ", hero=" + hero
                + ", mana=" + mana
                + '}';
    }
}
