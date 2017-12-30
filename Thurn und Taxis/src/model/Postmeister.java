package model;

/**
 * Eine der 4 Amtspersonen.
 * Der Postmeister erlaubt es eine zweite Karte zu ziehen.
 * 
 * @author Walter
 *
 */
public class Postmeister extends Amtspersonen{

	Postmeister() {
		this.name = "Postmeister";
		this.Beschreibung = "Ziehe eine zweite Karte.";
		this.apGenutzt = false;
	}
	
	public void apNutzen() {}
	
	public boolean prüfeNutzbarkeit() { return true; }	
	
}