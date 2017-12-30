package model;

/**
 * Liefert Informationen darueber ob Staedte verbunfen werden koennen.
 * Speichert außerdem die Staedte- & Probinz-Instanzen.
 * 
 * @author Walter
 *
 */
public class Map {

	private boolean[][] routenMatrix;			// Muss boolean sein weil rückgabewert
	private Stadt[] staedte;
	private Provinz[] provinzen;
	
	public boolean istVerbunden() {
		return true;
	}
}
