package model;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * Das Spiel selber. Beim erstellen der Instanz wird anhand der Parameter ausgewaehlt,
 * wieviele Spieler mitspielen. Der Spieler der anfaengt muss vom Hauptemnu als erstes uebergeben werden.
 *
 * @author Walter
 *
 */
public class Spiel {

	private Spielzug spielzug;
	private ArrayList<Spieler> spieler;
	private int spielerNr;

	private Map map;

	private Deck deck;
	private Ablage ablage;
	private OffeneKarten offeneKarten;

	private Bonusplaettchen bonusPlaettchen;

	//########################## Konstruktoren ##########################//

	/**
	 * Erzeugt das Spiel fuer 2 Spieler.
	 * Informationen fuer den Spieler entstehen im Hauptmenu.
	 */
	Spiel( String name1, String farbe1, Image icon1,
			String name2, String farbe2, Image icon2 ) {

		spielerNr = 0;
		spielInitialisieren();
		zweiSpielerInitialisieren( map, name1, farbe1, icon1, name2, farbe2, icon2 );
	}

	/**
	 * Erzeugt das Spiel fuer 3 Spieler.
	 * Informationen fuer den Spieler entstehen im Hauptmenu.
	 */
	Spiel( String name1, String farbe1, Image icon1,
			String name2, String farbe2, Image icon2,
			String name3, String farbe3, Image icon3 ) {

		spielerNr = 0;
		spielInitialisieren();
		dreiSpielerInitialisieren( map, name1, farbe1, icon1, name2, farbe2, icon2, name3, farbe3, icon3 );
	}

	/**
	 * Erzeugt das Spiel fuer 4 Spieler.
	 * Informationen fuer den Spieler entstehen im Hauptmenu.
	 */
	Spiel( String name1, String farbe1, Image icon1,
			String name2, String farbe2, Image icon2,
			String name3, String farbe3, Image icon3,
			String name4, String farbe4, Image icon4 ) {

		spielerNr = 0;
		spielInitialisieren();
		vierSpielerInitialisieren( map, name1, farbe1, icon1, name2, farbe2, icon2, name3, farbe3, icon3, name4, farbe4, icon4 );
	}

	//########################## Methoden ##########################//

	private final void spielInitialisieren() {

		map = new Map();

		deck = new Deck();
		offeneKarten = new OffeneKarten();
		ablage = new Ablage(map);

		ablage.kartenVerteilen(deck);

		bonusPlaettchen = new Bonusplaettchen(map);
	}

	private final void zweiSpielerInitialisieren( Map map, String name1, String farbe1, Image icon1,
			String name2, String farbe2, Image icon2 ) {

		Spieler spieler1 = new Spieler(map, bonusPlaettchen, name1, spielerNr++, farbe1, icon1 );
		Spieler spieler2 = new Spieler(map, bonusPlaettchen, name2, spielerNr++, farbe2, icon2 );

		spieler.add(spieler1);
		spieler.add(spieler2);
	}

	private final void dreiSpielerInitialisieren( Map map, String name1, String farbe1, Image icon1,
			String name2, String farbe2, Image icon2,
			String name3, String farbe3, Image icon3 ) {

		Spieler spieler1 = new Spieler(map, bonusPlaettchen, name1, spielerNr++, farbe1, icon1 );
		Spieler spieler2 = new Spieler(map, bonusPlaettchen, name2, spielerNr++, farbe2, icon2 );
		Spieler spieler3 = new Spieler(map, bonusPlaettchen, name3, spielerNr++, farbe3, icon3 );

		spieler.add(spieler1);
		spieler.add(spieler2);
		spieler.add(spieler3);
	}

	private final void vierSpielerInitialisieren( Map map, String name1, String farbe1, Image icon1,
			String name2, String farbe2, Image icon2,
			String name3, String farbe3, Image icon3,
			String name4, String farbe4, Image icon4 ) {

		Spieler spieler1 = new Spieler(map, bonusPlaettchen, name1, spielerNr++, farbe1, icon1 );
		Spieler spieler2 = new Spieler(map, bonusPlaettchen, name2, spielerNr++, farbe2, icon2 );
		Spieler spieler3 = new Spieler(map, bonusPlaettchen, name3, spielerNr++, farbe3, icon3 );
		Spieler spieler4 = new Spieler(map, bonusPlaettchen, name4, spielerNr++, farbe4, icon4 );

		spieler.add(spieler1);
		spieler.add(spieler2);
		spieler.add(spieler3);
		spieler.add(spieler4);
	}

	public Spielzug getSpielzug() {
		return spielzug;
	}

}
