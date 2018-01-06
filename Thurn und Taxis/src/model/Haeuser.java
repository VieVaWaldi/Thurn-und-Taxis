package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private ArrayList<Stadt> gesetzteHaeuser;
    private ArrayList<Stadt> zuSetzendeHaeuser;

    private int anzahlVerbleibenderHaeuser;
    private int maxAnzahlMöglicherHaeuser;
    private int punkte;

    private boolean baueInEinerProvinz;
    private boolean zweitesHausWurdeNichtGesetzt;

    Haeuser() {
        gesetzteHaeuser = new ArrayList<Stadt>();
        zuSetzendeHaeuser = new ArrayList<Stadt>();
        anzahlVerbleibenderHaeuser = 20;
        maxAnzahlMöglicherHaeuser = 0;
        punkte = 0;
        zweitesHausWurdeNichtGesetzt = true;
    }

    /**
     * Diese Funktion wird solange aufgerufen bis alle Haeuser gesetzt sind oder der Spieler keine mehr hat.
     * Wenn das der Fall ist wird true zurueck gegeben.
     * <p>
     * Dannach wird mit haeuserBestaetigen() der Vorgang abgeschloßen
     * oder mit haeuserZuruecksetzen() der Vorgang wiederholt.
     * <p>
     * Wenn der Spieler keine Haueser mehr hat muss die finale Runde eingeleitet werden.
     * Dafuer muss im Vorraus immer mit letzteRundeErreicht()
     * abgefragt werden ob der Spieler noch Haeuser hat.
     *
     * @param spielerRoute kommt als ArrayList von SpielerRoute
     * @param stadt ist die vom Spieler ausgewaehlte Stadt
     * @return boolean gibt solange false zurueck bis keine Hauser mehr gesetzt werden koennen
     */
    public boolean haeuserSetzen( ArrayList<Stadt> spielerRoute, Stadt stadt ) {

    	if( anzahlVerbleibenderHaeuser == 0 ) {
            System.out.println("Haeuser: Keine Hauser mehr vorhanden.");
            return true;
        }

        if( stadtIstNichtTeilderRoute( spielerRoute, stadt ) ) {
            System.out.println("Haeuser: Stadt ist nicht Teil der Route: " + stadt);
            return false;
        }

        if( zuSetzendeHaeuser.contains(stadt)) {
            System.out.println("Haeuser: Stadt wurde bereits ausgewaehlt.");
            return false;
        }

        if( gesetzteHaeuser.contains(stadt)) {
            System.out.println("Haeuser: Hier steht bereits ein Haus");
            return false;
        }

        if( zuSetzendeHaeuser.isEmpty() ) {
        	return erstesHausSetzen(stadt);
        }

        return alleWeiterenHaeuserSetzen( spielerRoute, stadt );
        
    }

    private boolean erstesHausSetzen( Stadt stadt ) {

         zuSetzendeHaeuser.add(stadt);
         anzahlVerbleibenderHaeuser--;
         return false;

    }

    /**
     * Beim setzen des 2. Hauses wird festgelegt ob die Haeuser nur innerhalb
     * einer Provinz oder nur in unterschiedlichen Provinzen gesetzt werden duerfen.
     * <p>
     * Zuerst wird nur einmal abgefragt ob das 2. Haus zur gleichen oder einer anderen
     * Provinz gehoert.
     *
     * @param stadt
     * @return
     */
    private boolean alleWeiterenHaeuserSetzen( ArrayList<Stadt> spielerRoute, Stadt stadt ) {
    	
    	if( zweitesHausWurdeNichtGesetzt ) {

	    	if( stadt.getProvinz() == zuSetzendeHaeuser.get(0).getProvinz() ) {
	    		baueInEinerProvinz = true;
	    	}
	    	else {
	    		baueInEinerProvinz = false;
	    	}

	    	zweitesHausWurdeNichtGesetzt = false;
    	}

    	if( baueInEinerProvinz ) {
    		return haeuserSetztenInEinerProvinz(spielerRoute, stadt);
    	}
    	else {
    		return haeuserSetztenInMehrerenProvinzen(spielerRoute, stadt);
    	}
    }

    /**
     * Beim ersten Aufruf wird berechnet wieviele Haeuser maximal gesetzt werden koennen.
     * Dann wird solange false zurueck gegeben bis alle Haeuser gesetzt worden sind.
     */
    private boolean haeuserSetztenInEinerProvinz( ArrayList<Stadt> spielerRoute, Stadt stadt ) {
    	
    	if( !stadtIstTeilDerProvinz(stadt)) {
    		return false;
    	}

    	if( maxAnzahlMöglicherHaeuser == 0 ) {

    		Provinz provinz = zuSetzendeHaeuser.get(0).getProvinz();

            for( int i=0; i<spielerRoute.size(); i++ ) {
                if( spielerRoute.get(i).getProvinz() == provinz ) {
                    maxAnzahlMöglicherHaeuser++;
                }
            }

            System.out.println("maxAnzahlMoeglicherHaeuse = " + maxAnzahlMöglicherHaeuser );
        }

        zuSetzendeHaeuser.add(stadt);
        anzahlVerbleibenderHaeuser--;
        
        if( zuSetzendeHaeuser.size() == maxAnzahlMöglicherHaeuser ) {
        	return true;
        }

        return false;
    }

    /**
     * Beim ersten Aufruf wird berechnet wieviele Haeuser maximal gesetzt werden koennen.
     * Dann wird solange false zurueck gegeben bis alle Haeuser gesetzt worden sind.
     * <p>
     * Die Anzahl unterschiedlicher Provinzen wird berechnet in dem die Provinzen der
     * spielerRouten-Liste auf ein Set uebertragen wird, dass keine Duplikate zulaesst.
     */
    private boolean haeuserSetztenInMehrerenProvinzen( ArrayList<Stadt> spielerRoute, Stadt stadt ) {

    	if( ! stadtIstNichtTeilDerProvinz(stadt) ) {
    		return false;
    	}

        if( maxAnzahlMöglicherHaeuser == 0 ) {

        	Set<Provinz> unterschiedlicheProvinzen = new HashSet<>();

        	for( int i=0; i<spielerRoute.size(); i++ ) {
        		unterschiedlicheProvinzen.add(spielerRoute.get(i).getProvinz());
        	}

        	maxAnzahlMöglicherHaeuser = unterschiedlicheProvinzen.size();

            System.out.println("maxAnzahlMoeglicherHaeuse = " + maxAnzahlMöglicherHaeuser );
        }

        zuSetzendeHaeuser.add(stadt);
        anzahlVerbleibenderHaeuser--;

        if( zuSetzendeHaeuser.size() == maxAnzahlMöglicherHaeuser ) {
        	return true;
        }

        return false;
    }

    private boolean stadtIstTeilDerProvinz( Stadt stadt ) {

        if( zuSetzendeHaeuser.get(0).getProvinz() == stadt.getProvinz()) {
            return true;
        }

        System.out.println("Haeuser: Stadt ist faelschlicherweise nicht Teil der Provinz");
        return false;
    }

    private boolean stadtIstNichtTeilDerProvinz( Stadt stadt ) {

        for( int i=0; i<zuSetzendeHaeuser.size(); i++ ) {
            if( zuSetzendeHaeuser.get(i).getProvinz() == stadt.getProvinz()) {
            	System.out.println("Haeuser: Stadt ist faelschlicherweise Teil der Provinz");
            	return false;
            }
        }
        return true;
    }

    private boolean stadtIstNichtTeilderRoute( ArrayList<Stadt> spielerRoute, Stadt stadt ) {

        boolean stadtGehoertNichtZurRoute = true;

        if(spielerRoute.contains(stadt)) {
            stadtGehoertNichtZurRoute = false;
        }

        return stadtGehoertNichtZurRoute;
    }

    /**
     * Nach dieser Methode muss punkteBerechnen() aufgerufen werden.
     * Die gesetzten Haeuser werden intern gespeichert.
     * <p>
     * Variablen werden fuer den naechsten Durchgang zurueckgesetzt.
     */
    public void haeuserBestaetigen() {

        punkte = zuSetzendeHaeuser.size();

        while( !zuSetzendeHaeuser.isEmpty() ) {
        	gesetzteHaeuser.add( zuSetzendeHaeuser.remove( zuSetzendeHaeuser.size()-1) );
        }

        zweitesHausWurdeNichtGesetzt = true;
        maxAnzahlMöglicherHaeuser = 0;
    }

    /**
     * Diese Methode ermoeglicht es die Haeuser nocheinmal zu setzen.
     * Intern werden dazu alle Variablen zurueckgesetzt.
     * <p>
     * Variablen werden fuer den naechsten Durchgang zurueckgesetzt.
     */
    public void haeuserZuruecksetzen() {

        int neueHaeuser = zuSetzendeHaeuser.size();
        anzahlVerbleibenderHaeuser += neueHaeuser;

        while( !zuSetzendeHaeuser.isEmpty() ) {
            zuSetzendeHaeuser.remove(zuSetzendeHaeuser.size()-1);
        }

        zweitesHausWurdeNichtGesetzt = true;
        maxAnzahlMöglicherHaeuser = 0;
    }

    /**
     * Muss als letzte in einer SpielRunde aufgerufen werden.
     *
     * @return boolean Spiel wird beendet
     */
    public boolean letzteRundeErreicht() {
    	return anzahlVerbleibenderHaeuser == 0;
    }

    /**
     * Methode darf erst nach haeuserBestaetigen() aufgerufen werden.
     */
    @Override
    public int punkteBerechnen() {
        return punkte;
    }
}
