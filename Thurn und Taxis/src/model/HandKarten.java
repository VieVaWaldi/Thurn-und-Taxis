package model;

import java.util.ArrayList;

/**
 * Die nur jeweiligen Handkarten der Spieler. Wenn verwendet wandern sie zur Ablage.
 * <p>
 * Karten duerfen nur �ber karteZiehen() in die Liste handkarten gelangen.
 * Karten duerfen nur ueber karteAblegen() die List handkarten verlassen.
 * 
 * @author Walter
 *
 */
public class HandKarten {

	private ArrayList<Stadt> handkarten;
	
	private final int MAX_ANZAHL_KARTEN = 6;
	
	public HandKarten() {
		handkarten = new ArrayList<Stadt>();
	}
	
	/**
	 * Nur ueber diese Methode duerfen Karten in diese Liste gelangen.
	 * Davor muss ueberprueft werden ob handkarten voll ist!.
	 */
	public void karteZiehen( Stadt karte ) {
		if(istVoll()) {
			System.out.println("HandKarten: zu viele Karetn auf der Hand");
		}
		else {
			handkarten.add(karte);
		}		
	}
	
	/**
	 * Nur ueber diese Methode duerfen Karten diese Liste verlassen.
	 * Benutzte Karten werden zu Routen & dannach abgelegt.
	 * Bevor diese Methode aufgerufen wird muss geprueft werden ob die Route gebaut werden kann.
	 */
	public Stadt karteAblegen( int idx ) {
		if(istLeer()) {
			System.out.println("HandKarten: Keine Karten auf der Hand");
		}
		
		return handkarten.remove(idx);
	}
	
	public boolean istVoll() {
		return handkarten.size() == MAX_ANZAHL_KARTEN; 
	}
	
	public boolean istLeer() {
		return handkarten.size() == 0;
	}
	
	public void printList() {
		int counter = 0;
		System.out.println("###HandKarten###");
		for( int i=0; i<handkarten.size(); i++) {
			System.out.println(handkarten.get(i).getName() + " " + counter++ );
		}
		System.out.println("###HandKarten Ende###");
	}
}
