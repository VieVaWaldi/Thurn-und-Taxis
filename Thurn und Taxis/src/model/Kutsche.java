package model;

/**
 * Kutschen erhaelt man fortlaufend fuer laengere Routen.
 * Durch das erreichen einer Kutsche erhaelt man Punkte.
 * <p>
 * Gibt durch das Interface Punkte die erworbenen Punkte zurueck.
 *
 * @author Walter
 *
 */
public class Kutsche implements Punkte {

	private int momentaneKutsche;
	private int punkte;

	Kutsche() {
		momentaneKutsche = 0;
	}

	/**
	 * Anhand der jetzigen groeﬂten Kutsche sowie der neuen Routenlaenge
	 * wird geprueft ob, ob der Spieler eine neue Kutsche erhaelt.
	 * Wenn ein Spieler die 5. Kutsche erreicht wird die letzte Runde gestartet.
	 * <p>
	 * Muss nach routeBeenden() aufgerufen werden.
	 * Nach dieser Methode muss punkteBerechnen() aufgerufen werden.
	 *
	 * @param routenLaenge
	 * @return true wenn die letzte Kutsche erreicht wird
	 */
	public void kutschePruefen( int routenLaenge ) {

		if( routenLaenge > momentaneKutsche ) {
			if( momentaneKutsche == 0 ) {
				momentaneKutsche = 3;
			}
			else {
				momentaneKutsche++;
			}
			punkte = momentaneKutsche;
		}
		else {
			punkte = 0;
		}
	}

	/**
     * Muss als letzte in einer SpielRunde aufgerufen werden.
     *
     * @return boolean Spiel wird beendet
     */
    public boolean letzteRundeErreicht() {
    	return momentaneKutsche == 7;
    }

	@Override
	public int punkteBerechnen() {
		return punkte;
	}

}
