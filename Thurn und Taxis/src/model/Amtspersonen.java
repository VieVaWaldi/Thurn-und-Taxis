package model;

/**
 * Die ElternKlasse für Amtspersonen. Sie ist abstrakt weil keine Instanz benötigt wird.
 * Amtspersonen geben dem Spieler in einer Runde mehr Möglichkeiten.
 * Man kann nur einen pro Runde einsetzen.
 *
 * @author Walter
 *
 */
public abstract class Amtspersonen {

	protected String name;					// Privates können nicht vererbt werden
	protected String beschreibung;
	protected boolean apGenutzt;

}
