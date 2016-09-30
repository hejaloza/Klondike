package klondike.views;

import klondike.models.Straight;

public class StraightView {
	
	private Straight straight;
	
	public StraightView(Straight straight){
		
		this.straight =straight;
		
		System.out.println(this.straight.toString());
		
		
	}

}
