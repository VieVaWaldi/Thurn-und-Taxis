package model;

/**
 * Liefert Informationen darueber ob Staedte verbunfen werden koennen.
 * Speichert außerdem die Staedte- & Probinz-Instanzen.
 *
 * @author Walter
 *
 */
public class Map {

	private final int ANZAHL_PROVINZEN = 9;
	private final int ANZAHL_STAEDTE = 22;
	
	private boolean[][] routenMatrix;			// Muss boolean sein weil rückgabewert
	private Provinz[] provinzen;
	private Stadt[] staedte;

	/**
	 * Initialisiert die Provinzen & Staedte. Provinzen muessen zuerst initialisiert werden
	 * weil sie in Staedten verwendet werden. Dananch wird die Matrix inititialisiert um Verbindungen anzuzeigen.
	 */
	Map() {
		provinzen = new Provinz[ANZAHL_PROVINZEN];
		initProvinzen();
		
		staedte = new Stadt[ANZAHL_STAEDTE];
		initStaedte();
		
		routenMatrix = new boolean[ANZAHL_STAEDTE][ANZAHL_STAEDTE];
		initMatrix();
	}

	/**
	 * Sieht in der Matrix nach ob 2 Staedte verbunden sind.
	 * 
	 * @param idStadt1
	 * @param idStadt2
	 * @return boolean istVerbunden
	 */
	public boolean istVerbunden( int idStadt1, int idStadt2 ) {
		return routenMatrix[idStadt1][idStadt2];
	}

	private void initProvinzen() {

		int iter=0;

		for( ProvinzEnum provinz : ProvinzEnum.values() ) {
			Provinz neueProv = new Provinz(provinz.toString());
			provinzen[iter++] = neueProv;
		}
	}

	/**
	 * Staedte werden der Provinz nach im Array abgespeichert.
	 * Erst aufrufen nach dem Provinzen inititalisiert wurden.
	 * Id wird hier erzeugt und zugeteilt.
	 */
	private void initStaedte() {

		int id = 0;
		
		for( StaedteEnum stadtEnum : StaedteEnum.values() ) {
			Provinz prov = sucheNachProvinz(stadtEnum.getProvinzEnum());
			Stadt neueStadt = new Stadt(stadtEnum.getNameEnum(), prov, id++ ); 
		}

	}
	
	/**
	 * Helferfunktion. Sucht in provinzen[] nach der richtigen Provinz um sie den Staedten hinzuzufuegen.
	 * 
	 * @param prov
	 * @return Provinz
	 */
	private Provinz sucheNachProvinz( String prov ) {
		
		for(int i=0; i<ANZAHL_PROVINZEN; i++ ) {
			if( prov == provinzen[i].getName() ) {
				return provinzen[i];
			}
		}
		System.out.println("Map: Provinz konnte nicht in Stadt eingefuegt werden.");
		return null;
	}

	/**
	 * Die RoutenMatrix wird mit Hilfe der vordefnierten Enums gefüllt.
	 * Die Matrix wird mit false auf jeder Stelle intitialisiert.
	 * Wenn eine Verbindung besteht wird true in die Matrix geschrieben.
	 */
	private void initMatrix() {
		
		for( int i=0; i<ANZAHL_STAEDTE; i++ ) {
			for( int j=0; j<ANZAHL_STAEDTE; j++) {
				routenMatrix[i][j] = false;
			}
		}
		
		int matrixReihePos = 0;
		
		for( VerAugsburg ver : VerAugsburg.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerBasel ver : VerBasel.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerBudweis ver : VerBudweis.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerCarlsruhe ver : VerCarlsruhe.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerFreiburg ver : VerFreiburg.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerIngolstadt ver : VerIngolstadt.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerInnsbruck ver : VerInnsbruck.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerKempten ver : VerKempten.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerLinz ver : VerLinz.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerLodz ver : VerLodz.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerMannheim ver : VerMannheim.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerMuenchen ver : VerMuenchen.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerNuernberg ver : VerNuernberg.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerPassau ver : VerPassau.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerPilsen ver : VerPilsen.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerRegensburg ver : VerRegensburg.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerSalzburg ver : VerSalzburg.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerSigmaringen ver : VerSigmaringen.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerStuttgart ver : VerStuttgart.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerUlm ver : VerUlm.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerWuerzburg ver : VerWuerzburg.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
		for( VerZuerich ver : VerZuerich.values() ) {
			routenMatrix[matrixReihePos][ver.getVerbindung()] = true;
		}
		matrixReihePos++;
		
	}
	
	private enum StaedteEnum{
		Augsburg("Augsburg","Baiern"), 
		Basel("Basel","Schweiz"), 
		Budweis("Budweis","Boehmen"), 
		Carlsruhe("Carlsruhe","Baden"), 
		Freiburg("Freiburg","Baden"), 
		Ingolstadt("Ingolstadt","Baiern"),
		Innsbruck("Innsbruck","Tyrol"), 
		Kempten("Kempten","Baiern"), 
		Linz("Linz","Salzburg"), 
		Lodz("Lodz","Polen"), 
		Mannheim("Mannheim","Baden"), 
		Muenchen("Muenchen","Baiern"), 
		Nuernberg("Nuernberg","Baiern"),
		Passau("Passu","Baiern"), 
		Pilsen("Pilsen","Boehmen"), 
		Regensburg("Regensburg","Baiern"), 
		Salzburg("Salzburg","Salzburg"), 
		Sigamaringen("Sigmaringen","Hohenzollern"), 
		Stuttgart("Stuttgart","Würtemberg"),
		Ulm("Ulm","Würtemberg"), 
		Wuerzburg("Wuerzburg","Baiern"), 
		Zuerich("Zuerich","Schweiz");
		
		private String stadt;
		private String prov;

		StaedteEnum( String stadt, String prov ) {
			this.stadt = stadt;
			this.prov = prov;
		}
		
		public String getNameEnum() {
			return this.stadt;
		}
		
		public String getProvinzEnum() {
			return this.prov;
		}

	}

	private enum ProvinzEnum {
		Baden("Baden"),
		Baiern("Baiern"),
		Boehmen("Boehmen"),
		Hohenzollern("Hohenzollern"),
		Polen("Polen"),
		Salzburg("Salzburg"),
		Schweiz("Schweiz"),
		Tyrol("Tyrol"),
		Würtemberg("Würtemberg");

		private String name;

		ProvinzEnum( String name ) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}
	}
	
	/**
	 * Die folgenden Enums haben alle vordefinierte Id`s
	 * die für die Staedte stehen mit denen sie verbunden sind.
	 * 
	 * ver sthet für Verbindung.
	 * 	
	 * @author Walter
	 *
	 */
	private enum VerAugsburg {
		ver1(5), ver2(6), ver3(7), ver4(11), ver5(19);
		
		private int id;
		
		VerAugsburg( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerBasel {
		ver1(4), ver2(21);
		
		private int id;
		
		VerBasel( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerBudweis {
		ver1(8), ver2(14);
		
		private int id;
		
		VerBudweis( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerCarlsruhe{
		ver1(4), ver2(10), ver3(18);
		
		private int id;
		
		VerCarlsruhe( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerFreiburg {
		ver1(1), ver2(3), ver3(17), ver4(21);
		
		private int id;
		
		VerFreiburg( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerIngolstadt {
		ver1(0), ver2(10), ver3(11), ver4(15), ver5(18), ver6(19);
		
		private int id;
		
		VerIngolstadt( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerInnsbruck {
		ver1(0), ver2(7), ver3(11), ver4(16);
		
		private int id;
		
		VerInnsbruck( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerKempten {
		ver1(0), ver2(1), ver3(6), ver4(17), ver5(19);
		
		private int id;
		
		VerKempten( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerLinz {
		ver1(2), ver2(13), ver3(16);
		
		private int id;
		
		VerLinz( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerLodz {
		ver1(14);
		
		private int id;
		
		VerLodz( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerMannheim {
		ver1(3), ver2(18), ver3(20);
		
		private int id;
		
		VerMannheim( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerMuenchen {
		ver1(0), ver2(5), ver3(6), ver4(13), ver5(15), ver6(16);
		
		private int id;
		
		VerMuenchen( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerNuernberg {
		ver1(5), ver2(14), ver3(15), ver4(18), ver5(20);
		
		private int id;
		
		VerNuernberg( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerPassau {
		ver1(8), ver2(11), ver3(15), ver4(16);
		
		private int id;
		
		VerPassau( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerPilsen {
		ver1(2), ver2(9), ver3(12), ver4(15);
		
		private int id;
		
		VerPilsen( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerRegensburg {
		ver1(5), ver2(11), ver3(12), ver4(13), ver5(14);
		
		private int id;
		
		VerRegensburg( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerSalzburg {
		ver1(6), ver2(8), ver3(11), ver4(13);
		
		private int id;
		
		VerSalzburg( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerSigmaringen {
		ver1(4), ver2(7), ver3(18), ver4(19), ver5(21);
		
		private int id;
		
		VerSigmaringen( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerStuttgart {
		ver1(3), ver2(5), ver3(10), ver4(12), ver5(17), ver6(19), ver7(20);
		
		private int id;
		
		VerStuttgart( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerUlm {
		ver1(0), ver2(5), ver3(7), ver4(17), ver5(18);
		
		private int id;
		
		VerUlm( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerWuerzburg {
		ver1(10), ver2(12), ver3(18);
		
		private int id;
		
		VerWuerzburg( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
	
	private enum VerZuerich {
		ver1(1), ver2(4), ver3(17);
		
		private int id;
		
		VerZuerich( int id ) {
			this.id = id;
		}
		
		public int getVerbindung() {
			return id;
		}
	}
}