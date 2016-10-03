package klondike.controllers;

import java.util.ArrayList;

import klondike.models.Card;
import klondike.models.StackDiscard;
import klondike.models.StackLadder;
import klondike.utils.IO;
import klondike.views.GameView;

public class DiscardToLadderController {
	
	private StackDiscard discard;
	private ArrayList<StackLadder> ladders;
	private GameView gameView;
	private IO io;
	
	public DiscardToLadderController(StackDiscard discard,ArrayList<StackLadder> ladders, GameView gameView){
		this.discard=discard;
		this.ladders=ladders;
		this.gameView = gameView;
		io = new IO();
	}

	public void execute(){
		if (discard.getStackCard().isEmpty()) {
			io.writeln("El descarte esta vacio");
		} else {
			int fromLadder = io.readInt("A que Escalera? [1-7]:");
			Card discardCard = discard.getStackCard().lastElement();
			int discardValue = discardCard.getNumber().getCardValue();
			StackLadder escalera = ladders.get(fromLadder - 1);
			if (escalera.getStackCard().isEmpty()) {
				if (discardValue == 13) {
					escalera.getStackCard().push(discard.getStackCard().pop());
				} else {
					io.writeln("La carta no corresponde");
				}
			} else {
				Card ladderCard = escalera.getStackCard().lastElement();
				int ladderCardValue = ladderCard.getNumber().getCardValue();
				if ((discardValue == ladderCardValue - 1)) {
					escalera.getStackCard().push(discard.getStackCard().pop());
				} else {
					io.writeln("La carta no corresponde");
				}
			}
		}
		gameView.imprimirBoard();
	}
}