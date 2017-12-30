package model;

/**
 * Liefert Informationen darueber ob Staedte verbunfen werden koennen.
 * Speichert au�erdem die Staedte- & Probinz-Instanzen.
 * 
 * @author Walter
 *
 */
public class Map {

	private boolean[][] routenMatrix;			// Muss boolean sein weil r�ckgabewert
	private Stadt[] staedte;
	private Provinz[] provinzen;
	
	public boolean istVerbunden() {
		return true;
	}
}
