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
	private Hashtable<Suit,StackSuit> suitStacks;
	private StackDiscard discard;
	boolean ok;
	private GameView gameView;

	public Game() {
		deck = new StackDeck();
		deck.shuffle();
		straights = new ArrayList<StackStraight>();
		suitStacks = new Hashtable<Suit,StackSuit>();
		discard = new StackDiscard();
		gameView = new GameView(deck, straights, suitStacks, discard);

		for (int i = 0; i < Suit.values().length; i++) {
			StackSuit suit = new StackSuit();		
			suitStacks.put(Suit.values()[i],suit);
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

				} else if (opcion == 3) {

					if (discard.getStackCard().size() > 0) {
						Card carta = discard.getStackCard().pop();
						Suit palo= carta.getSuit();
						
						StackSuit a = suitStacks.get(palo);
						a.getStackCard().push(carta);


					}

					gameView.imprimirBoard();

							
			} else if (opcion == 4) {

				if (discard.getStackCard().size() > 0) {
					
					System.out.println("A que Escalera? [1-7]:");
					int opcion_escalera = Integer.parseInt(br.readLine());
					Card carta = discard.getStackCard().pop();
					
					StackStraight escalera= straights.get(opcion_escalera-1);
					escalera.getStackCard().push(carta);

				}

				gameView.imprimirBoard();

			} else if (opcion == 5) {

						
					System.out.println("De que Escalera? [1-7]:");
					int opcion_escalera2 = Integer.parseInt(br.readLine());
					
					
					StackStraight escalera= straights.get(opcion_escalera2-1);
					Card carta = escalera.getStackCard().pop();
					

					Suit palo= carta.getSuit();
					
					StackSuit a = suitStacks.get(palo);
					a.getStackCard().push(carta);
					

				gameView.imprimirBoard();

			}else if (opcion == 6) {

				
				System.out.println("De que Escalera? [1-7]:");
				int de_escalera = Integer.parseInt(br.readLine());
				
				System.out.println("Cuantas Cartas?:");
				int cuantas_cartas = Integer.parseInt(br.readLine());
				
				System.out.println("A que Escalera? [1-7]:");
				int a_escalera = Integer.parseInt(br.readLine());
				
				StackCard a = new StackCard();
				
				for(int i=0; i<cuantas_cartas; i++){
					a.getStackCard().push(straights.get(de_escalera-1).getStackCard().pop());
					//straights.get(a_escalera).getStackCard().push(straights.get(de_escalera).getStackCard().pop());
				}
				
				while(a.getStackCard().size()!=0){
					//straights.get(a_escalera).getStackCard().push(straights.get(de_escalera).getStackCard().pop());
					straights.get(a_escalera-1).getStackCard().push(a.getStackCard().pop());
				}
				
								

			gameView.imprimirBoard();

		}else if (opcion == 7) {

			
			System.out.println("De que Palo? [1-4]:");
			int de_palo = Integer.parseInt(br.readLine());
			System.out.println("A que Escalera? [1-7]:");
			int a_escalera = Integer.parseInt(br.readLine());
			Suit s = Suit.values()[de_palo-1];
			StackSuit palo= suitStacks.get(s);
			
			Card carta = palo.getStackCard().pop();

			straights.get(a_escalera-1).getStackCard().push(carta);
			
			

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