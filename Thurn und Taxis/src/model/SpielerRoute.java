package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Braucht zum initialisieren die Map um zu pruefen ob Routen realisierbar sind.
 * Speichert und verwaltet die aktuelle Route des Spielers.
 * Arbeitet intern mit den Staedte-ID´S.
 *
 * @author Walter
 *
 */
public class SpielerRoute {

	private Map map;

	private Stadt anfang;
	private Stadt ende;
	private ArrayList<Stadt> staedte;

	SpielerRoute( Map map ) {
		this.map = map;
		staedte = new ArrayList<Stadt>();
	}

	/**
	 * Vor dieser Methode muss mit routeKanGelegtWerden() geprueft werden ob die Route moeglich ist.
	 * Wenn dies moeglich ist wird eine neue Route begonnen
	 * oder am Anfang / Ende der alten Route die Route erweitert.
	 *
	 * @param stadt wird von den Handkarten des Spielers uebergeben
	 */
	public void routeLegen( Stadt stadt ) {
		
		if( !routeKannGelegtWerden(stadt)) {
			return;
		}

		if(routeIstLeer()) {
			routeNeuStarten(stadt);
		}
		else {
			routeWeiterFuehren(stadt);
		}
	}

	private void routeNeuStarten( Stadt stadt ) {

		anfang = stadt;
		ende = stadt;

		staedte.add(stadt);
	}

	/**
	 * Anfang und Ende der Routen koennen sich beide veraendern, anbhaengig davon wo
	 * der Spieler die Route erweitert.
	 *
	 * @param stadt
	 */
	private void routeWeiterFuehren( Stadt stadt ) {

		int id = stadt.getID();

		if( map.istVerbunden( anfang.getID(), id ) ) {
			staedte.add(stadt);

			Stadt verbindung = anfang;
			anfang = stadt;

			return;
		}

		if( map.istVerbunden( ende.getID(), id ) ) {
			staedte.add(stadt);

			Stadt verbindung = ende;
			ende = stadt;

			return;
		}
	}

	/**
	 * Muss geprueft werden bevor die Route begonnen wird.
	 * Zuerst wird geprueft obdie Route leer ist. Wenn nicht wird geschaut
	 * ob die neue Stadt mit dem Anfang oder Ende verbunden werden kann.
	 *
	 * @param stadt
	 */
	public boolean routeKannGelegtWerden( Stadt stadt ) {

		int id = stadt.getID();

		if( routeIstLeer() ) {
			return true;
		}

		if( staedte.contains(stadt)) {
			System.out.println("SpielerRoute: Stadt ist bereits in der Route.");
			return false;
		}

		if( map.istVerbunden( anfang.getID(), id ) ) {
			return true;
		}

		if( map.istVerbunden( ende.getID(), id ) ) {
			return true;
		}

		System.out.println("SpielerRoute: Route kann nicht gelget werden.");
		return false;
	}

	/**
	 * Route wird verworfen. Es werden keine Punkte vergeben.
	 */
	public void routeVerwerfen() {

		anfang = null;
		ende = null;
		while(!staedte.isEmpty()) {
			staedte.remove(staedte.size()-1);
		}
	}

	/**
	 * Davor muss mit routeKannBeendetWerden() geprueft werden ob das beenden moeglich ist.
	 * Wird beim erfolgreichen beenden der Route aufgerufen. Dabei wird die Route intern geloescht,
	 * sie muss also extern gespeichert werden bis alle Haueser gesetzt sind.
	 *
	 * @return ArrayList mit den Städten der SpielerRoute
	 */
	public <Stadt> ArrayList<Stadt> routeBeenden() {

		if( !routeKannBeendetWerden() ) {
			return null;
		}

		ArrayList<Stadt> list = new ArrayList<Stadt>();

		for( int i=0; i<staedte.size(); i++ ) {		// I dont get why its not size()-1
			list.add((Stadt) staedte.get(i));
		}

		routeVerwerfen();

		return list;
	}
	
	/**
	 * Muss vor routeBeenden() geprueft werden.
	 * 
	 * @return boolean Route kann beendet werden
	 */
	public boolean routeKannBeendetWerden() {
		
		if( staedte.size() < 3 ) {
			System.out.println("SpielerRoute: Route muss zum Beenden min 3 Staedte beinhalten.");
			return false;
		} 
		
		return true;
	}	

	private boolean routeIstLeer() {
		return staedte.size() == 0;
	}

	public void printRoute() {
		System.out.println("###SpielerRoute###");
		for( int i=0; i<staedte.size(); i++ ) {
			System.out.println(staedte.get(i).getName());
		}
		System.out.println("##################");
	}
}