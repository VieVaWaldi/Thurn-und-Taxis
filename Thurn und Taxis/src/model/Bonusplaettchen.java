package model;

/**
 * Die Elternklasse für alle Bonusplaettchen.
 * BP´s sind BonusPunkte die man für bestimmte Aktionen erhält.	
 *
 * @author Lucy
 *
 */
public abstract class Bonusplaettchen implements Punkte {

	protected int punkte;					// Privates können nicht vererbt werden
	protected int aufrufzahl;
	protected String beschreibung;

}
