package controller.model;

/**
 * Eine der 4 Amtspersonen.
 * Mit dem Postillion kann man seine Route um 2 Staedte statt einer verlängern.
 *
 * @author Walter
 *
 */
public class Postillion extends Amtspersonen{

	Postillion() {
		this.name = "Postillion";
		this.beschreibung = "Du kannst deine Route um 2 Staedte erweitern.";
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