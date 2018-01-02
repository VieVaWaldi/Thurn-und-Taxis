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
        
        
        
        Provinz p = new Provinz("joh");
        Stadt s1 = new Stadt("Augsburg", p, 0);
        Stadt s2 = new Stadt("Innsbruck", p, 6);
        Stadt s3 = new Stadt("Kempten", p, 7);
        Stadt s4 = new Stadt("Ulm", p, 19);
        Stadt s5 = new Stadt("Sigmaringen", p, 17);

        Stadt s6 = new Stadt("heil", p, 2);
        
        // Liste mit Verbindungen
        
        route.routeLegen(s1);
        route.routeLegen(s6);
        route.routeLegen(s2);
        route.routeLegen(s3);
        route.routeLegen(s4);
        route.routeLegen(s5);
        route.routeLegen(s1);
        route.routeLegen(s1);  
        route.routeLegen(s1); 
        route.routeLegen(s3);  
        
        route.printRoute();
        
        ArrayList<Stadt> list = route.routeBeenden();
        
        route.printRoute();
        
        System.out.println("---");
        System.out.println("neue Liste:");
        for( int i=0; i<list.size(); i++ ) {
        	System.out.println(list.get(i).getName());
        }
        
        System.out.println("Job Done!");

    }
}
