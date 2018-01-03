package model;

/**
 * Die Elternklasse für alle Bonusplaettchen.
 * BP´s sind BonusPunkte die man für bestimmte Aktionen erhält.	
 * Im Gegensatz zu den Kutschen gibt es jedes Bonusplaettchen nur 1 mal fuer alle Spieler.
 *
 * @author Lucy
 *
 */
public abstract class Bonusplaettchen implements Punkte {

	protected int punkte;					
	protected int aufrufZahl;
	protected String beschreibung;

}
