package model;

/**
 * RoutenBP´s erhält man für das erreichen einer bestimmten Routenlänge.
 *
 * @author Walter
 *
 */
public class RoutenBP implements Punkte {

	private int punkte;
	private String beschreibung;

	private int AUFRUFZAHL_LAENGE_5;
	private int AUFRUFZAHL_LAENGE_6;
	private int AUFRUFZAHL_LAENGE_7;

	RoutenBP() {
		AUFRUFZAHL_LAENGE_5 = 2;
		AUFRUFZAHL_LAENGE_6 = 3;
		AUFRUFZAHL_LAENGE_7 = 4;
		beschreibung = "Wenn die Rote 5, 6, 7 oder mehr Staedte enthaelt, erhaelt der Spieler das hoechste Bonusplaettchen fuer die Routenlaenge. Jedes BP kann nur eine bestimmte Anzahl mal verteilt werden.";
	}

	/**
	 * Diese Methode wird nach haeuserSetzen() aufgerufen.
	 * Nach dieser Methode muss immer punkteBerechnen() aufgerufen werden.
	 * Wenn Bedingung nicht erfuellt ist werden 0 Punkte zurueck gegeben.
	 * Wenn Spieler eine Kutsche erhaelt wird geprueft ob es dafuer noch Punkte gibt.
	 *
	 * @param routenLaenge
	 * @return
	 */
	public void pruefeBedingung( int routenLaenge ) {

		if( routenLaenge == 5 ) {
			laenge5();
			return;
		}
		if( routenLaenge == 6 ) {
			laenge6();
			return;

		}
		if( routenLaenge >= 7 ) {
			laenge7();
			return;
		}

		punkte = 0;
	}

	private void laenge5() {

		if( AUFRUFZAHL_LAENGE_5 == 0 ) {
			punkte = 0;
			return;
		}

		punkte = AUFRUFZAHL_LAENGE_5;
		AUFRUFZAHL_LAENGE_5--;
	}

	private void laenge6() {

		if( AUFRUFZAHL_LAENGE_6 == 0 ) {
			punkte = 0;
			return;
		}

		punkte = AUFRUFZAHL_LAENGE_6;
		AUFRUFZAHL_LAENGE_6--;
	}

	private void laenge7() {

		if( AUFRUFZAHL_LAENGE_7 == 0 ) {
			punkte = 0;
			return;
		}

		punkte = AUFRUFZAHL_LAENGE_7;
		AUFRUFZAHL_LAENGE_7--;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Muss nach pruefeBedingung() aufgerufen werden.
	 */
	@Override
	public int punkteBerechnen() {
		return punkte;
	}
}
