package model;

import java.util.List;

/**
 * ProvinzBP´s erhält man für Hauser in bestimmten Provinzen.
 *
 * @author Walter
 *
 */
public class ProvinzBP extends Bonusplaettchen {

	private List<Stadt> benoetigteProvinzen;

	ProvinzBP() {

	}

	@Override
	public int punkteBerechnen() {
		return 0;
	}

	public boolean pruefeBedingung() {
		return true;
	}

}
