package klondike.models;

import java.util.ArrayList;
import java.util.Hashtable;

import klondike.controller.DiscardToStraightController;
import klondike.controller.DiscardToSuitController;
import klondike.utils.IO;
import klondike.views.GameView;

public class Game {

	private StackDeck deck;
	private ArrayList<StackStraight> straights;
	private Hashtable<Suit, StackSuit> suitStacks;
	private StackDiscard discard;
	boolean ok;
	private GameView gameView;
	private DiscardToSuitController discardToSuit;
	private DiscardToStraightController discardToStraight;
	private IO io;

	public Game() {
		deck = new StackDeck();
		deck.shuffle();
		straights = new ArrayList<StackStraight>();
		suitStacks = new Hashtable<Suit, StackSuit>();
		discard = new StackDiscard();
		gameView = new GameView(deck, straights, suitStacks, discard);
		discardToSuit= new DiscardToSuitController(discard,suitStacks);
		discardToStraight=new DiscardToStraightController(discard,straights);
		
		io = new IO();

		for (int i = 0; i < Suit.values().length; i++) {
			StackSuit suit = new StackSuit();
			suitStacks.put(Suit.values()[i], suit);
		}

		for (int i = 1; i <= 7; i++) {
			StackStraight s = new StackStraight();
			s.addCards(i, deck);
			straights.add(s);
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

				discardToSuit.moveDiscardToSuit();
				gameView.imprimirBoard();

			} else if (opcion == 4) {

				discardToStraight.moveDiscardToStraight();
				gameView.imprimirBoard();

			} else if (opcion == 5) {

				int opcion_escalera2 = io.readInt("De que Escalera? [1-7]:");

				StackStraight escalera = straights.get(opcion_escalera2 - 1);
				Card carta = escalera.getStackCard().pop();

				Suit palo = carta.getSuit();

				StackSuit a = suitStacks.get(palo);
				if (a.getStackCard().lastElement().getNumber().getCardValue() == carta.getNumber().getCardValue() - 1) {
					a.getStackCard().push(carta);
				} else {
					io.writeln("La carta no corresponde");
				}

				gameView.imprimirBoard();

			} else if (opcion == 6) {

				int de_escalera = io.readInt("De que Escalera? [1-7]:");
				int cuantas_cartas = io.readInt("Cuantas Cartas?:");
				int a_escalera = io.readInt("A que Escalera? [1-7]:");
				if (straights.get(de_escalera - 1).getStackCard()
						.get(straights.get(de_escalera - 1).getStackCard().size() - cuantas_cartas).getNumber()
						.getCardValue() == straights.get(a_escalera).getStackCard()
								.get(straights.get(a_escalera).getStackCard().size() - 1).getNumber().getCardValue()
								- 1) {
					StackCard a = new StackCard();

					for (int i = 0; i < cuantas_cartas; i++) {
						a.getStackCard().push(straights.get(de_escalera - 1).getStackCard().pop());
						// straights.get(a_escalera).getStackCard().push(straights.get(de_escalera).getStackCard().pop());
					}

					while (a.getStackCard().size() != 0) {
						// straights.get(a_escalera).getStackCard().push(straights.get(de_escalera).getStackCard().pop());
						straights.get(a_escalera - 1).getStackCard().push(a.getStackCard().pop());
					}

					gameView.imprimirBoard();
				} else {
					io.writeln("Selección no válida");
				}

			} else if (opcion == 7) {
				int de_palo = io.readInt("De que Palo? [1-4]:");
				int a_escalera = io.readInt("A que Escalera? [1-7]:");
				Suit s = Suit.values()[de_palo - 1];
				StackSuit palo = suitStacks.get(s);

				Card carta = palo.getStackCard().pop();

				straights.get(a_escalera - 1).getStackCard().push(carta);

				gameView.imprimirBoard();

			} else if (opcion == 8) {
				int en_escalera = io.readInt("En que Escalera? [1-7]:");

				Card carta = straights.get(en_escalera - 1).getStackCard().lastElement();
				carta.setHidden(false);

				gameView.imprimirBoard();
			}
		} while (!ok);
	}

	public static void main(String[] args) {
		new Game();
	}

}