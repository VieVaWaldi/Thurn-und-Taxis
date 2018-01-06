package model;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Hier sind alle verdeckten Karten die der Spieler ziehen kann.
 * <p>
 * Karten duerfen nur über karteNachlegen() in die Liste deck gelangen.
 * Karten duerfen nur ueber karteZiehen() die List deck verlassen.
 * 
 * @author Walter
 *
 */
public class Deck {
	
	private ArrayList<Karte> deck;
	
	private final int MAX_ANZAHL_KARTEN = 66;
	
	Deck() {
		deck = new ArrayList<Karte>();
	}
	
	/**
	 * Nur über diese Methode werden Karten gezogen.
	 * <p>
	 * Vor dieser Methode muss mit deckIstLeer() geprueft werden ob Deck leer ist.
	 * Wenn das der Fall ist muss Ablage zuerst Karten an Deck neu verteilen.
	 */
	public Karte karteZiehen() {
		
		if(istLeer()) {
			System.out.println("Deck: Keine Karten im Deck. Verteile Karten erneut!");			
			return null;
		}
		
		return deck.remove(deck.size()-1);		
	}
	
	/**
	 * Nur über diese Methode darf deck Karten erhalten.
	 */
	public boolean karteNachlegen( Karte karte ) {
		if(istVoll()) {
			return false;
		}
		deck.add(karte);
		return true;
	}
	
	public boolean istVoll() {
		return deck.size() == MAX_ANZAHL_KARTEN;
	}
	
	/**
	 * Muss vor karteZiehen() geprueft werden.
	 * Wenn true muessen erst Karten von Ablage geholt werden bis Ablage leer ist.
	 * 
	 * @return
	 */
	public boolean istLeer() {
		return deck.size() == 0;
	}	
	
	public void printList() {
		int counter = 0;
		System.out.println("###Deck###");
		for( int i=0; i<deck.size(); i++) {
			System.out.println(deck.get(i).getStadt() + " #" + counter++ );
		}
		System.out.println("###Deck Ende###");
	}

}
