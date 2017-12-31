package model;

// Temporäre main klasse

/**
 *
 * @author Walter
 *
 */
public class Fassade {

    public static void main(String[]args) {

        Deck deck = new Deck();
        Ablage ablage = new Ablage();
        OffeneKarten ok = new OffeneKarten();
        
        HandKarten hk1 = new HandKarten();
        HandKarten hk2 = new HandKarten();
        
        deck.printList();
        ablage.printList();
        ok.printList();
        hk1.printList();
        hk2.printList();
        
        ablage.kartenVerteilen(deck);

        while(!ok.istVoll()) {
        	ok.karteNachlegen(deck.karteZiehen());
        }
        
        hk1.karteZiehen(deck.karteZiehen());
        hk1.karteZiehen(deck.karteZiehen());
        hk1.karteZiehen(deck.karteZiehen());
        
        hk2.karteZiehen(ok.karteZiehen(3, deck.karteZiehen()));
        hk2.karteZiehen(ok.karteZiehen(3, deck.karteZiehen()));
        hk2.karteZiehen(deck.karteZiehen());
        
        deck.printList();
        ablage.printList();
        ok.printList();
        hk1.printList();
        hk2.printList();
        ok.printList();
    }
}
