package model;

import java.util.ArrayList;

import javafx.scene.image.Image;

// ### lass dir ne schöne beschreibung einfallen ### //

/**
 * Repraesentiert den Spieler. Der Spieler vereinigt alle Informationen
 * und Instanzendie den Spieler ausmachen.
 * <p>
 * Der Spieler erhäält Karten vom Spiel ueber Deck oder OffeneKarten.
 * Der Spieler gibt ueber seine HandKarten karten an die Ablage im Spiel.
 *
 *
 * @author Walter
 *
 */
public class Spieler {

	private String name;
	private int spielerNr;
	private String farbe;
	private Image icon;

	private int punkte;
	private Haeuser haeuser;
	private ArrayList<Stadt> routeFuerHaeuser;
	private Kutsche kutsche;

	private HandKarten handkarten;

	private SpielerRoute route;

	/**
	 * @param name
	 * @param spielerNr
	 * @param farbe
	 * @param icon
	 * @param map
	 */
	Spieler( String name, int spielerNr, String farbe, Image icon, Map map ) {

		this.name = name;
		this.spielerNr = spielerNr;
		this.farbe = farbe;
		this.icon = icon;

		punkte = 0;
		haeuser = new Haeuser();
		routeFuerHaeuser = new ArrayList<Stadt>();
		kutsche = new Kutsche();
		handkarten = new HandKarten();
		route = new SpielerRoute(map);
	}

	// For testing
	Spieler() {

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
	 * Stadt wird von den Handkarten ausgewaehlt. Zuerst wird geprueft,
	 * ob die Route gelegt werden kann. Wenn dem so ist wird die Karte gespielt & abgelegt.
	 *
	 * @param stadt von den Handkarten zum ueberpruefen ob Route moeglich ist
	 * @param idx ( = Index ) der Karte vom Spieler in seinen Handkarten
	 * @return Karte die auf die Ablage kommt
	 */
	public Karte routeLegen( Stadt stadt, int idx ) {

		if( route.routeKannGelegtWerden(stadt)) {

			route.routeLegen(stadt);
			return handkarten.karteAblegen(idx);
		}

		System.out.println("Spieler: Route kann nicht geleget werden.");
		return null;
	}

	/**
	 * Wenn Route beendet werden kann wird die aktuelle Route
	 * aus Spielerroute geloescht und temporaer in Spieler gespeichert.
	 * Wenn Route nicht beendet wird, wird sie automatisch pausiert.
	 *
	 * @return boolean Route wurde erfolgreich beendet
	 *
	 */
	public boolean routeBeenden() {

		if( route.routeKannBeendetWerden() ) {

			for( int i=0; i< routeFuerHaeuser.size(); i++) {
				routeFuerHaeuser.remove(i);
			}

			routeFuerHaeuser = route.routeBeenden();

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

		return haeuser.haeuserSetzen(routeFuerHaeuser, stadt);
	}

	/**
	 * Erst aufrufen nachdem Route erfolgreich beendet wurde.
	 * Wenn Haeuser besteatigt wurden wird bonusPlaettchenSammeln() aufgerufen.
	 * Wenn Haeuser zurueckgesetzt aufgerufen wurde muss haeuserSetzen() nochmal aufgerufen werden.
	 *
	 * @param bestaetigt Haeuser koennen gesetzt werden
	 * @return boolean false wenn Haeuser nochmal gesetzt werden sollen.
	 */
	public boolean haeuserBestaetigen( boolean bestaetigt ) {

		if( bestaetigt ) {
			haeuser.haeuserBestaetigen();
			punkte += haeuser.punkteBerechnen();
			return true;
		}

		haeuser.haeuserZuruecksetzen();
		return false;
	}

	/**
	 *
	 */
	public void bonusPlaettchenSammelm() {

	}

	public void kutscheErhalten() {

	}







	/**
	 * Diese Methode wird am Ende des Spielzuges aufgerufen.
	 * Davor muss geprueft werden ob der Spieler mehr als 3 Karten hat.
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
	 * Nur diese Punkte Methode wird vom Spiel selber aufgerufen.
	 * Muss am Ende des Zuges aufgerufen werden.
	 * Gibt 0 Punkte zurueck wenn keine verdient worden sind.
	 *
	 * @param bpPunkte
	 */
	public void punkteBerechnen( int bpPunkte ) {

		//haeuser.
		punkte += haeuser.punkteBerechnen();
		punkte += kutsche.punkteBerechnen();

	}
}
