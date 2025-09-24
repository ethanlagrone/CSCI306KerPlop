package levelPieces;

import java.util.Random;

import gameEngine.Drawable;
import gameEngine.InteractionResult;

//Gamepiece, Returns KILL/NONE
public class Canon extends GamePiece{

	public Canon(char symbol, String label, int location) {
		super('<', label, location);
	}

	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		if((playerLocation < this.getLocation()) && (playerLocation - this.getLocation() <= 3)){
			Random random = new Random();
			int shot = random.nextInt(0, 10);
			if(shot == 5) {
				return InteractionResult.KILL;
			} else {
				return InteractionResult.NONE;
			}
		} else {
			return InteractionResult.NONE;
		}
	}
}
