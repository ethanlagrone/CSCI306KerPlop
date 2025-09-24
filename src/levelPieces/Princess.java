package levelPieces;

import gameEngine.Drawable;
import gameEngine.InteractionResult;

//Gamepiece, Returns ADVANCE/NONE
public class Princess extends GamePiece {
	
	public Princess(String label, int location) {
		super('P', label, location);
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
