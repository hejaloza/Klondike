package klondike.models;

import java.util.ArrayList;

public class Discard {
	
	private ArrayList<Card> discard;
	
	public Discard() {
		discard =new ArrayList<Card> ();
	}
	
	
	
	public String toString(){
		String a = "";
		for(int i=0 ; i<this.discard.size(); i++){
			a = a + this.discard.get(i).toString();
		}
		return a;
	}

}
