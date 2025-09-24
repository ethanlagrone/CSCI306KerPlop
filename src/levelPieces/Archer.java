package levelPieces;

import java.util.Random;

import gameEngine.Drawable;
import gameEngine.InteractionResult;
 

//Gamepiece, Returns KILL/NONE, Interacts at a distance
public class Archer extends GamePiece {

	//Archer Constructor
	public Archer(String label, int location) {
		super('A', label, location);
	}

	//Archer interacts with player
	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		if(playerLocation < this.getLocation()) {
			//Archer shoots at player if player in front of them
			Random random = new Random();
			int shot = random.nextInt(0, this.getLocation()-1);
			if(shot == playerLocation) {
				return InteractionResult.KILL;
			} else {
				return InteractionResult.NONE;
			}
		} else if (playerLocation == this.getLocation()) {
			//Archer does nothing
			return InteractionResult.NONE;
		} else {
			//Archer does nothing, the player got past them
			return InteractionResult.NONE;
		}
	}
}
