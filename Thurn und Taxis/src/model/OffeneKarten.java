package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Hier liegen die für die Spieler sichtbaren Karten welche gezogen werden koennen.
 * Die ersten Karten kommen von der Ablage, dannach werden sie vom Deck nachgezogen.
 * <p>
 * Karten duerfen nur über karteNachlegen() in die Liste offeneKarten gelangen.
 * Karten duerfen nur ueber karteZiehen() die List offeneKarten verlassen.
 * 
 * @author Walter
 *
 */
public class OffeneKarten {
	
	private ArrayList<Stadt> offeneKarten;
	
	private final int MAX_ANZAHL_KARTEN = 6;
	
	OffeneKarten() {
		offeneKarten = new ArrayList<Stadt>();
	}
	
	/**
	 * Nur ueber diese Methode werden Karten gezogen.
	 * Wenn eine Karte gezogen wird, gibt offeneKarten die gewaehlte Karte dem Spieler.
	 * Hierbei muss eine Karte vom Deck nachgezogen werden
	 * @return
	 */
	public Stadt karteZiehen( int idx, Stadt karteVomDeck) {
		if(istLeer()) {
			return null;
		}
		
		Stadt karte = offeneKarten.remove(idx);
		
		offeneKarten.add(idx, karteVomDeck);		
		
		return karte;
	}

	/**
	 * Nur über diese Methode darf offeneKarten Karten erhalten.
	 */
	public boolean karteNachlegen( Stadt karte ) {
		if(istVoll()) {
			return false;
		}
		offeneKarten.add(karte);
		return true;
	}
	
	public boolean istVoll() {
		return offeneKarten.size() == MAX_ANZAHL_KARTEN; 
	}
	
	private boolean istLeer() {
		return offeneKarten.size() == 0; 
	}
	
	public void printList() {
		int counter = 0;
		System.out.println("###OffeneKarten###");
		for( int i=0; i<offeneKarten.size(); i++) {
			System.out.println(offeneKarten.get(i).getName() + " " + counter++ );
		}
		System.out.println("###OffeneKarten Ende###");
	}
}
