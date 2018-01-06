package model;

import java.util.ArrayList;

/**
 * Diese Klasse verwaltet alle Bonusplaettchen und hält ale Instanzen.
 * BP´s sind BonusPunkte die man für bestimmte Aktionen erhält.
 * Im Gegensatz zu den Kutschen gibt es jedes Bonusplaettchen nur 1 mal fuer alle Spieler.
 *
 * @author Walter
 */
public class Bonusplaettchen {

	private int punkte;

	private AlleProvinzenBP alleProvinzenBP;
	private ProvinzBP provinzBP;
	private RoutenBP routenBP;

	public Bonusplaettchen( Map map ) {

		punkte = 0;

		alleProvinzenBP = new AlleProvinzenBP(map);
		provinzBP = new ProvinzBP(map);
		routenBP = new RoutenBP();
	}

	/**
	 * Diese Methode prueft alle Bedingungen fuer alle Bonusplaettchen
	 * und gibt die akkumulierten Punkte zurueck.
	 */
	public int punkteBerechnen( int routenLaenge,  ArrayList<Stadt> staedteMitHaeusern, Spieler spieler ) {

		alleProvinzenBP.pruefeBedingung(staedteMitHaeusern, spieler);
		punkte += alleProvinzenBP.punkteBerechnen();
		System.out.println(punkte);

		provinzBP.pruefeBedingung(staedteMitHaeusern, spieler);
		punkte += provinzBP.punkteBerechnen();
		System.out.println(punkte);

		routenBP.pruefeBedingung(routenLaenge);
		punkte += routenBP.punkteBerechnen();
		System.out.println(punkte);

		int tmp = punkte;
		punkte = 0;

		return tmp;
	}
}