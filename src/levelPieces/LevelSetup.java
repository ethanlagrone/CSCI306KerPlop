package levelPieces;

import java.util.ArrayList;

import gameEngine.Drawable;
import gameEngine.Moveable;

public class LevelSetup {
	
	//Generate level
	public void createLevel(int levelNum) {
		if(levelNum == 1) {
			//Setup level 1
		} else {
			//Setup level 2
		}
		
	}
	
	//Return the board
	public Drawable[] getBoard() {
		return null;
		
	}

	//Return an arraylist of the moving pieces
	public ArrayList<Moveable> getMovingPieces() {
		// TODO Auto-generated method stub
		return null;
	}

	//Return an arraylist of interacting pieces
	public ArrayList<GamePiece> getInteractingPieces() {
		// TODO Auto-generated method stub
		return null;
	}

	//return player starting location
	public int getPlayerStartLoc() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
