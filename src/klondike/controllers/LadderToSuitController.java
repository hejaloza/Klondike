package klondike.controllers;

import java.util.ArrayList;
import java.util.Hashtable;

import klondike.models.Card;
import klondike.models.StackLadder;
import klondike.models.StackSuit;
import klondike.models.Suit;
import klondike.utils.IO;
import klondike.views.GameView;

public class LadderToSuitController {
	
	private ArrayList<StackLadder> ladders;
	private Hashtable<Suit, StackSuit> suitStacks;
	private GameView gameView;
	private IO io;
	
	public LadderToSuitController(ArrayList<StackLadder> ladders, Hashtable<Suit, StackSuit> suitStacks, GameView gameView){
		this.ladders = ladders;
		this.suitStacks = suitStacks;
		this.gameView = gameView;
		io = new IO();
	}

	public void execute(){
		int fromLadder = io.readInt("De que Escalera? [1-7]:");
		StackLadder ladder = ladders.get(fromLadder - 1);
		Card card = ladder.getStackCard().lastElement();
		StackSuit stackSuit = suitStacks.get(card.getSuit());
		if(stackSuit.getStackCard().isEmpty()){
			if(card.getNumber().getCardValue()==1){
				stackSuit.getStackCard().push(ladder.getStackCard().pop());
			}else{
				io.writeln("La carta no corresponde");
			}
		}else{
			int previousCardValue = stackSuit.getStackCard().lastElement().getNumber().getCardValue();
			if(previousCardValue == card.getNumber().getCardValue()-1){
				stackSuit.getStackCard().push(ladder.getStackCard().pop());
			}else{
				io.writeln("La carta no corresponde");
			}
		}
		gameView.imprimirBoard();
	}
}