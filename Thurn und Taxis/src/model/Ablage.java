package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Hier werden alle Karten initialisert und alle genutzen Karten waehrend des Spiels abgelegt.
 * Zu Beginn und wenn das Deck leer ist werden diese an das Deck verteilt.
 * <p>
 * Karten duerfen nur über karteAblegen() in die Liste ablage gelangen.
 * Karten duerfen nur ueber kartenVerteilen() die Liste ablage verlassen.
 *
 * @author Walter
 *
 */
public class Ablage {

	private Map map;

	private ArrayList<Karte> ablage;			// Nehme ich weil shuffle

	private final int MAX_ANZAHL_KARTEN = 66;

	/**
	 * Muss von den KartenKlassen als letzte initialisiert werden, da sie die anderen fuellt.
	 */
	Ablage( Map map ) {
		this.map = map;
		ablage = new ArrayList<Karte>();
		initAblage();
	}

	private void initAblage() {

		int kartenId = 0;

		for( int i=0; i<MAX_ANZAHL_KARTEN/3; i++ ) {
			ablage.add( new Karte(map.getStadt(i), kartenId) );
		}

		for( int i=0; i<MAX_ANZAHL_KARTEN/3; i++ ) {
			ablage.add( new Karte(map.getStadt(i), kartenId) );
		}

		for( int i=0; i<MAX_ANZAHL_KARTEN/3; i++ ) {
			ablage.add( new Karte(map.getStadt(i), kartenId) );
		}
	}

	private void mischen() {
		Collections.shuffle(ablage);
	}

	/**
	 * Nur ueber diese Methoden duerfen Karten die Ablage verlassen.
	 * Mischt die Karten. Verteilt die Karten dann an das Deck wenn Deck leer ist
	 * & zu Beginn dies Spiels.
	 */
	public void kartenVerteilen( Deck deck ) {

		mischen();

		while(!istLeer()) {
			Karte karte = ablage.remove(ablage.size()-1);
			deck.karteNachlegen(karte);
		}
	}

	/**
	 * Nur über diese Methode darf ablage Karten erhalten.
	 */
	public void karteAblegen( Karte karte ) {
		ablage.add(karte);
	}

	private boolean istLeer() {
		return ablage.size() == 0;
	}

	public void printList() {
		int counter = 0;
		System.out.println("###Ablage###");
		for( int i=0; i<ablage.size(); i++) {
			System.out.println(ablage.get(i).getStadt() + " #" + counter++ );
		}
		System.out.println("###Ablage Ende###");
	}
	
	public Karte getKarte( int idx ) {
		return ablage.get(idx);
	}
}
