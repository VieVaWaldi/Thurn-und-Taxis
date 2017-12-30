package model;

/**
 * Die Elternklasse f�r alle Bonusplaettchen.
 * BP�s sind BonusPunkte die man f�r bestimmte Aktionen erh�lt.	
 *
 * @author Lucy
 *
 */
public abstract class Bonusplaettchen implements Punkte {

	protected int punkte;					// Privates k�nnen nicht vererbt werden
	protected int aufrufzahl;
	protected String beschreibung;

}
