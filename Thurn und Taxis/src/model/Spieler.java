package model;

import java.util.ArrayList;

import javafx.scene.image.Image;

// ### lass dir ne schöne beschreibung einfallen ### //

/**
 * Spieler enthaelt alle Informationen ueber den Spieler und
 * alle Optionen die er ausfuehren kann ( Außer Amtsmaenner, die sind in Spielzug ).
 * Soviele alle Informationen und Methoden die Spielzug braucht um mit Spieler zu arbeiten.
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

	/**
	 * For testing only
	 *
	 * @param map
	 * @param bonusplaettchen
	 */
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

	//########################### Phase 1  ###########################//

	/**
	 * Karte kommt von den offenen Karten oder vom Deck
	 * und wird den Handkarten des Spielers hinzugefuegt.
	 * <p>
	 * Es darf nur eine Karte pro Runde gezogen werden,
	 * ausser der Postmeister wurde genutzt.
	 *
	 * @param karte die gezogen wurde
	 */
	public void karteZiehen( Karte karte ) {
		handkarten.karteZiehen(karte);
	}

	/**
	 * Wenn der Spieler nur eine Karte hat wird der Postmann automatisch genutzt.
	 *
	 * @return
	 */
	public boolean hatEineKarte() {
		return handkarten.hatEineKarte();
	}

	//########################### Phase 1  ###########################//

	/**
	 * Stadt wird von den Handkarten ausgewaehlt. Zuerst muss mit
	 * routeKannGelegtWerden() geprueft werden ob Route gelegt werden kann.
	 * Wenn dem so ist wird die Karte gespielt & abgelegt.
	 * <p>
	 * Es darf nur eine Stadt pro Runde gelegt werden,
	 * ausser der Postillion wurde genutzt.
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

	//########################### Phase 2  ###########################//

	/**
	 * Wenn Route beendet werden kann wird die aktuelle Route
	 * aus Spielerroute geloescht und temporaer in Spieler gespeichert.
	 * Wenn Route nicht beendet wird, wird sie automatisch pausiert und
	 * nur die Methoden zuVieleKartenAblegen() und letzteRunde() muessen
	 * gestartet werden.
	 *
	 * @return boolean false wenn die Route nicht beendet werden kann
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
	 * Wenn der Spieler die Route nicht weiterfuehren moechte oder das nicht kann
	 * muss die Route automatisch verworfen werden.
	 */
	public void routeVerwerfen() {
		route.routeVerwerfen();
	}

	//########################### Phase 3  ###########################//

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
	 * <p>
	 * Wenn der Spieler den Wagner aktiviert braucht er 2 Staedte weniger
	 * auf die naechste Kutsche.
	 */
	public void kutscheErhalten( boolean wagnerGenutzt ) {

		int routenLaenge = beendeteRoute.size();

		kutsche.kutschePruefen( routenLaenge, wagnerGenutzt );
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

	//########################### Helfer ###########################//

	public void printHandKarten() {
		handkarten.printList();
	}

	public String toString() {
		return name + " #" + spielerNr + " hat " + punkte;
	}

	/**
	 * Zum anzeigen der Punkte des Spielers
	 * @return
	 */
	public int getPunkte() {
		return punkte;
	}
}
