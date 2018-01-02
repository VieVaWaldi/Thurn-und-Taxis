package model;

//### TESTING ### //

/**
 * Diese Klasse wird benoetigt weil jede Stadt 3 mal als Karte im Spiel vorkommt.
 * Alle Karten die sich eine Stadt teilen zeigen intern auf die gleiche StadtInstanz.
 *
 * @author Walter
 *
 */
public class Karte {

	private Stadt stadt;
	private int id;

	Karte( Stadt stadt, int id ) {
		this.stadt = stadt;
		this.id = id;
	}

	public Stadt getStadt() {
		return this.stadt;
	}

}
