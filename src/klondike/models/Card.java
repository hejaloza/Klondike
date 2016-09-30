package klondike.models;

public class Card{
	private Suit suit;
	private Number number;
	private boolean hidden;
	
	public Card (Number number, Suit suit,boolean hidden){
		this.number = number;
		this.suit = suit;
		this.hidden=hidden;
	}
	
	public String toString(){
		
		if( this.hidden){
			return "[]";
		}
		return this.number + "" + this.suit;
	}
	
	public void setHidden(boolean hidden){
		
		this.hidden=hidden;
		
		
	}
}