package model;

/**
 * Staedte werden fuer die Routen und Karten benoetigt.
 * 
 * @author Walter
 *
 */
public class Stadt {

	private String name;
	private Provinz provinz;		// Ist n String weils einfacher ist. Evtl fixe ich das mal
	private int id;
	
	/**
	 * @param name der Stadt
	 * @param provinz der Stadt
	 * @param id der Stadt
	 */
	Stadt( String name, Provinz provinz, int id) {
		this.name = name;
		this.provinz = provinz;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public Provinz getProvinz() {
		return provinz;
	}
	
	public int getID() {
		return id;
	}
	
	public String toString() {
		return name + " in " + provinz + " mit ID: " + id;
	}
	
}
