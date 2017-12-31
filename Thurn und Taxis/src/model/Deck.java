package model;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Hier sind alle verdeckten Karten die der Spieler ziehen kann.
 * <p>
 * Karten duerfen nur über karteNachlegen() in die Liste deck gelangen.
 * Karetn duerfen nur ueber karteZiehen() die List deck verlassen.
 * 
 * @author Walter
 *
 */
public class Deck {
	
	private ArrayList<Stadt> deck;
	
	private final int MAX_ANZAHL_KARTEN = 66;
	
	Deck() {
		deck = new ArrayList<Stadt>();
	}
	
	/**
	 * Nur über diese Methode werden Karten gezogen.
	 * Vor dieser Methode muss geprueft werden ob deck leer ist.
	 * Wenn das der Fall ist muss Ablage zuerst Karten an Deck neu verteilen. 
	 */
	public Stadt karteZiehen() {
		if(istLeer()) {
			System.out.println("Error: Keine Karten in Deck");
			return null;
		}
		
		return deck.remove(deck.size()-1);		
	}
	
	/**
	 * Nur über diese Methode darf deck Karten erhalten.
	 */
	public boolean karteNachlegen( Stadt karte ) {
		if(istVoll()) {
			return false;
		}
		deck.add(karte);
		return true;
	}
	
	public boolean istVoll() {
		return deck.size() == MAX_ANZAHL_KARTEN;
	}
	
	public boolean istLeer() {
		return deck.size() == 0;
	}	
	
	public void printList() {
		int counter = 0;
		System.out.println("###Deck###");
		for( int i=0; i<deck.size(); i++) {
			System.out.println(deck.get(i).getName() + " " + counter++ );
		}
		System.out.println("###Deck Ende###");
	}

}
