package model;

/**
 * RoutenBP�s erh�lt man f�r das erreichen einer bestimmten Routenl�nge.
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
