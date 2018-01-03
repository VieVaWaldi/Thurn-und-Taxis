package model;

import java.util.ArrayList;

/**
 * Die jeweiligen Handkarten der Spieler. Wenn verwendet wandern sie zur Ablage.
 * <p>
 * Karten duerfen nur über karteZiehen() in die Liste handkarten gelangen.
 * Karten duerfen nur ueber karteAblegen() die List handkarten verlassen.
 *
 * @author Walter
 *
 */
public class HandKarten {

	private ArrayList<Karte> handkarten;

	private final int MAX_ANZAHL_KARTEN = 6;

	public HandKarten() {
		handkarten = new ArrayList<Karte>();
	}

	/**
	 * Nur ueber diese Methode duerfen Karten in diese Liste handkarten gelangen.
	 */
	public void karteZiehen( Karte karte ) {
		if(istVoll()) {
			System.out.println("HandKarten: zu viele Karetn auf der Hand");
		}
		else {
			handkarten.add(karte);
		}
	}

	/**
	 * Nur ueber diese Methode duerfen Karten die Liste handkarten verlassen.
	 * Benutzte Karten werden zu Routen & dannach abgelegt.
	 * Bevor diese Methode aufgerufen wird muss geprueft werden ob die Route gebaut werden kann.
	 *
	 * @return Karte die der Spieler ablegt
	 */
	public Karte karteAblegen( int idx ) {
		if(istLeer()) {
			System.out.println("HandKarten: Keine Karten auf der Hand");
		}

		return handkarten.remove(idx);
	}

	/**
	 * Am Ende eines Zuges muessen alle bis auf 3 Karten
	 * die Handkarten des Spielers verlassen.
	 *
	 * @return boolean Spieler hat mehr als 3 Karten
	 */
	public boolean mehrAlsDreiKarten() {
		return handkarten.size() > 3;
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
			System.out.println(handkarten.get(i).getStadt() + " #" + counter++ );
		}
		System.out.println("###HandKarten Ende###");
	}
}
