package klondike.models;

public class StackStraight extends StackCard {
		
	public StackStraight() {
		super();
	}
	
	public void addCards(int max, StackDeck deck){
		for(int i = 0; i<max; i++){
			Card carta = deck.getStackCard().pop();
			if(i==max-1){
				carta.setHidden(false);
			}
			this.getStackCard().add(carta);
		}
	}
}