package model;

/**
 * Eine der 4 Amtspersonen.
 * Der Postmeister erlaubt es dem Spieler eine zweite Karte zu ziehen.
 *
 * @author Walter
 *
 */
public class Postmeister extends Amtspersonen{

	Postmeister() {
		this.name = "Postmeister";
		this.beschreibung = "Der Postmeister erlaubt es eine zweite Karte zu ziehen.";
		this.apGenutzt = false;
	}

	/**
	 * Wenn der Amtsmann genutzt wurde darf kein anderer Amtsmann
	 * in der gleichen Runde genutzt werden. Geprueft wird das mit der Variable
	 * apGenutzt in Spielzug.
	 *
	 * @return boolean true wenn der Amtsmann genutzt wurde.
	 */
	public boolean apNutzen() {

		apGenutzt = true;
		return true;
	}

	/**
	 * Muss immer zum Ende der Runde aufgerufen werden,
	 * damit die Amtsmaenner fuer den naechsten Spieler wieder
	 * aller zur Verfuegung stehen.
	 */
	public void apZurueckSetzten() {
		apGenutzt = false;
	}

}