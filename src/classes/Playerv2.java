package classes;

import java.util.ArrayList;

public class Playerv2 {
    int nrCardsInDeck = 0;
    ArrayList<Cards> deck = new ArrayList<Cards>();

    ArrayList<Cards> hand = new ArrayList<Cards>();
    Hero hero;
    int mana;

    public Playerv2() {
    }

    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    public void setNrCardsInDeck(int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    public ArrayList<Cards> getDeck() {
        return deck;
    }

    public ArrayList<Cards> getHand() { return hand; }

    public void setHand(ArrayList<Cards> hand) { this.hand = hand; }

    public void setDeck(ArrayList<Cards> deck) {
        this.deck = deck;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String toString() {
        return "Playerv2{" +
                "nrCardsInDeck=" + nrCardsInDeck +
                ", deck=" + deck +
                ", hand=" + hand +
                ", hero=" + hero +
                ", mana=" + mana +
                '}';
    }
}