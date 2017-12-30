package model;

/**
 * Eine der 4 Amtspersonen.
 * Der Amtmann tauscht die offenen Karten aus.
 * 
 * @author Walter
 *
 */
public class Amtmann extends Amtspersonen{

	Amtmann() {
		this.name = "Amtmann";
		this.Beschreibung = "Die offenen Karten werden ausgetauscht";
		this.apGenutzt = false;
	}
	
	public void apNutzen() {}
	
	public boolean prüfeNutzbarkeit() { return true; }	
	
}