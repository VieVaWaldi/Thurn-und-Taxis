package model;

import java.util.ArrayList;

/**
 * Kuemmert sich um die Logik fuer das setzen/bestaetigen/zuruecksetzen
 * von Haeusern. Speichert zusaetzlich die verbliebene Anzahl der Häuser.
 * <p>
 * Gibt durch das Interface Punkte die erworbenen Punkte zurueck.
 *
 * @author Walter
 *
 */
public class Haeuser implements Punkte {

	ArrayList<Stadt> gesetzteHaeuser;
	ArrayList<Stadt> zuSetzendeHaeuser;

	private int anzahlVerbleibenderHaeuser;
	private int maxAnzahlMöglicherHaeuser;
	private int punkte;

	Haeuser() {
		gesetzteHaeuser = new ArrayList<Stadt>();
		zuSetzendeHaeuser = new ArrayList<Stadt>();
		anzahlVerbleibenderHaeuser = 20;
		maxAnzahlMöglicherHaeuser = 0;
		punkte = 0;
	}

	/**
	 * Sobald keine Haeuser mehr gesetzt werden koennen darf der Spieler entscheiden,
	 * ob er seine Haeuser nochmal setzten moechte oder fertig ist.
	 * Wenn der Spieler keine Haueser mehr hat muss die final Runde eingeleitet werden.
	 *
	 * @param staedte kommt als ArrayList von SpielerRoute
	 * @param stadt ist die vom Spieler ausgewaehlte Stadt
	 * @return boolean gibt solange false zurueck bis keine Hauser mehr gesetzt werden koennen
	 */
	public boolean haeuserSetzen( ArrayList<Stadt> staedte, Stadt stadt ) {

		if( anzahlVerbleibenderHaeuser == 0 ) {
			System.out.println("Haeuser: Keine Hauser mehr vorhanden.");
			return true;
		}
		
		if( stadtIstNichtTeilderRoute(staedte, stadt) ) {
			System.out.println("Haeuser: Stadt ist nicht Teil der Route: " + stadt);
			return false;
		}

		if( zuSetzendeHaeuser.contains(stadt)) {
			System.out.println("Haeuser: Stadt wurde bereits ausgewaehlt.");
			return false;
		}
		
		if( gesetzteHaeuser.contains(stadt)) {
			System.out.println("Haeuser: Hier stehtt bereits ein Haus");
		}

		if( zuSetzendeHaeuser.isEmpty() ) {
			zuSetzendeHaeuser.add(stadt);
			anzahlVerbleibenderHaeuser--;
			return false;
		}

		if( stadtIstTeilDerProvinz(stadt) ) {
			return haeuserSetztenInEinerProvinz( staedte, stadt );
		}

		if( stadtIstNichtTeilDerProvinz(stadt) ) {
			return haeuserSetztenInMehrerenProvinzen( staedte, stadt );
		}

		System.out.println("Haueser: Stadt kann nicht gesetzt werden: " + stadt);
		return false;
	}

	private boolean stadtIstNichtTeilderRoute( ArrayList<Stadt> staedte, Stadt stadt ) {

		boolean stadtGehoertNichtZurRoute = true;

		if(staedte.contains(stadt)) {
			stadtGehoertNichtZurRoute = false;
		}
	
		return stadtGehoertNichtZurRoute;
	}

	/**
	 * Es muss erst berechnet werden wieviele Hauser maximal gesetzt werden koennen.
	 * Dann gibt diese Methode solange false zurueck bis die Zahl erreicht ist.
	 */
	private boolean haeuserSetztenInEinerProvinz( ArrayList<Stadt> staedte, Stadt stadt ) {

		if( maxAnzahlMöglicherHaeuser == 0 ) {
			Provinz provinz = zuSetzendeHaeuser.get(0).getProvinz();

			for( int i=0; i<staedte.size(); i++ ) {
				if( staedte.get(i).getProvinz() == provinz ) {
					maxAnzahlMöglicherHaeuser++;
				}
			}
		}

		if( zuSetzendeHaeuser.size() < maxAnzahlMöglicherHaeuser ) {
			zuSetzendeHaeuser.add(stadt);
			anzahlVerbleibenderHaeuser--;
			return false;
		}

		return true;
	}

	/**
	 * Es muss erst berechnet werden wieviele Hauser maximal gesetzt werden koennen.
	 * Dann gibt diese Methode solange false zurueck bis die Zahl erreicht ist.
	 */
	private boolean haeuserSetztenInMehrerenProvinzen( ArrayList<Stadt> staedte, Stadt stadt ) {

		if( maxAnzahlMöglicherHaeuser == 0 ) {
			Provinz provinz = zuSetzendeHaeuser.get(0).getProvinz();

			for( int i=0; i<staedte.size(); i++ ) {
				if( staedte.get(i).getProvinz() != provinz ) {
					maxAnzahlMöglicherHaeuser++;
				}
			}
			maxAnzahlMöglicherHaeuser++;
		}

		if( zuSetzendeHaeuser.size() < maxAnzahlMöglicherHaeuser ) {
			zuSetzendeHaeuser.add(stadt);
			anzahlVerbleibenderHaeuser--;
			return false;
		}

		return true;
	}

	private boolean stadtIstTeilDerProvinz( Stadt stadt ) {

		for( int i=0; i<zuSetzendeHaeuser.size(); i++ ) {
			if( zuSetzendeHaeuser.get(i).getProvinz() != stadt.getProvinz()) {
				return false;
			}
		}
		return true;
	}

	private boolean stadtIstNichtTeilDerProvinz( Stadt stadt ) {

		for( int i=0; i<zuSetzendeHaeuser.size(); i++ ) {
			if( zuSetzendeHaeuser.get(i).getProvinz() == stadt.getProvinz()) {
				return false;
			}
		}
		return true;
	}
	
	
	// #### Besuchte Häuser müssen hier drinnen sein

	/**
	 * Die gesetzten Haeuser werden gespeichert
	 * 
	 * @return ArrayList<Stadt> mit den gesetzten Haeusern
	 */
	public <Stadt> ArrayList<Stadt> haeuserBestaetigen() {

		punkte = zuSetzendeHaeuser.size();
		
		ArrayList<Stadt> list = new ArrayList<Stadt>();

		while( !zuSetzendeHaeuser.isEmpty() ) {
			list.add((Stadt) zuSetzendeHaeuser.remove(zuSetzendeHaeuser.size()-1));
		}
		
		maxAnzahlMöglicherHaeuser = 0;
		
		return list;
	}

	/**
	 * Diese Methode ermoeglicht es die Haeuser nocheinmal zu setzen.
	 * Intern werden dazu alle Variablen zurueckgesetzt.
	 */
	public void haeuserZuruecksetzen() {
		
		int gesetzteHaeuser = zuSetzendeHaeuser.size();
		anzahlVerbleibenderHaeuser += gesetzteHaeuser;
		
		while( !zuSetzendeHaeuser.isEmpty() ) {
			zuSetzendeHaeuser.remove(zuSetzendeHaeuser.size()-1);
		}
		
		maxAnzahlMöglicherHaeuser = 0;
	}

	/**
	 * Methode darf erst nach haeuserBestaetigen() aufgerufen werden.
	 */
	@Override
	public int punkteBerechnen() {
		return punkte;
	}
}
