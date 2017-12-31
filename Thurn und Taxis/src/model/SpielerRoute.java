package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Bruacht zum initialisieren die Map um zu pruefen ob Routen realisierbar sind.
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
	 * Zuerst wird geprueft ob eine neue Route gelegt werden muss 
	 * oder eine alte weitergefuehrt wird. Wenn sie weitergefuehrt wird
	 * prueft Map ob das moeglich ist. Dabei wird geprueft ob die HandKarte mit
	 * dem Start oder End Knoten der Route verbunden werden kann.
	 * 
	 * @param stadt wird von den Handkarten uebergeben
	 * @return Stadt gibt die Stadt zurueck an die verbunden wurde
	 */
	public Stadt routeLegen( Stadt stadt ) {
		
		if(routeIstLeer()) {
			return routeNeuStarten(stadt);
		}
		else {
			return routeWeiterFuehren(stadt);
		}
	}
	
	private Stadt routeNeuStarten( Stadt stadt ) {
		
		anfang = stadt;
		ende = stadt;
		
		staedte.add(stadt);
		
		return stadt;
	}
	
	/**
	 * Prueft ob Route moeglich ist und gibt neue Verbindung zurueck.
	 * @param stadt von den Handkarten
	 * @return Stadt mit der verbunden wurde
	 */
	private Stadt routeWeiterFuehren( Stadt stadt ) {
		
		int id = stadt.getID();
		
		if( staedte.contains(stadt)) {
			System.out.println("SpielerRoute: Stadt ist bereits in der Route.");
			return null;
		}
		
		if( map.istVerbunden( anfang.getID(), id ) ) {
			staedte.add(stadt);
			
			Stadt verbindung = anfang;
			anfang = stadt;
			
			return verbindung;
		}
		
		if( map.istVerbunden( ende.getID(), id ) ) {
			staedte.add(stadt);

			Stadt verbindung = ende;
			ende = stadt;
			return verbindung;
		}
		
		System.out.println("SpielerRoute: Diese Route kann nicht gelegt werden");
		return null;
	}

	public void routeVerwerfen() {
		anfang = null;
		ende = null;
		while(!staedte.isEmpty()) {
			staedte.remove(staedte.size()-1);
		}
	}
	
	
	
	
	// ### List kann noch nicht zurück gegeben werden ######################## //
	
	/**
	 * Gibt eine Liste der in der Route enthaltenen Staedte zurueck.
	 */
	public <Stadt> ArrayList<Stadt> routeBeenden() {
		
		ArrayList<Stadt> list = new ArrayList<Stadt>();

		for( int i=0; i<staedte.size(); i++ ) {
			list.add((Stadt) staedte.get(i));
			System.out.println("---durchlauf---");
		}
		
		routeVerwerfen();
		
		return list;		
	}
	
	public boolean routeIstLeer() {
		return staedte.size() == 0;
	}
	
	public void printRoute() {
		System.out.println("###SpielerRoute###");
		for( int i=0; i<staedte.size(); i++ ) {
			System.out.println(staedte.get(i).getName());
		}
	}
}