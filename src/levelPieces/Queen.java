package levelPieces;

import gameEngine.Drawable;
import gameEngine.InteractionResult;

//Gamepiece, Returns ADVANCE/NONE
public class Queen extends GamePiece {
	
	public Queen(int location) {
		super('Q', "Queen", location);
	}

	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		if(playerLocation == this.getLocation()) {
			return InteractionResult.ADVANCE;
		} else {
			return InteractionResult.NONE;
		}
	}
}
