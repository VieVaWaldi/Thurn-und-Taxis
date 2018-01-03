package model;

import java.util.ArrayList;
import java.util.List;

/**
 * ProvinzBP´s erhält man für Hauser in bestimmten Provinzen.
 * Diese Klasse speichert die Spieler die ein BP bereits erhalten haben.
 *
 * @author Walter
 *
 */
public class ProvinzBP implements Punkte {

	private Map map;
	
	private int punkte;
	
	private int AUFRUFANZAHL_BADEN;
	private int AUFRUFANZAHL_SCHWEIZUNDTYROL;
	private int AUFRUFANZAHL_WUERTTEMBERGUNDHOHENZOLLERN;
	private int AUFRUFANZAHL_BOEHMENUNDSALZBURG;
	private int AUFRUFANZAHL_BAIERN;
	
	private ArrayList<Stadt> baden;
	private ArrayList<Spieler> spielerHatBaden;
	
	private ArrayList<Stadt> schweizUndTyrol;
	private ArrayList<Spieler> spielerHatSchweizUndTyrol;
	
	private ArrayList<Stadt> wuerttembergUndHohenzollern;
	private ArrayList<Spieler> spielerHatWuerttembergUndHohenzollen;
	
	private ArrayList<Stadt> boehmenUndSalzburg;
	private ArrayList<Spieler> spielerHatBoehmenUndSalzburg;
	
	private ArrayList<Stadt> baiern;
	private ArrayList<Spieler> spielerHatBaiern;
	
	private List<Stadt> benoetigteProvinzen;

	ProvinzBP( Map map ) {
		
		this.map = map;
		
		punkte = 0;
		
		AUFRUFANZAHL_BADEN = 3;
		AUFRUFANZAHL_SCHWEIZUNDTYROL = 3;
		AUFRUFANZAHL_WUERTTEMBERGUNDHOHENZOLLERN = 3;
		AUFRUFANZAHL_BOEHMENUNDSALZBURG = 3;			// Punkte +1
		AUFRUFANZAHL_BAIERN = 4;						// Punkte +1
		
		baden = new ArrayList<Stadt>();
		schweizUndTyrol = new ArrayList<Stadt>();
		wuerttembergUndHohenzollern = new ArrayList<Stadt>();
		boehmenUndSalzburg = new ArrayList<Stadt>();
		baiern = new ArrayList<Stadt>();
		
		spielerHatBaden = new ArrayList<Spieler>();
		spielerHatSchweizUndTyrol = new ArrayList<Spieler>();
		spielerHatWuerttembergUndHohenzollen = new ArrayList<Spieler>();
		spielerHatBoehmenUndSalzburg = new ArrayList<Spieler>();
		spielerHatBaiern = new ArrayList<Spieler>();
		
		initProvinzBP();		
	}

	/**
	 * Die Informationen zum initialisieren der Arrays kommen von Map.
	 */
	private void initProvinzBP() {
		baden.add(map.getStadt(3));
		baden.add(map.getStadt(4));
		baden.add(map.getStadt(10));
		
		schweizUndTyrol.add(map.getStadt(1));
		schweizUndTyrol.add(map.getStadt(21));
		schweizUndTyrol.add(map.getStadt(6));
		
		wuerttembergUndHohenzollern.add(map.getStadt(18));
		wuerttembergUndHohenzollern.add(map.getStadt(19));
		wuerttembergUndHohenzollern.add(map.getStadt(17));
		
		boehmenUndSalzburg.add(map.getStadt(2));
		boehmenUndSalzburg.add(map.getStadt(14));
		boehmenUndSalzburg.add(map.getStadt(8));
		boehmenUndSalzburg.add(map.getStadt(16));

		baiern.add(map.getStadt(0));
		baiern.add(map.getStadt(5));
		baiern.add(map.getStadt(7));
		baiern.add(map.getStadt(11));
		baiern.add(map.getStadt(12));
		baiern.add(map.getStadt(13));
		baiern.add(map.getStadt(15));
		baiern.add(map.getStadt(20));
	}

	/**
	 * Diese Methode wird nach haeuserSetzen() aufgerufen.
	 * Nach dieser Methode muss immer punkteBerechnen() aufgerufen werden.
	 * <p>
	 * Fuer jede Provinz wird solange der Spieler das BP noch nicht hat oder das BP
	 * noch nicht vollstaendig verteilt wurde, geprueft ob die Haeuser des Spielers
	 * Teil der Provinz ist.
	 * <p>
	 * Wenn Bedingung nicht erfuellt ist werden 0 Punkte zurueck gegeben.
	 * Wenn Spieler eine Kutsche erhaelt wird geprueft ob es dafuer noch Punkte gibt.
	 * 
	 * @param staedteMitHaeusern enthaelt alle Haeuser des Spielers
	 * @param spieler
	 */
	public void pruefeBedingung( ArrayList<Stadt> staedteMitHaeusern, Spieler spieler ) {
		
		if(!spielerHatBaden.contains(spieler) && AUFRUFANZAHL_BADEN != 0 ) {
			pruefeBaden( staedteMitHaeusern, spieler);
		}
		
		if(!spielerHatSchweizUndTyrol.contains(spieler) && AUFRUFANZAHL_SCHWEIZUNDTYROL != 0 ) {
			pruefeSchweizUndTyrol( staedteMitHaeusern, spieler);
		}

		if(!spielerHatWuerttembergUndHohenzollen.contains(spieler) && AUFRUFANZAHL_WUERTTEMBERGUNDHOHENZOLLERN != 0 ) {
			pruefeWuerttembergUndHohenzollen( staedteMitHaeusern, spieler);
		}

		if(!spielerHatBoehmenUndSalzburg.contains(spieler) && AUFRUFANZAHL_BOEHMENUNDSALZBURG != 0 ) {
			pruefeBoehmenUndSalzburg( staedteMitHaeusern, spieler);
		}
		
		if(!spielerHatBaiern.contains(spieler) && AUFRUFANZAHL_BAIERN != 1 ) {
			pruefeBaiern( staedteMitHaeusern, spieler);
		}
		
	}
	
	private void pruefeBaden( ArrayList<Stadt> staedteMitHaeusern, Spieler spieler ) {
		
		int counter = 0;
		
		for( int i=0; i<baden.size(); i++) {
			if( staedteMitHaeusern.contains(baden.get(i)) ) {
				counter++;
			}
		}
		
		if( counter >= 3 ) {
			punkte += AUFRUFANZAHL_BADEN;
			AUFRUFANZAHL_BADEN--;
			spielerHatBaden.add(spieler);
		}		
	}	

	private void pruefeSchweizUndTyrol( ArrayList<Stadt> staedteMitHaeusern, Spieler spieler ) {
		
		int counter = 0;
		
		for( int i=0; i<schweizUndTyrol.size(); i++) {
			if( staedteMitHaeusern.contains(schweizUndTyrol.get(i)) ) {
				counter++;
			}
		}
		
		if( counter >= 3 ) {
			punkte += AUFRUFANZAHL_SCHWEIZUNDTYROL;
			AUFRUFANZAHL_SCHWEIZUNDTYROL--;
			spielerHatSchweizUndTyrol.add(spieler);
		}		
	}
	
	private void pruefeWuerttembergUndHohenzollen( ArrayList<Stadt> staedteMitHaeusern, Spieler spieler ) {
		
		int counter = 0;
		
		for( int i=0; i<wuerttembergUndHohenzollern.size(); i++) {
			if( staedteMitHaeusern.contains(wuerttembergUndHohenzollern.get(i)) ) {
				counter++;
			}
		}
		
		if( counter >= 3 ) {
			punkte += AUFRUFANZAHL_WUERTTEMBERGUNDHOHENZOLLERN;
			AUFRUFANZAHL_WUERTTEMBERGUNDHOHENZOLLERN--;
			spielerHatWuerttembergUndHohenzollen.add(spieler);
		}		
	}

	private void pruefeBoehmenUndSalzburg( ArrayList<Stadt> staedteMitHaeusern, Spieler spieler ) {
		
		int counter = 0;
		
		for( int i=0; i<boehmenUndSalzburg.size(); i++) {
			if( staedteMitHaeusern.contains(boehmenUndSalzburg.get(i)) ) {
				counter++;
			}
		}
		
		if( counter >= 3 ) {
			punkte += AUFRUFANZAHL_BOEHMENUNDSALZBURG;
			AUFRUFANZAHL_BOEHMENUNDSALZBURG--;
			spielerHatBoehmenUndSalzburg.add(spieler);
		}		
	}
	
	private void pruefeBaiern( ArrayList<Stadt> staedteMitHaeusern, Spieler spieler ) {
		
		int counter = 0;
		
		for( int i=0; i<baiern.size(); i++) {
			if( staedteMitHaeusern.contains(baiern.get(i)) ) {
				counter++;
			}
		}
		
		if( counter >= 3 ) {
			punkte += AUFRUFANZAHL_BAIERN;
			AUFRUFANZAHL_BAIERN--;
			spielerHatBaiern.add(spieler);
		}		
	}
	
	@Override
	public int punkteBerechnen() {
		 int tmp = punkte;
		 punkte = 0;
		return tmp;
	}
}
