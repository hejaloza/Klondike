package klondike.controller;

import java.util.ArrayList;

import klondike.models.Card;
import klondike.models.StackDiscard;
import klondike.models.StackStraight;
import klondike.utils.IO;

public class DiscardToStraightController {
	
	private IO io;
	private StackDiscard discard;
	private ArrayList<StackStraight> straights;
	
	public DiscardToStraightController(StackDiscard discard,ArrayList<StackStraight> straights){
		
		this.discard=discard;
		this.straights=straights;
		io = new IO();
		
	}
	
	
	
	public void moveDiscardToStraight(){
		
		if (discard.getStackCard().isEmpty()) {
			System.out.println("El descarte esta vacio");
		} else {
			int opcion_escalera = io.readInt("A que Escalera? [1-7]:");
			Card discard_card = discard.getStackCard().lastElement();
			int discard_value = discard_card.getNumber().getCardValue();

			StackStraight escalera = straights.get(opcion_escalera - 1);

			if (escalera.getStackCard().isEmpty()) {

				if (discard_value == 13) {
					escalera.getStackCard().push(discard.getStackCard().pop());

				} else {
					io.writeln("La carta no corresponde");
				}

			} else {

				Card straight_card = escalera.getStackCard().lastElement();
				int straight_card_value = straight_card.getNumber().getCardValue();

				if ((discard_value == straight_card_value - 1)) {
					escalera.getStackCard().push(discard.getStackCard().pop());

				} else {
					io.writeln("La carta no corresponde");
				}

			}

		}
		
	}

}
