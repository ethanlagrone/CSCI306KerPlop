package levelPieces;

import java.util.Random;

import gameEngine.Drawable;
import gameEngine.InteractionResult;
import gameEngine.Moveable;

//Gamepiece, movable, moves randomly, Returns HIT/NONE
public class Ninja extends GamePiece implements Moveable {
	
	public Ninja(int location) {
		super('N', "Ninja", location);
	}

	//Ninja hits them if it ends up within one spot of player
	@Override
	public InteractionResult interact(Drawable[] gameBoard, int playerLocation) {
		if(playerLocation == this.getLocation() || playerLocation - 1 == this.getLocation() || playerLocation + 1 == this.getLocation()) {
			return InteractionResult.HIT;
		} else {
			return InteractionResult.NONE;
		}
	}

	//Ninja moves randomly
	@Override
	public void move(Drawable[] gameBoard, int playerLocation) {
		Random random = new Random();
		int temp = this.getLocation();
		//The princess and castle will be at the end, so ninja random location shouldn't be either of the last 2 spots.
		int newLocation = random.nextInt(this.getLocation()-5, this.getLocation()+5);
		
		if (newLocation<0) {
			newLocation=0;
		}
		else {if(newLocation>7) {
			newLocation=7;
		}}
		
		if (newLocation==playerLocation) {
			if((newLocation-1)<0) {
				newLocation++;
			}
			else {
				newLocation--;
			}
		}
		
		if((newLocation>=0)&&(newLocation<8)) {
	        gameBoard[temp] = null;  
	        this.setLocation(newLocation);   
	        gameBoard[newLocation] = this;  
		}
	}

}
