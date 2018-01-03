package model;

import javafx.scene.image.Image;

// ### lass dir ne schöne beschreibung einfallen ### //

/**
 * Repraesentiert den Spieler. 
 * 
 * 
 * @author Walter
 *
 */
public class Spieler {

	private String name;
	private int spielerNr;
	private String farbe;
	private Image icon;
	
	private int punkte;
	private Haeuser haeuser;
	private Kutsche kutsche;
	
	private HandKarten handkarten;
	
	private SpielerRoute route;
	
	private Stadt[] gesetzteHaeuser;
	private Bonusplaettchen[] bonusplaettchen;
}
