package model;

/**
 * Staedte werden fuer die Routen und Karten benoetigt.
 * 
 * @author Walter
 *
 */
public class Stadt {

	private String name;
	private int id;
	private Provinz provinz;
	
	Stadt( String name, int id, Provinz provinz) {
		this.name = name;
		this.id = id;
		this.provinz = provinz;
	}
	
}
