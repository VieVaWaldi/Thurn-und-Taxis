package model;

import java.util.ArrayList;
import java.util.Scanner;

// Temporäre main klasse



//#### HANDKARTEN zuruecksetzen

/**
 *
 * @author Walter
 *
 */
public class Fassade {

    public static void main( String[] args ) {

    	try {
			Map map = new Map();

			Bonusplaettchen bp = new Bonusplaettchen(map);

			Deck deck = new Deck();
			OffeneKarten ok = new OffeneKarten();
			Ablage ablage = new Ablage(map);
			
			Spieler s1 = new Spieler(map, bp);
			Spieler s2 = new Spieler(map, bp);
			
			//##########################################//
			
			
					
			ablage.kartenVerteilen(deck);
			
			while( !ok.istVoll() ) {
				ok.karteNachlegen( deck.karteZiehen() );
			}
			
			
			
			for( int j=0; j<6; j++ ) {
				
				System.out.println("\n//#################################//" );
				System.out.println("//         Runde: " + j + "        //\n" );
				
				for( int i=0; i<57; i++ ) {
					
					if(deck.istLeer()) {
						ablage.kartenVerteilen(deck);
					}
					s1.karteZiehen( ok.karteZiehen(2, deck.karteZiehen()) );
				}
	   
				s1.printHandKarten();
				
				System.out.println(" -> Stadt auf Route setzen: ");
				Scanner in = new Scanner(System.in);
				int idx = in.nextInt();
				
				Karte karte = null;
				
				while(true) {
					if( s1.routeKannGelegtWerden(idx) ) {
						karte = s1.routeLegen( idx );
						break;
					}
					System.out.println(" -> Stadt NOCHMAL auf Route setzen: ");
					idx = in.nextInt();
				}
				
				ablage.karteAblegen(karte);
				
				System.out.println(" ---> gelegt wurde: " + karte.getStadt() );
				
				if(s1.routeBeenden()) {
					
					boolean allSet = false;
					
					while(!allSet) {
						System.out.println(" -> haussetzen: ");
						int b = in.nextInt();
						allSet = s1.haeuserSetzen(map.getStadt(b));
					}
					
					System.out.println("---Haueser weredenzurueckgesetzt---");
					s1.haeuserZuruecksetzen();
					
					allSet = false;
					
					while(!allSet) {
						System.out.println(" -> haussetzen: ");
						int b = in.nextInt();
						allSet = s1.haeuserSetzen(map.getStadt(b));
					}
					
					s1.haeuserBestaetigen();
					
					s1.bonusPlaettchenSammelm();
					
					s1.kutscheErhalten();
					
				}
				
				while( s1.mehrAlsDreiKarten() ) {
					ablage.karteAblegen(s1.zuVieleKartenAblegen(3));
				}
								
				s1.printHandKarten();
			
			}			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}