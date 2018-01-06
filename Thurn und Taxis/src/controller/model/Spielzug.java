package controller.model;

// PHASENN GENAUER BESCHREIBEN

/**
 * Spielzug enthaelt die Logik fuer eine komplette Spielrunde, also alle Optionen
 * die ein Spieler in einer Runde hat.
 * <p>
 * Jede Spielrunde wird in 3 Phasen aufgeteilt. 1. Karten ziehen
 * 2. Route legen & 3. Spielzug abschließen.
 *
 * @author Walter
 *
 */
public class Spielzug {

	private Spieler aktuellerSpieler;

	private Phase phase;

	private boolean apGenutzt;
	private Amtmann amtmann;
	private Postmeister postmeister;
	private Postillion postillion;
	private Wagner wagner;

	private Deck deck;
	private OffeneKarten offeneKarten;
	private Ablage ablage;


	Spielzug( Deck deck, OffeneKarten offeneKarten, Ablage ablage ) {

		apGenutzt = false;
		amtmann = new Amtmann();
		postmeister = new Postmeister();
		postillion = new Postillion();
		wagner = new Wagner();

		this.deck = deck;
		this.offeneKarten = offeneKarten;
		this.ablage = ablage;
	}

	/**
	 * Wird als erstes in einer Runde aufgerufen.
	 *
	 * @param spieler der Spieler der jetzt an der Runde ist
	 */
	public void aktuellenSpielerEntgegennehmen( Spieler spieler ) {
		aktuellerSpieler = spieler;
	}

	//########################## Phase 1 ##########################//

	/**
	 * 1. Phase: Karten nehmen. Der Spieler muss in dieser Phase eine Karte nehmen.
	 * Er hat durch den Amtsmann die Möglichkeit die offenen Karten zu erneuern
	 * und kann mit dem Postmann eine 2. Karte ziehen.
	 */
	private void phase1() {

		this.phase = Phase.Phase1;
	}

	/**
	 * Zu Beginn muss der Spieler seinen Zug bestaetigen.
	 * Erst dann darf die View seine Information anzeigen.
	 *
	 * @param bestaetigt
	 * @return boolean true wenn der Spiezug bestaetigt wurde
	 */
	public boolean SpielzugBestaetigen( boolean bestaetigt ) {

		phase1();

		if( bestaetigt ) {
			amtsmaennerZuruecksetzen();
			return true;
		}

		return false;
	}

	private void amtsmaennerZuruecksetzen() {

		apGenutzt = false;
		amtmann.apZurueckSetzten();
		postmeister.apZurueckSetzten();
		postillion.apZurueckSetzten();
		wagner.apZurueckSetzten();
	}

	public void karteVomDeckZiehen() {

		pruefeObDeckKartenBraucht();
		aktuellerSpieler.karteZiehen(deck.karteZiehen());
	}

	public void karteVonOffenenKartenZiehen( int idx ) {

		pruefeObDeckKartenBraucht();
		aktuellerSpieler.karteZiehen(offeneKarten.karteZiehen(idx, deck.karteZiehen()));

	}

	/**
	 * Muss automatisch genutzt werden wenn Spieler nur 1 Karte hat.
	 *
	 * @return boolean false wenn der Postmeister nicht genutzt werden kann
	 */
	public boolean postmeisterNutzenDeck() {

		if( apGenutzt ) {
			return false;
		}

		apGenutzt = postmeister.apNutzen();
		karteVomDeckZiehen();

		return true;
	}

	/**
	 * Wenn der Spieler nur eine Karte hat wird der Postmann automatisch genutzt.
	 *
	 * @return
	 */
	public boolean hatEineKarte() {
		return aktuellerSpieler.hatEineKarte();
	}


	/**
	 * @param idx ( = Index ) der Handkarten des Spielers
	 * @return boolean false wenn der Postmeister nicht genutzt werden kann
	 */
	public boolean postmeisterNutzenOffeneKarten( int idx ) {

		if( apGenutzt ) {
			return false;
		}

		apGenutzt = postmeister.apNutzen();
		karteVonOffenenKartenZiehen(idx);

		return false;
	}

	/**
	 * Alle offenen Karten werden auf die Ablage gelegt und es werden neue vom
	 * Deck hinzugefuegt.
	 *
	 * @return boolean false wenn der Amtmann nicht genutzt werden kann
	 */
	public boolean amtmannNutzen() {

		if( apGenutzt ) {
			return false;
		}

		apGenutzt = amtmann.apNutzen();

		for( int i=0; i<6; i++ ) {
			pruefeObDeckKartenBraucht();
			ablage.karteAblegen( offeneKarten.karteZiehen( i, deck.karteZiehen() ) );
		}

		return true;
	}

	private void pruefeObDeckKartenBraucht() {

		if(deck.istLeer()) {
			ablage.kartenVerteilen(deck);
		}
	}

	//########################## Phase 2 ##########################//

	/**
	 * Phase 2: Route legen. Der Spieler muss in dieser Phase eine Route beginnen
	 * oder weiterfuehren. Wenn der Spieler keine Route weiterfuehren kann oder will,
	 * wird diese automatisch verworfen. Wenn dies der Fall ist muss er eine neuer Route beginnen.
	 */
	private void phase2() {

		this.phase = Phase.Phase2;
	}

	/**
	 * Ein Spieler kann einmal pro Runde eine Route legen.
	 * Mit dem Postillion darf er das 2. mal.
	 * Eine neue Route wird begonnen oder eine alte weitergefuehrt.
	 *
	 * @param idx ( = Index ) der Handkarten des Spielers
	 * @return boolean true wenn Route erfolgreich gelegt wurde
	 */
	public boolean routeLegen( int idx ) {

		phase2();

		if( aktuellerSpieler.routeKannGelegtWerden(idx) ) {
			aktuellerSpieler.routeLegen(idx);
			return true;
		}

		return false;
	}

	public void routeVerwerfen() {
		aktuellerSpieler.routeVerwerfen();
	}

	/**
	 * @param idx ( = Index ) der Handkarten des Spielers
	 * @return boolean false wenn der Postillion nicht genutzt werden kann
	 */
	public boolean postillionNutzen( int idx ) {

		if( apGenutzt ) {
			return false;
		}

		apGenutzt = postillion.apNutzen();
		routeLegen(idx);

		return true;
	}

	//########################## Phase 3 ##########################//

	/**
	 * In Phase 3 wird die Route entweder pausiert wenn der Spieler fertig ist,
	 * oder beendet wenn er das will. Wenn Sie beendet wird werden Heuser gesetzt,
	 * Bonusplaettchen & Kutschen verteilt und Punkte berechnet.
	 */
	private void phase3() {

		this.phase = Phase.Phase3;
	}

	/**
	 * Wenn diese Methode false zurueck gibt muss nur noch mehrAlsDreiKartenAblegen()
	 * und roouteBeenden() aufgerufen werden.
	 *
	 * @return boolean false wenn die Route nicht beendet werden kann. Rufe routePausiern() auf.
	 */
	public boolean routeBeenden() {

		phase3();
		return aktuellerSpieler.routeBeenden();
	}

	public void routePausieren( int idx ) {
		mehrAlsDreiKartenAblegen(idx);
	}

	/**
	 * Erst aufrufen nach dem Route erfolgreich beendet wurde.
	 * Nach dem setzen darf der Spieler die Auswahl bestaetigen oder zuruecksetzen.
	 *
	 * @return boolean gibt solange false zurueck bis keine Haeuser mehr gesetzt werden koennen
	 */
	public boolean haeuserSetzen( Stadt stadt ) {
		return aktuellerSpieler.haeuserSetzen( stadt );
	}

	/**
	 * Erst aufrufen nachdem Route erfolgreich beendet wurde.
	 * Hierbei werden die Punkte automatisch berechnet.
	 * Wenn Haeuser besteatigt muss wird bonusPlaettchenSammeln() aufgerufen werden.
	 *
	 * @param bestaetigt Haeuser koennen gesetzt werden
	 * @return boolean false wenn Haeuser nochmal gesetzt werden sollen.
	 */
	public void haeuserBestaetigen() {
		aktuellerSpieler.haeuserBestaetigen();
	}

	/**
	 * Wenn diese Methode aufgerufen wurde muss haeuserSetzen() nochmal aufgerufen werden.
	 */
	public void haeuserZuruecksetzen() {
		aktuellerSpieler.haeuserZuruecksetzen();
	}

	/**
	 * Bonusplaettchen, Kutsche und Punnkte werden verteilt.
	 */
	public void rundeAbschließen() {

		aktuellerSpieler.bonusPlaettchenSammelm();

		if(!apGenutzt) {
			apGenutzt = true;
		}

		aktuellerSpieler.kutscheErhalten(apGenutzt);
	}

	private void mehrAlsDreiKartenAblegen( int idx) {

		if(aktuellerSpieler.mehrAlsDreiKarten()){
			aktuellerSpieler.zuVieleKartenAblegen(idx);
		}
	}

	/**
	 * Muss jedes mal am Ende der Runde abgefragt werden.
	 *
	 * @return true wenn letzte Runde erreicht wurde.
	 */
	public boolean spielzugBeenden() {

		return aktuellerSpieler.letzteRundeErreicht();
	}

	//########################### Enum  ###########################//

	public Phase getPhase() {
		return phase;
	}

	private enum Phase {

		Phase1("1. Phase: Du musst in dieser Phase eine Karte nehmen. Du hast durch den Amtsmann die Möglichkeit die offenen Karten zu erneuern. Und mit dem Postmann kannst du eine 2. Karte ziehen."),
		Phase2("2. Phase: Du musst in dieser Phase eine Route beginnen oder weiterfuehren. Wenn das nicht geht oder du nicht willst, wird diese automatisch verworfen. Wenn dies der Fall ist muss du eine neue Route beginnen."),
		Phase3("3. Phase: Du kannst Route entweder pausieren oder beenden. Wenn Sie beendet wird werden Heuser gesetzt, Bonusplaettchen & Kutschen verteilt und Punkte berechnet.");

		private String beschreibung;

		Phase( String beschreibung ) {
			this.beschreibung = beschreibung;
		}

		public String getBeschreibung() {
			return beschreibung;
		}
	}

}
