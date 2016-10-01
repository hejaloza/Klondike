package klondike.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import klondike.models.Card;
import klondike.models.StackDeck;
import klondike.models.StackDiscard;
import klondike.models.StackStraight;
import klondike.models.StackSuit;
import klondike.models.Suit;

public class GameView {

	private StackDeck deck;
	private ArrayList<StackStraight> straights;
	private ArrayList<StackSuit> suitStacks;
	private StackDiscard discard;


	public GameView(StackDeck deck, ArrayList<StackStraight> straights, ArrayList<StackSuit> suitStacks,
			StackDiscard discard) {

		this.deck = deck;
		this.straights = straights;
		this.suitStacks = suitStacks;
		this.discard = discard;
	}

	public void board() {
		if(deck.getStackCard().size()==0){
			System.out.println("Baraja: []");
		}else{
			System.out.println("Baraja: [X,X]");
		}
		

		System.out.println("Descarte:" + discard.toString());

		for (int i = 0; i < Suit.values().length; i++) {
			System.out.println("Palo " + Suit.values()[i] + ": " + suitStacks.get(i).toString());
		}

		for (int i = 1; i <= 7; i++) {
			System.out.println("Escalera" + i + ": " + straights.get(i - 1).toString());
		}

		System.out.println("-----------------------------");
	}

	public void imprimirBoard() {

		board();

		System.out.println("1. Mover de baraja a descarte");
		System.out.println("2. Mover de descarte a baraja");
		System.out.println("3. Mover de descarte a palo");
		System.out.println("4. Mover de descarte a escalera");
		System.out.println("5. Mover de escalera a palo");
		System.out.println("6. Mover de escalera a escalera");
		System.out.println("7. Mover de palo a escalera");
		System.out.println("8. Voltear en escalera");
		System.out.println("9. Salir");



	}

}
