package klondike.models;

import java.util.ArrayList;

public class Straight {
	
	private ArrayList<Card> straight;
	
	public Straight() {
		straight =new ArrayList<Card> ();
	}
	
	public void addCards(int max, Deck deck){
		for(int i = 0; i<max; i++){
			this.straight.add(deck.getDeck().get(0));
			deck.getDeck().remove(0);
		}
		
		
	}
	
	public String toString(){
		String a = "";
		for(int i=0 ; i<this.straight.size(); i++){
			a = a + this.straight.get(i).toString();
		}
		return a;
	}

}
