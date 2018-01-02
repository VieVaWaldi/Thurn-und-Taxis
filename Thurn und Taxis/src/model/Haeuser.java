package model;

import java.util.ArrayList;

/**
 * Kuemmert sich um die Logik fuer das setzen/bestaetigen/zuruecksetzen
 * von Haeusern. Speichert zusaetzlich die verbliebene Anzahl der Häuser.
 * <p>
 * Gibt durch das Interface Punkte die erworbenen Punkte zurueck.
 *
 * @author Walter
 *
 */
public class Haeuser implements Punkte {

	ArrayList<Stadt> gesetzteHauser;
	private int anzahl;

	Haeuser() {
		gesetzteHauser = new ArrayList<Stadt>();
		anzahl = 20;
	}

	/**
	 * HauserSetzen wird solange aufgerufen bis es das System an
	 * hauserBestaetigen weiterleitet.
	 *
	 * @param staedte
	 * @param stadt
	 * @retuen LT DIE LISTE ?
	 */
	public void haeuserSetzen( ArrayList<Stadt> staedte, Stadt stadt ) {

		if( !istStadtTeilderRoute(staedte, stadt) ) {
			System.out.println("Stadt ist nicht Teil der Route");
			return;
		}

		if( gesetzteHauser.isEmpty() ) {
			gesetzteHauser.add(stadt);
		}

		/*
		for( int i=0; i<gesetzteHauser.size()-1; i++ ) {
			//#### ALLEtädte und Provinzen kommen von MAP ###//
			if( gesetzteHauser.get(i).getProvinz() ==))
		}*/

	}

	public boolean istStadtTeilderRoute( ArrayList<Stadt> staedte, Stadt stadt ) {

		boolean stadtGehoertZurRoute = false;

		for( int i=0; i<staedte.size()-1; i++ ) {
			if( stadt.getID() == staedte.get(i).getID() ) {
				stadtGehoertZurRoute = true;
				break;
			}
		}

		return stadtGehoertZurRoute;

	}

	private void haeuserSetztenInEinerProvinz( Stadt stadt ) {

	}

	private void haeuserSetztenInMehrerenProvinzen( Stadt stadt ) {

	}

	public void haeuserBestaetigen() {

	}

	public void hauserZuruecksetzen() {

	}

	@Override
	public int punkteBerechnen() {
		// TODO Auto-generated method stub
		return 0;
	}
}
