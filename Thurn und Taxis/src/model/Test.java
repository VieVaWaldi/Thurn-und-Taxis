package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[]args) {
		
		Map map = new Map();
		
		SpielerRoute route = new SpielerRoute(map);
		
		Haeuser h = new Haeuser();
		
		route.routeLegen(map.getStadt(0));
		route.routeLegen(map.getStadt(5));
		route.routeLegen(map.getStadt(11));
		route.routeLegen(map.getStadt(16));
		route.routeLegen(map.getStadt(8));
		route.routeLegen(map.getStadt(2));
		
		route.printRoute();
		
		ArrayList<Stadt> s = route.routeBeenden();
		
		for( int i=0; i<s.size(); i++ ) {
			System.out.println(s.get(i));
		}
		
		Scanner it = new Scanner(System.in);
		
		boolean go = false;
		
		while(!go) {

			System.out.println("-> gib id");
			int id = it.nextInt();
			
			go = h.haeuserSetzen(s, map.getStadt(id));
			
		}
		
		h.haeuserZuruecksetzen();
		System.out.println("wurde zurueckgesetzt");
		
		go = false;
		
		while(!go) {

			System.out.println("-> gib id");
			int id = it.nextInt();
			
			go = h.haeuserSetzen(s, map.getStadt(id));
			
		}
		
		System.out.println("job done");
	}
}
