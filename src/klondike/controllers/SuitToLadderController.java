package klondike.controllers;

import java.util.ArrayList;
import java.util.Hashtable;

import klondike.models.Card;
import klondike.models.StackLadder;
import klondike.models.StackSuit;
import klondike.models.Suit;
import klondike.utils.IO;
import klondike.views.GameView;

public class SuitToLadderController {
	
	
	private ArrayList<StackLadder> ladders;
	private Hashtable<Suit,StackSuit> suitStacks;
	private GameView gameView;
	private IO io;
	
	
	public SuitToLadderController(ArrayList<StackLadder> ladders,Hashtable<Suit,StackSuit> suitStacks, GameView gameView){
		
		this.ladders=ladders;
		this.gameView = gameView;
		this.suitStacks = suitStacks;
		io = new IO();
	}
	
	public void execute(){
		
		int fromSuit = io.readInt("De que Palo? [1-4]:");
		int toLadder = io.readInt("A que Escalera? [1-7]:");
		Suit suitStack = Suit.values()[fromSuit - 1];
		
		StackSuit suit = suitStacks.get(suitStack);
		
		if(suit.getStackCard().isEmpty()){
			io.writeln("El Palo esta vacio");
		}else{			
			Card card = suit.getStackCard().pop();
			ladders.get(toLadder - 1).getStackCard().push(card);			
		}

		gameView.imprimirBoard();
		
	}
	
	
	

}
