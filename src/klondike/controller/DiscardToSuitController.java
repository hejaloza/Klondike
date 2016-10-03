package klondike.controller;

import java.util.Hashtable;

import klondike.models.Card;
import klondike.models.StackDiscard;
import klondike.models.StackSuit;
import klondike.models.Suit;
import klondike.utils.IO;

public class DiscardToSuitController {
	
	private IO io;
	private StackDiscard discard;
	private Hashtable<Suit, StackSuit> suitStacks;
	
	public DiscardToSuitController(StackDiscard discard,Hashtable<Suit, StackSuit> suitStacks){
		
		this.discard=discard;
		this.suitStacks=suitStacks;
		io = new IO();
		
	}
	public void moveDiscardToSuit(){
		
		if (discard.getStackCard().isEmpty()) {

			io.writeln("El descarte está vacío");

		} else {

			Card discard_card = discard.getStackCard().lastElement();
			Suit discard_suit = discard_card.getSuit();
			int discard_value = discard_card.getNumber().getCardValue();
			StackSuit suit_array = suitStacks.get(discard_suit);

			if (suit_array.getStackCard().isEmpty()) {
				if (discard_value == 1) {
					suit_array.getStackCard().push(discard.getStackCard().pop());
				} else {
					io.writeln("La primera carta de un Palo debe ser una A");
				}

			} else {
				Card suit_card = suit_array.getStackCard().lastElement();
				int suit_value = suit_card.getNumber().getCardValue();
				if (discard_value == suit_value + 1) {
					suit_array.getStackCard().push(discard.getStackCard().pop());

				} else {
					io.writeln("La carta no corresponde");
				}
			}

		}
		
		
	}

}
