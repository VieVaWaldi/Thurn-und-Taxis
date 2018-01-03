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
   
    	ProvinzBP bp = new ProvinzBP(map);
    	
    	ArrayList<Stadt> list = new ArrayList<>();
    	
    	list.add(map.getStadt(3));
    	list.add(map.getStadt(4));
    	list.add(map.getStadt(10));
    	list.add(map.getStadt(11));
    	list.add(map.getStadt(12));
    	list.add(map.getStadt(8));
    	
    	bp.pruefeBedingung(list, s1);
    	System.out.println(bp.punkteBerechnen());
    	
    	bp.pruefeBedingung(list, s2);
    	System.out.println(bp.punkteBerechnen());
    
    	bp.pruefeBedingung(list, s1);
    	System.out.println(bp.punkteBerechnen());
    }
}
