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

	private int punkte;
	private int laenge;
	
	// ### Man erh�lt zugang zur n�chst h�heren Kutsche. �berlege besseren namen ###
	public void upgrade() {
		
	}
	
	@Override
	public int punkteBerechnen() {
		// TODO Auto-generated method stub
		return 0;
	}

}
