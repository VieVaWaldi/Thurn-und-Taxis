package model;

import java.util.List;

/**
 * StadtBP�s erh�lt man f�r das halten bestimmer Staedte.
 *
 * @author Walter
 *
 */
public class StadtBP extends Bonusplaettchen {

	private List<Stadt> benoetigteStaedte;

	StadtBP() {

	}

	@Override
	public int punkteBerechnen() {
		return 0;
	}

	public boolean pruefeBedingung() {
		return true;
	}

}
