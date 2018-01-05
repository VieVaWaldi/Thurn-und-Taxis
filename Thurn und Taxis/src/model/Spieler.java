package model;

import java.util.ArrayList;

import javafx.scene.image.Image;

// ### lass dir ne schöne beschreibung einfallen ### //

/**
 * Repraesentiert den Spieler. Der Spieler vereinigt alle Informationen
 * und Instanzen die den Spieler ausmachen.
 * <p>
 * Der Spieler erhaelt Karten vom Spiel ueber Deck oder OffeneKarten.
 * Der Spieler gibt ueber seine HandKarten karten an die Ablage im Spiel.
 *
 * @author Walter
 */
public class Spieler {

	private String name;
	private int spielerNr;
	private String farbe;
	private Image icon;

	private int punkte;
	private ArrayList<Stadt> beendeteRoute;
	private Haeuser haeuser;
	private Bonusplaettchen bonusplaettchen;
	private Kutsche kutsche;

	private boolean letzteRunde;

	private HandKarten handkarten;

	private SpielerRoute route;

	/**
	 * @param name
	 * @param spielerNr
	 * @param farbe
	 * @param icon
	 * @param map
	 */
	Spieler( Map map, Bonusplaettchen bonusplaettchen, String name, int spielerNr, String farbe, Image icon ) {

		this.name = name;
		this.spielerNr = spielerNr;
		this.farbe = farbe;
		this.icon = icon;

		punkte = 0;
		haeuser = new Haeuser();
		this.bonusplaettchen = bonusplaettchen;
		kutsche = new Kutsche();
		letzteRunde = false;
		handkarten = new HandKarten();
		route = new SpielerRoute(map);
	}

	// For testing
	Spieler( Map map, Bonusplaettchen bonusplaettchen) {

		punkte = 0;
		beendeteRoute = new ArrayList<Stadt>();
		haeuser = new Haeuser();
		this.bonusplaettchen = bonusplaettchen;
		kutsche = new Kutsche();
		letzteRunde = false;
		handkarten = new HandKarten();
		route = new SpielerRoute(map);
	}

	/**
	 * Karte kommt vom den offenen Karten oder vom Deck
	 * und wird den Handkarten des Spielers hinzugefuegt.
	 *
	 * @param karte die gezogen wurde
	 */
	public void karteZiehen( Karte karte ) {
		handkarten.karteZiehen(karte);
	}

	/**
	 * Stadt wird von den Handkarten ausgewaehlt. Zuerst muss mit
	 * routeKannGelegtWerden() geprueft werden ob Route gelegt werden kann.
	 * Wenn dem so ist wird die Karte gespielt & abgelegt.
	 *
	 * @param stadtAusHandkarten von den Handkarten zum ueberpruefen ob Route moeglich ist
	 * @param idx ( = Index ) der Karte vom Spieler in seinen Handkarten
	 * @return Karte die auf die Ablage kommt
	 */
	public Karte routeLegen( int idx ) {

		route.routeLegen( handkarten.testeObRouteMoeglichIst(idx) );
		return handkarten.karteAblegen(idx);

	}

	/**
	 * Das muss geprueft werden bevor der Spieler die Route legt.
	 * 
	 * @param idx ( = Index ) der Handkarten des Spielers
	 * @return boolean true wenn die Route gelegt werden kann
	 */
	public boolean routeKannGelegtWerden( int idx ) {
		
		return route.routeKannGelegtWerden( handkarten.testeObRouteMoeglichIst(idx));		
	}
	
	/**
	 * Wenn Route beendet werden kann wird die aktuelle Route
	 * aus Spielerroute geloescht und temporaer in Spieler gespeichert.
	 * Wenn Route nicht beendet wird, wird sie automatisch pausiert und
	 * nur die Methoden zuVieleKartenAblegen() und letzteRunde() muessen
	 * gestartet werden.
	 *
	 * @return boolean Route wurde erfolgreich beendet
	 */
	public boolean routeBeenden() {

		if( route.routeKannBeendetWerden() ) {

			beendeteRoute = null;
			
			beendeteRoute = route.routeBeenden();

			return true;
		}

		return false;
	}

	/**
	 * Erst aufrufen nach dem Route erfolgreich beendet wurde.
	 * Nach dem setzen darf der Spieler die Auswahl bestaetigen oder zuruecksetzen.
	 *
	 * @return boolean gibt solange false zurueck bis keine Haeuser mehr gesetzt werden koennen
	 */
	public boolean haeuserSetzen( Stadt stadt ) {

		return haeuser.haeuserSetzen( beendeteRoute, stadt);
	}

	/**
	 * Erst aufrufen nachdem Route erfolgreich beendet wurde.
	 * Hierbei werden die Punkte automatisch berechnet.
	 * Wenn Haeuser besteatigt muss wird bonusPlaettchenSammeln() aufgerufen werden.
	 *
	 * @param bestaetigt Haeuser koennen gesetzt werden
	 * @return boolean false wenn Haeuser nochmal gesetzt werden sollen.
	 */
	public void haeuserBestaetigen() {

		haeuser.haeuserBestaetigen();
		letzteRunde = haeuser.letzteRundeErreicht();
		punkte += haeuser.punkteBerechnen();
	}

	/**
	 * Wenn diese Methode aufgerufen wurde muss haeuserSetzen() nochmal aufgerufen werden.
	 */
	public void haeuserZuruecksetzen() {
		haeuser.haeuserZuruecksetzen();
	}

	/**
	 * Erst nach haeuserBestaetigten() aufrufen.
	 * Hierbei werden die Punkte automatisch berechnet.
	 */
	public void bonusPlaettchenSammelm() {

		int routenLaenge = beendeteRoute.size();

		punkte += bonusplaettchen.punkteBerechnen(routenLaenge, beendeteRoute, this);
	}

	/**
	 * Erst nach bonusPlaettchenSammeln() aufrufen.
	 * Hierbei werden die Punkte automatisch berechnet.
	 */
	public void kutscheErhalten() {

		int routenLaenge = beendeteRoute.size();

		kutsche.kutschePruefen(routenLaenge);
		letzteRunde = kutsche.letzteRundeErreicht();

		punkte += kutsche.punkteBerechnen();
	}

	/**
	 * Diese Methode wird am Ende des Spielzuges aufgerufen.
	 * Davor muss mit mehrAlsDreiKarten() geprueft werden ob der Spieler mehr als 3 Karten hat.
	 * Die abgelegte Karte kommt in die Ablage.
	 *
	 * @param idx ( = Index ) der Karte des Spielers
	 * @return Karte wenn Spieler mehr als Drei Karten hat
	 */
	public Karte zuVieleKartenAblegen( int idx ) {

		if( mehrAlsDreiKarten() ) {
			return handkarten.karteAblegen(idx);
		}

		return null;
	}

	public boolean mehrAlsDreiKarten() {
		return handkarten.mehrAlsDreiKarten();
	}

	/**
	 * Diese Methode muss als letztes aufgerufen werden.
	 *
	 * @return boolean die letzte Runde wurde erreicht
	 */
	public boolean letzteRundeErreicht() {
		return letzteRunde;
	}

	public void printHandKarten() {
		handkarten.printList();
	}

	public String toString() {
		return name + " #" + spielerNr + " hat " + punkte;
	}
}
