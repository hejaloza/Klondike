package klondike.models;

import java.util.ArrayList;

public class Game {

	private Deck deck;
	private ArrayList<Straight> straights;
	private ArrayList<SuitStack> suitStacks;
	private Discard discard;

	public Game() {

		deck = new Deck();
		deck.shuffle();
		straights = new ArrayList<Straight>();
		suitStacks = new ArrayList<SuitStack>();

		/* Baraja **********/
		System.out.println("Baraja: [X,X]");
		////////

		discard = new Discard();
		
		System.out.println("Descarte:" +discard.toString());
		
		/////////
		
		for (int i = 0; i < Suit.values().length; i++) {

			SuitStack suit = new SuitStack();
			suitStacks.add(suit);

			System.out.println("Palo " + Suit.values()[i] + ": " + suit.toString());

		}

		
			
		
		
		
		//////////////
		for (int i = 1; i <= 7; i++) {
			Straight s = new Straight();
			s.addCards(i, deck);
			System.out.println("Escalera" + i + ": " + s.toString());
			straights.add(s);

		}
		System.out.println("/////");

		/////////

	}

	public static void main(String[] args) {

		new Game();

	}

}
