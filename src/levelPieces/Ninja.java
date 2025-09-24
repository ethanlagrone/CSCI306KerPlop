package levelPieces;

import gameEngine.Drawable;
import gameEngine.InteractionResult;
import gameEngine.Moveable;

//Gamepiece, movable, moves randomly, Returns HIT/NONE
public class Ninja extends GamePiece implements Moveable {

	public Ninja(char symbol, String label, int location) {
		super('N', label, location);
	}

	//Ninja hits them if it ends up within one spot of player
	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		if((playerLocation-1 == this.getLocation()) || (playerLocation+1 == this.getLocation())) {
			return InteractionResult.HIT;
		} else {
			return InteractionResult.NONE;
		}
	}

	//Want this to move the ninja randomly
	@Override
	public void move(Drawable[] gameBoard, int playerLocation) {
		
	}

}
