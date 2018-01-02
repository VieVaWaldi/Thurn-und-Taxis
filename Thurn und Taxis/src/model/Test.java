package model;

public class Test {
	
	public static void main(String[]args) {
		
		Map map = new Map();
		
		Ablage ablage = new Ablage(map);
		Deck deck = new Deck();
		OffeneKarten ok = new OffeneKarten();
		HandKarten hk1 = new HandKarten();
		HandKarten hk2 = new HandKarten();
		
		ablage.printList();
		deck.printList();
		ok.printList();
		hk1.printList();
		hk2.printList();

		System.out.println(ablage.getKarte(0).getStadt().equals(ablage.getKarte(22).getStadt()));
		
	}
}
