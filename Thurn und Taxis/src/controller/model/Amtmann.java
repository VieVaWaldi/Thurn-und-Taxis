package controller.model;

/**
 * Eine der 4 Amtspersonen.
 * Der Amtmann tauscht die offenen Karten aus.
 *
 * @author Walter
 *
 */
public class Amtmann extends Amtspersonen{

	Amtmann() {
		this.name = "Amtmann";
		this.beschreibung = "Die offenen Karten werden ausgetauscht.";
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
	 * Muss immer zum Beginn der Runde aufgerufen werden,
	 * damit die Amtsmaenner fuer den naechsten Spieler wieder
	 * aller zur Verfuegung stehen.
	 */
	public void apZurueckSetzten() {
		apGenutzt = false;
	}

}