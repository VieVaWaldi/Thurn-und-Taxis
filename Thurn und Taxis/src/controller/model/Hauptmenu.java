package model;

import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * Im Hauptmenu kann man ein neues Spiel anfangen oder ein altes laden.
 * Beim erstellen eines neuen Spiels werden die Spieler erstellt ( Instanz entsteht in Spiel )
 * und an Spiel uebergeben. Es faengt der Spieler an der als erstes eingetragen wird.
 *
 * @author Walter
 *
 */
public class Hauptmenu {

	Spiel spiel;

	Map map;
	Bonusplaettchen bonusplaettchen;

	private String name1;
	private String farbe1;
	private Image icon1;

	private String name2;
	private String farbe2;
	private Image icon2;

	private String name3;
	private String farbe3;
	private Image icon3;

	private String name4;
	private String farbe4;
	private Image icon4;

	private int anzahlSpieler;

	Hauptmenu() {
		anzahlSpieler = 0;
	}

	/**
	 * Es koennen maximal 4 Spieler erstellt werden.
	 * @param name
	 * @param farbe
	 * @param icon
	 */
	public void spielerErstellen( String name, String farbe, Image icon ) {

		anzahlSpieler++;

		if( anzahlSpieler == 1 ) {
			name1 = name;
			farbe1 = farbe;
			icon1 = icon;
		}

		if( anzahlSpieler == 2 ) {
			name2 = name;
			farbe2 = farbe;
			icon2 = icon;
		}

		if( anzahlSpieler == 3 ) {
			name3 = name;
			farbe3 = farbe;
			icon3 = icon;
		}

		if( anzahlSpieler == 4 ) {
			name4 = name;
			farbe4 = farbe;
			icon4 = icon;
		}
	}

	/**
	 * Spiel kann erst gestartet werden wenn mindestens 2 Spieler ausgewaehlt sind.
	 */
	public void spielStarten() {

		if( anzahlSpieler < 2 ) {
			return;
		}

		if( anzahlSpieler == 2 ) {
			spiel = new Spiel(name1, farbe1, icon1, name2, farbe2, icon2);
		}

		if( anzahlSpieler == 3 ) {
			spiel = new Spiel(name1, farbe1, icon1, name2, farbe2, icon2, name3, farbe3, icon3);
		}

		if( anzahlSpieler == 4 ) {
			spiel = new Spiel(name1, farbe1, icon1, name2, farbe2, icon2, name3, farbe3, icon3, name4, farbe4, icon4);
		}

	}

	public void bildAuswaehlen() {

	}

	public void spielLaden() {

	}

	public void beenden() {
		Platform.exit();
	}

	public void lautstaerkeAnAUs() {

	}
}
