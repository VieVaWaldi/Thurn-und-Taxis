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

    	SpielerRoute route = new SpielerRoute(map);
        
    	Haeuser haeuser = new Haeuser();
    	
        // Liste mit Verbindungen
        
        route.routeLegen(map.getStadt(0));
        route.routeLegen(map.getStadt(5));
        route.routeLegen(map.getStadt(11));
        route.routeLegen(map.getStadt(13));
        route.routeLegen(map.getStadt(8));
        route.routeLegen(map.getStadt(16));
        
        route.printRoute();
        
        ArrayList<Stadt> list = route.routeBeenden();
        
        
        
        haeuser.haeuserSetzen(list, map.getStadt(0));
        haeuser.haeuserSetzen(list, map.getStadt(5));
        haeuser.haeuserSetzen(list, map.getStadt(11));
        haeuser.haeuserSetzen(list, map.getStadt(13));
        haeuser.haeuserSetzen(list, map.getStadt(8));
        haeuser.haeuserSetzen(list, map.getStadt(16));
        
        ArrayList<Stadt> list2 = haeuser.haeuserBestaetigen();
        
        for( int i=0; i<list2.size(); i++ ) {
        	System.out.println(list2.get(i));
        }
        
        System.out.println(haeuser.punkteBerechnen());
        
        System.out.println("---------------------");
        
        
        
        route.routeLegen(map.getStadt(0));
        route.routeLegen(map.getStadt(6));
        route.routeLegen(map.getStadt(16));
        route.routeLegen(map.getStadt(13));
        route.routeLegen(map.getStadt(15));
        
        route.printRoute();

        ArrayList<Stadt> list3 = route.routeBeenden();

        haeuser.haeuserSetzen(list3, map.getStadt(0));
        haeuser.haeuserSetzen(list3, map.getStadt(6));
        haeuser.haeuserSetzen(list3, map.getStadt(16));
        haeuser.haeuserSetzen(list3, map.getStadt(13));
        haeuser.haeuserSetzen(list3, map.getStadt(15));
        haeuser.haeuserSetzen(list3, map.getStadt(21));
        
        ArrayList<Stadt> list4 = haeuser.haeuserBestaetigen();
        
        System.out.println("---Häuser in:");
        for( int i=0; i<list4.size(); i++ ) {
        	System.out.println(list4.get(i));
        }
        
        
        System.out.println("punnkte: " + haeuser.punkteBerechnen());
        
        
    }
}
