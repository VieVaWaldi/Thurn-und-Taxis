package model;

/**
 * Eine der 4 Amtspersonen.
 * Mit dem Postillion kann man seine Route um 2 Staedte statt einer verlängern.
 * 
 * @author Walter
 *
 */
public class Postillion extends Amtspersonen{

	Postillion() {
		this.name = "Postillion";
		this.Beschreibung = "Du kannst deine Route um 2 Staedte erweitern.";
		this.apGenutzt = false;
	}
	
	public void apNutzen() {}
	
	public boolean prüfeNutzbarkeit() { return true; }	
	
}