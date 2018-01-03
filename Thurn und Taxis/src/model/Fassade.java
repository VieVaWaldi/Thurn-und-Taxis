package model;

import java.util.ArrayList;

// Temporäre main klasse

/**
 *
 * @author Walter
 *
 */
public class Fassade {

    public static void main(String[]args) {

    	Map map = new Map();

    	Spieler s1 = new Spieler();
    	Spieler s2 = new Spieler();
   
    	Bonusplaettchen bp = new Bonusplaettchen(map); 
    	
    	ArrayList<Stadt> list = new ArrayList<>();
    	
    	list.add(map.getStadt(3));
    	list.add(map.getStadt(4));
    	list.add(map.getStadt(10));
    	list.add(map.getStadt(11));
    	list.add(map.getStadt(12));
    	list.add(map.getStadt(8));
    	
	    System.out.println( "hi" +bp.punkteBerechnen(list.size(), list, s1) );
	    System.out.println( "hi" +bp.punkteBerechnen(list.size(), list, s2) );
	    System.out.println( "hi" +bp.punkteBerechnen(list.size(), list, s1) );
	    
    }
}
