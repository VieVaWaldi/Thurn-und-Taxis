package model;

/**
 * Eine der 4 Amtspersonen.
 * Mit dem Wagner braucht man 2 Staedte weniger auf die naechste Kutsche.
 * 
 * @author Walter
 *
 */
public class Wagner extends Amtspersonen{

	Wagner() {
		this.name = "Wagner";
		this.Beschreibung = "Du brauchst 2 Staedte weniger um die n�chste Kutsche zu bekommen.";
		this.apGenutzt = false;
	}
	
	public void apNutzen() {}
	
	public boolean pr�feNutzbarkeit() { return true; }	
	
}
