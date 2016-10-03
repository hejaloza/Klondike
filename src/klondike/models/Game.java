package klondike.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import klondike.views.GameView;

public class Game {

	private StackDeck deck;
	private ArrayList<StackStraight> straights;
	private Hashtable<Suit, StackSuit> suitStacks;
	private StackDiscard discard;
	boolean ok;
	private GameView gameView;

	public Game() {
		deck = new StackDeck();
		deck.shuffle();
		straights = new ArrayList<StackStraight>();
		suitStacks = new Hashtable<Suit, StackSuit>();
		discard = new StackDiscard();
		gameView = new GameView(deck, straights, suitStacks, discard);

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

		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(System.in));

		do {
			ok = false;
			try {
				System.out.println("Opcion? [1-9]:");
				int opcion = Integer.parseInt(br.readLine());

				if (opcion == 1) {
					if (deck.getStackCard().isEmpty()) {
						System.out.println("La baraja está vacía");
					} else {
						Card a = deck.getStackCard().pop();
						a.setHidden(false);
						discard.getStackCard().push(a);
						gameView.imprimirBoard();
					}

				} else if (opcion == 2) {

					if (discard.getStackCard().isEmpty()) {
						System.out.println("El descarte está vacío");
					} else {

						while (discard.getStackCard().size() != 0) {

							deck.getStackCard().push(discard.getStackCard().pop());

						}

					}
					gameView.imprimirBoard();

				} else if (opcion == 3) {

					if (discard.getStackCard().isEmpty()) {

						System.out.println("El descarte esta vacio");

					} else {

						Card discard_card = discard.getStackCard().lastElement();
						Suit discard_suit = discard_card.getSuit();
						int discard_value = discard_card.getNumber().getCardValue();
						StackSuit suit_array = suitStacks.get(discard_suit);

						if (suit_array.getStackCard().isEmpty()) {
							if (discard_value == 1) {
								suit_array.getStackCard().push(discard.getStackCard().pop());
							} else {
								System.out.println("La primera carta de un Palo debe ser una A");

							}

						} else {
							Card suit_card = suit_array.getStackCard().lastElement();
							int suit_value = suit_card.getNumber().getCardValue();
							if (discard_value == suit_value + 1) {
								suit_array.getStackCard().push(discard.getStackCard().pop());

							} else {
								System.out.println("La carta no corresponde");
							}
						}

					}

					gameView.imprimirBoard();

				} else if (opcion == 4) {

					if (discard.getStackCard().isEmpty()) {

						System.out.println("El descarte esta vacio");

					}

					else {

						System.out.println("A que Escalera? [1-7]:");
						int opcion_escalera = Integer.parseInt(br.readLine());
						Card discard_card = discard.getStackCard().lastElement();
						int discard_value = discard_card.getNumber().getCardValue();

						StackStraight escalera = straights.get(opcion_escalera - 1);

						if (escalera.getStackCard().isEmpty()) {

							if (discard_value == 13) {
								escalera.getStackCard().push(discard.getStackCard().pop());

							} else {
								System.out.println("La carta no corresponde");
							}

						} else {

							Card straight_card = escalera.getStackCard().lastElement();
							int straight_card_value = straight_card.getNumber().getCardValue();

							if ((discard_value == straight_card_value - 1)) {
								escalera.getStackCard().push(discard.getStackCard().pop());

							} else {
								System.out.println("La carta no corresponde");
							}

						}

					}

					gameView.imprimirBoard();

				} else if (opcion == 5) {

					System.out.println("De que Escalera? [1-7]:");
					int opcion_escalera2 = Integer.parseInt(br.readLine());

					StackStraight escalera = straights.get(opcion_escalera2 - 1);
					Card carta = escalera.getStackCard().pop();

					Suit palo = carta.getSuit();

					StackSuit a = suitStacks.get(palo);
					if (a.getStackCard().lastElement().getNumber().getCardValue() == carta.getNumber().getCardValue()
							- 1) {
						a.getStackCard().push(carta);
					} else {
						System.out.println("La carta no corresponde");
					}

					gameView.imprimirBoard();

				} else if (opcion == 6) {

					System.out.println("De que Escalera? [1-7]:");
					int de_escalera = Integer.parseInt(br.readLine());

					System.out.println("Cuantas Cartas?:");
					int cuantas_cartas = Integer.parseInt(br.readLine());

					System.out.println("A que Escalera? [1-7]:");
					int a_escalera = Integer.parseInt(br.readLine());

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
						System.out.println("Selección no válida");
					}

				} else if (opcion == 7) {

					System.out.println("De que Palo? [1-4]:");
					int de_palo = Integer.parseInt(br.readLine());
					System.out.println("A que Escalera? [1-7]:");
					int a_escalera = Integer.parseInt(br.readLine());
					Suit s = Suit.values()[de_palo - 1];
					StackSuit palo = suitStacks.get(s);

					Card carta = palo.getStackCard().pop();

					straights.get(a_escalera - 1).getStackCard().push(carta);

					gameView.imprimirBoard();

				} else if (opcion == 8) {

					System.out.println("En que Escalera? [1-7]:");
					int en_escalera = Integer.parseInt(br.readLine());

					Card carta = straights.get(en_escalera - 1).getStackCard().lastElement();
					carta.setHidden(false);

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