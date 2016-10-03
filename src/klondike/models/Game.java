package klondike.models;

import java.util.ArrayList;
import java.util.Hashtable;

import klondike.controllers.DiscardToLadderController;
import klondike.controllers.DiscardToSuitController;
import klondike.controllers.LadderToLadderController;
import klondike.controllers.LadderToSuitController;
import klondike.controllers.SuitToLadderController;
import klondike.utils.IO;
import klondike.views.GameView;

public class Game {

	private StackDeck deck;
	private ArrayList<StackLadder> ladders;
	private Hashtable<Suit, StackSuit> suitStacks;
	private StackDiscard discard;
	boolean ok;
	private GameView gameView;
	private IO io;

	public Game() {
		deck = new StackDeck();
		deck.shuffle();
		ladders = new ArrayList<StackLadder>();
		suitStacks = new Hashtable<Suit, StackSuit>();
		discard = new StackDiscard();
		gameView = new GameView(deck, ladders, suitStacks, discard);
		io = new IO();

		for (int i = 0; i < Suit.values().length; i++) {
			StackSuit suit = new StackSuit();
			suitStacks.put(Suit.values()[i], suit);
		}

		for (int i = 1; i <= 7; i++) {
			StackLadder s = new StackLadder();
			s.addCards(i, deck);
			ladders.add(s);
		}

		gameView.imprimirBoard();

		do {
			ok = false;
			int opcion = io.readInt("Opcion= [1-9]:");

			if (opcion == 1) {
				if (deck.getStackCard().isEmpty()) {
					io.writeln("La baraja está vacía");
				} else {
					Card a = deck.getStackCard().pop();
					a.setHidden(false);
					discard.getStackCard().push(a);
					gameView.imprimirBoard();
				}

			} else if (opcion == 2) {

				if (discard.getStackCard().isEmpty()) {
					io.writeln("El descarte está vacío");
				} else {

					while (discard.getStackCard().size() != 0) {

						deck.getStackCard().push(discard.getStackCard().pop());

					}

				}
				gameView.imprimirBoard();

			} else if (opcion == 3) {
				new DiscardToSuitController(discard, suitStacks, gameView).execute();
			} else if (opcion == 4) {
				new DiscardToLadderController(discard, ladders, gameView).execute();
			} else if (opcion == 5) {
				new LadderToSuitController(ladders, suitStacks, gameView).execute();
			} else if (opcion == 6) {
				new LadderToLadderController(ladders, gameView).execute();
			} else if (opcion == 7) {
				new SuitToLadderController(ladders, suitStacks,gameView).execute();
			} else if (opcion == 8) {
				int en_escalera = io.readInt("En que Escalera? [1-7]:");

				Card carta = ladders.get(en_escalera - 1).getStackCard().lastElement();
				carta.setHidden(false);

				gameView.imprimirBoard();
			}
		} while (!ok);
	}

	public static void main(String[] args) {
		new Game();
	}

}