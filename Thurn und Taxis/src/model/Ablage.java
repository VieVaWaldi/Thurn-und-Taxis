package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Hier werden alle genutzen Karten abgelegt.
 * Zu Beginn und wenn das Deck leer ist werden die an das Deck verteilt.
 * <p>
 * Karten duerfen nur über karteAblegen() in die Liste ablage gelangen.
 * Karten duerfen nur ueber kartenVerteilen() die Liste ablage verlassen.
 *
 * @author Walter
 *
 */
public class Ablage {

	private ArrayList<Stadt> ablage;			// Nehme ich weil shuffle
	
	private final int MAX_ANZAHL_KARTEN = 66;

	/**
	 * Muss von den KartenKlassen als letzte initialisiert werden, da sie die anderen fuellt.
	 */
	Ablage( ) {
		ablage = new ArrayList<Stadt>();
		initAblage();
	}

	private void initAblage() {

		for( KartenEnum karte : KartenEnum.values() ) {
			ablage.add( new Stadt(karte.getNameEnum(), new Provinz(karte.getProvinzEnum()), karte.getIdEnum()) );
		}
	}

	private void mischen() {
		Collections.shuffle(ablage);
	}

	/**
	 * Nur ueber diese Methoden duerfen Karten die Ablage verlassen.
	 * Mischt die Karten. Verteilt die Karten dann an das Deck wenn Deck leer ist
	 * & zu Beginn dies Spiels.
	 */
	public void kartenVerteilen( Deck deck ) {

		mischen();
		
		while(!istLeer()) {
			Stadt karte = ablage.remove(ablage.size()-1);
			deck.karteNachlegen(karte);
		}
	}

	/**
	 * Nur über diese Methode darf ablage Karten erhalten.
	 */
	public void karteAblegen( Stadt karte ) {
		ablage.add(karte);
	}

	private boolean istLeer() {
		return ablage.size() == 0;
	}
	
	public void printList() {
		int counter = 0;
		System.out.println("###Ablage###");
		for( int i=0; i<ablage.size(); i++) {
			System.out.println(ablage.get(i).getName() + " " + counter++ );
		}
		System.out.println("###Ablage Ende###");
	}
	
	/**
	 * Hier sind die Karten für die Initialisierung der Ablage gespeichert.
	 */
	private enum KartenEnum{
		Karte0("Augsburg","Baiern", 0),
		Karte1("Basel","Schweiz", 1),
		Karte2("Budweis","Boehmen", 2),
		Karte3("Carlsruhe","Baden",3),
		Karte4("Freiburg","Baden",4),
		Karte5("Ingolstadt","Baiern",5),
		Karte6("Innsbruck","Tyrol",6),
		Karte7("Kempten","Baiern",7),
		Karte8("Linz","Salzburg",8),
		Karte9("Lodz","Polen",9),
		Karte10("Mannheim","Baden",10),
		Karte11("Muenchen","Baiern",11),
		Karte12("Nuernberg","Baiern",12),
		Karte13("Passau","Baiern",13),
		Karte14("Pilsen","Boehmen",14),
		Karte15("Regensburg","Baiern",15),
		Karte16("Salzburg","Salzburg",16),
		Karte17("Sigmaringen","Hohenzollern",17),
		Karte18("Stuttgart","Würtemberg",18),
		Karte19("Ulm","Würtemberg",19),
		Karte20("Wuerzburg","Baiern",20),
		Karte21("Zuerich","Schweiz",21),

		Karte22("Augsburg","Baiern",0),
		Karte23("Basel","Schweiz",1),
		Karte24("Budweis","Boehmen",2),
		Karte25("Carlsruhe","Baden",3),
		Karte26("Freiburg","Baden",4),
		Karte27("Ingolstadt","Baiern",5),
		Karte28("Innsbruck","Tyrol",6),
		Karte29("Kempten","Baiern",7),
		Karte30("Linz","Salzburg",8),
		Karte31("Lodz","Polen",9),
		Karte32("Mannheim","Baden",10),
		Karte33("Muenchen","Baiern",11),
		Karte34("Nuernberg","Baiern",12),
		Karte35("Passu","Baiern",13),
		Karte36("Pilsen","Boehmen",14),
		Karte37("Regensburg","Baiern",15),
		Karte38("Salzburg","Salzburg",16),
		Karte39("Sigmaringen","Hohenzollern",17),
		Karte40("Stuttgart","Würtemberg",18),
		Karte41("Ulm","Würtemberg",19),
		Karte42("Wuerzburg","Baiern",20),
		Karte43("Zuerich","Schweiz",21),

		Karte44("Augsburg","Baiern",0),
		Karte45("Basel","Schweiz",1),
		Karte46("Budweis","Boehmen",2),
		Karte47("Carlsruhe","Baden",3),
		Karte48("Freiburg","Baden",4),
		Karte49("Ingolstadt","Baiern",5),
		Karte50("Innsbruck","Tyrol",6),
		Karte51("Kempten","Baiern",7),
		Karte52("Linz","Salzburg",8),
		Karte53("Lodz","Polen",9),
		Karte54("Mannheim","Baden",10),
		Karte55("Muenchen","Baiern",11),
		Karte56("Nuernberg","Baiern",12),
		Karte57("Passu","Baiern",13),
		Karte58("Pilsen","Boehmen",14),
		Karte59("Regensburg","Baiern",15),
		Karte60("Salzburg","Salzburg",16),
		Karte61("Sigmaringen","Hohenzollern",17),
		Karte62("Stuttgart","Würtemberg",18),
		Karte63("Ulm","Würtemberg",19),
		Karte64("Wuerzburg","Baiern",20),
		Karte65("Zuerich","Schweiz",21);

		private String stadt;
		private String prov;
		private int id;

		KartenEnum( String stadt, String prov, int id ) {
			this.stadt = stadt;
			this.prov = prov;
			this.id = id;
		}

		public String getNameEnum() {
			return this.stadt;
		}

		public String getProvinzEnum() {
			return this.prov;
		}

		public int getIdEnum() {
			return this.id;
		}
	}
}
