package model;

/**
 * Spiel speichert alle alle offenen Instanzen.
 * 
 * @author Walter
 *
 */
public class Spiel {
	
	private Deck deck;
	private Ablage ablage;
	private OffeneKarten offeneKarten;
	
	private Spieler[] spieler;
	
	private RoutenBP[] routenBP;
	private StadtBP[] stadtBP;
	private ProvinzBP[] provinzBP;
	
	Spiel() {
		
	}

}
