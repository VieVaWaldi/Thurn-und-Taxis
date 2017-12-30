package model;

/**
 * RoutenBP´s erhält man für das erreichen einer bestimmten Routenlänge.
 *
 * @author Walter
 *
 */
public class RoutenBP extends Bonusplaettchen {

	private int routenLaenge;

	RoutenBP() {

	}

	@Override
	public int punkteBerechnen() {
		return 0;
	}

	public boolean pruefeBedingung() {
		return true;
	}

}
