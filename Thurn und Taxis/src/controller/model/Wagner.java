package controller.model;

/**
 * Eine der 4 Amtspersonen.
 * Mit dem Wagner braucht man 2 Staedte weniger auf die naechste Kutsche.
 *
 * @author Walter
 *
 */
public class Wagner extends Amtspersonen{

	Wagner() {
		this.name = "Wagner";
		this.beschreibung = "Du brauchst 2 Staedte weniger um die nächste Kutsche zu bekommen.";
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
