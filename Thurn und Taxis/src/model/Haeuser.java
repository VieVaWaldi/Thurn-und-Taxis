package model;

/**
 * Kuemmert sich um die Logik fuer das setzen/bestaetigen/zuruecksetzen 
 * von Haeusern. Speichert zusaetzlich die verbliebene Anzahl.
 * <p>
 * Gibt durch das Interface Punkte die erworbenen Punkte zurueck.
 * 
 * @author Walter
 *
 */
public class Haeuser implements Punkte {

	private int anzahl;
	
	Haeuser() {
		anzahl = 20;
	}
	
	public void haeuserSetzen() {
		
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
