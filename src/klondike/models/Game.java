package klondike.models;

import java.util.ArrayList;

public class Game {

	private Deck deck;
	private ArrayList<Straight> straights;
	private ArrayList<SuitStack> suitStack;
	private Discard discard;

	public Game() {
 
		deck = new Deck();
		deck.shuffle();
		straights = new ArrayList<Straight>();

		discard = new Discard();
		for (int i = 1; i <= 7; i++) {
			Straight s = new Straight();
			s.addCards(i, deck);
			System.out.println(s.toString());
			straights.add(s);

		}
		System.out.println(""+deck.getDeck().size());
	}
	

	public static void main(String[] args) {
		
		new Game();
		
	
		
		
		

	}

}
