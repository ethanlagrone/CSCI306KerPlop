package levelPieces;

import gameEngine.Drawable;
import gameEngine.InteractionResult;

//Gamepiece, Returns GET_POINT/NONE
public class Treasure extends GamePiece {
	
	public Treasure(int location) {
		super('T', "Treasure", location);
	}

	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		if(playerLocation == this.getLocation()) {
			return InteractionResult.GET_POINT;
		} else {
			return InteractionResult.NONE;
		}
	}
}
