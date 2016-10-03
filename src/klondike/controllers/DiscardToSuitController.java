package klondike.controllers;

import java.util.Hashtable;

import klondike.models.Card;
import klondike.models.StackDiscard;
import klondike.models.StackSuit;
import klondike.models.Suit;
import klondike.utils.IO;
import klondike.views.GameView;

public class DiscardToSuitController {

	private StackDiscard discard;
	private Hashtable<Suit, StackSuit> suitStacks;
	private GameView gameView;
	private IO io;

	public DiscardToSuitController(StackDiscard discard, Hashtable<Suit, StackSuit> suitStacks, GameView gameView) {
		this.discard = discard;
		this.suitStacks = suitStacks;
		this.gameView = gameView;
		io = new IO();
	}

	public void execute() {
		if (discard.getStackCard().isEmpty()) {
			io.writeln("El descarte está vacío");
		} else {
			Card discardCard = discard.getStackCard().lastElement();
			Suit discardSuit = discardCard.getSuit();
			int discardValue = discardCard.getNumber().getCardValue();
			StackSuit suitArray = suitStacks.get(discardSuit);
			if (suitArray.getStackCard().isEmpty()) {
				if (discardValue == 1) {
					suitArray.getStackCard().push(discard.getStackCard().pop());
				} else {
					io.writeln("La primera carta de un Palo debe ser una A");
				}
			} else {
				Card suit_card = suitArray.getStackCard().lastElement();
				int suit_value = suit_card.getNumber().getCardValue();
				if (discardValue == suit_value + 1) {
					suitArray.getStackCard().push(discard.getStackCard().pop());
				} else {
					io.writeln("La carta no corresponde");
				}
			}
		}
		gameView.imprimirBoard();
	}
}