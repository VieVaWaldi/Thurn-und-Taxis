package model;

/**
 * Die ElternKlasse f�r Amtspersonen. Sie ist abstrakt weil keine Instanz ben�tigt wird.
 * Amtspersonen geben dem Spieler in einer Runde mehr M�glichkeiten.
 * Man kann nur einen pro Runde einsetzen.
 *
 * @author Walter
 *
 */
public abstract class Amtspersonen {

	protected String name;					// Privates k�nnen nicht vererbt werden
	protected String beschreibung;
	protected boolean apGenutzt;

}
