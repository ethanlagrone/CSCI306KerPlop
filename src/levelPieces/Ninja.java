package levelPieces;

import java.util.Random;

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

	//Ninja moves randomly
	@Override
	public void move(Drawable[] gameBoard, int playerLocation) {
		Random random = new Random();
		int ninjaSpot = random.nextInt(0, gameBoard.length-1);
		this.setLocation(ninjaSpot);
	}

}
