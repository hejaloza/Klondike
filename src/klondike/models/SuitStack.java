package klondike.models;

import java.util.ArrayList;

public class SuitStack {
	
	private ArrayList<Card> suitStack;
	
	
	public SuitStack() {
		suitStack =new ArrayList<Card> ();
	}
	
	
	
	public void addCards(Card card){
					
			this.suitStack.add(card);
		
	}
	

	
	
	
	public String toString(){
		String a = "";
		for(int i=0 ; i<this.suitStack.size(); i++){
			a = a + this.suitStack.get(i).toString();
		}
		return a;
	}

}
