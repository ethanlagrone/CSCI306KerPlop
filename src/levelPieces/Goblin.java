package levelPieces;

import gameEngine.Drawable;
import gameEngine.InteractionResult;
import gameEngine.Moveable;

//Gamepiece, Movable, moves linearly, returns HIT/NONE
public class Goblin extends GamePiece implements Moveable {
	
	public Goblin(int location) {
		super('G', "Goblin", location);
	}

	//Goblin will hit the player if they are on the same spot
	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		if(playerLocation == this.getLocation()) {
			return InteractionResult.HIT;
		} else {
			return InteractionResult.NONE;
		}
	}

	//Goblin moves to the left
	@Override
	public void move(Drawable[] gameBoard, int playerLocation) {
		int temp = this.getLocation();
	    int newLocation = temp - 1;
	    if (newLocation >= 0) {
	        gameBoard[temp] = null;  
	        this.setLocation(newLocation);   
	        gameBoard[newLocation] = this;   
	    }
	}
}
