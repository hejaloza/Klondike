package klondike.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import klondike.views.GameView;

public class Game {

	private StackDeck deck;
	private ArrayList<StackStraight> straights;
	private ArrayList<StackSuit> suitStacks;
	private StackDiscard discard;
	boolean ok;
	private GameView gameView;

	public Game() {
		deck = new StackDeck();
		deck.shuffle();
		straights = new ArrayList<StackStraight>();
		suitStacks = new ArrayList<StackSuit>();
		discard = new StackDiscard();
		gameView = new GameView(deck, straights, suitStacks, discard);

		for (int i = 0; i < Suit.values().length; i++) {
			StackSuit suit = new StackSuit();
			suitStacks.add(suit);
		}

		for (int i = 1; i <= 7; i++) {
			StackStraight s = new StackStraight();
			s.addCards(i, deck);
			straights.add(s);
		}

		gameView.imprimirBoard();

		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(System.in));

		do {
			ok = false;
			try {
				System.out.println("Opcion? [1-9]:");
				int opcion = Integer.parseInt(br.readLine());

				if (opcion == 1) {
					Card a = deck.getStackCard().pop();
					a.setHidden(false);
					discard.getStackCard().push(a);
					gameView.imprimirBoard();

				} else if (opcion == 2) {

					if (deck.getStackCard().isEmpty()) {

						while (discard.getStackCard().size() != 0) {

							deck.getStackCard().push(discard.getStackCard().pop());

						}
						
					}
					gameView.imprimirBoard();

				}

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} while (!ok);

	}

	public static void main(String[] args) {
		new Game();
	}

}