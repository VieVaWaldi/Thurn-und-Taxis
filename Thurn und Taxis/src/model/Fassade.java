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

    	Kutsche kutsche = new Kutsche();
    	
    	kutsche.kutschePruefen(3);
    	System.out.println(kutsche.punkteBerechnen());
    	kutsche.kutschePruefen(4);
    	System.out.println(kutsche.punkteBerechnen());
    	kutsche.kutschePruefen(5);
    	System.out.println(kutsche.punkteBerechnen());
    	kutsche.kutschePruefen(5);
    	System.out.println(kutsche.punkteBerechnen());
    	kutsche.kutschePruefen(3);
    	System.out.println(kutsche.punkteBerechnen());
    	System.out.println(kutsche.kutschePruefen(6));
    	System.out.println(kutsche.punkteBerechnen());
    	System.out.println(kutsche.kutschePruefen(7));
    	System.out.println(kutsche.punkteBerechnen());
    	
    }
}
