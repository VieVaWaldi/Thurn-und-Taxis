package controller.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * AlleProvinzenBP´s erhaelt man für ein Haus in jeder Provinz.
 *
 * @author Walter
 *
 */
public class AlleProvinzenBP implements Punkte {

	private Map map;

	private int punkte;
	private int AUFRUFANZAHL;
	private ArrayList<Provinz> benoetigteProvinzen;
	private ArrayList<Spieler> spielerHatBP;

	AlleProvinzenBP( Map map ) {
		this.map = map;

		AUFRUFANZAHL = 4;
		benoetigteProvinzen = new ArrayList<Provinz>();
		spielerHatBP = new ArrayList<Spieler>();

		initAlleProvinzenBP();
	}

	private void initAlleProvinzenBP() {

		for( int i=0; i<map.getAnzahlProvinzen(); i++ ) {
			benoetigteProvinzen.add(map.getProvinz(i));
		}
	}

	/**
	 * Diese Methode wird nach haeuserSetzen() aufgerufen.
	 * Nach dieser Methode muss immer punkteBerechnen() aufgerufen werden.
	 * Die unterschiedlichen Provinzen werden durch das Set voneinander getrennt.
	 * Wenn Bedingung nicht erfuellt ist werden 0 Punkte zurueck gegeben.
	 * Wenn Spieler eine Kutsche erhaelt wird geprueft ob es dafuer noch Punkte gibt.
	 *
	 * @param staedteMitHaeusern enthaelt alle Haeuser des Spielers
	 */
	public void pruefeBedingung( ArrayList<Stadt> staedteMitHaeusern, Spieler spieler ) {

		if( spielerHatBP.contains(spieler)) {
			System.out.println("AlleProvinzenBP: Spieler hat BP bereits.");
			punkte = 0;
			return;
		}

		ArrayList<Provinz> provinzenMitHaeusern = new ArrayList<Provinz>();

		for( int i=0; i<staedteMitHaeusern.size(); i++ ) {
			provinzenMitHaeusern.add(staedteMitHaeusern.get(i).getProvinz());
		}

		Set<Provinz> unterschiedlicheProvinzen = new HashSet<Provinz>(provinzenMitHaeusern);

		if( unterschiedlicheProvinzen.size() >= 7 ) {
			punkte = AUFRUFANZAHL + 2;
			AUFRUFANZAHL--;
			spielerHatBP.add(spieler);
		}

	}

	@Override
	public int punkteBerechnen() {
		return punkte;
	}

}
