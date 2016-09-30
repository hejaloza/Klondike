package klondike.models;

import java.util.ArrayList;

public class Straight {
	
	private ArrayList<Card> straight;
	
	public Straight() {
		straight =new ArrayList<Card> ();
	}
	
	public void addCards(int max, Deck deck){
		for(int i = 0; i<max; i++){
			Card carta = deck.getDeck().get(0);

			if(i==max-1){
				carta.setHidden(false);
			}
			this.straight.add(carta);
			deck.getDeck().remove(carta);
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
